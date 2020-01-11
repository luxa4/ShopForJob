package ru.belyaev.shop.service.impl;


import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.entity.OrderItem;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.exception.AccessDeniedException;
import ru.belyaev.shop.exception.InternalServerErrorException;
import ru.belyaev.shop.exception.ResourceNotFoundException;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.jdbc.JDBCUtil;
import ru.belyaev.shop.jdbc.ResultSetHandler;
import ru.belyaev.shop.jdbc.ResultSetHandlerFactory;
import ru.belyaev.shop.model.CurrentAccount;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.model.ShoppingCartItem;
import ru.belyaev.shop.model.SocialAccount;
import ru.belyaev.shop.service.OrderService;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


class OrderServiceImpl implements OrderService {

    @Override
    public void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        shoppingCart.removeProduct(productForm.getIdProduct(), productForm.getCount());
    }

    private static final ResultSetHandler<Product> productsResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSethandler(ResultSetHandlerFactory.RESULT_SET_HANDLER_PRODUCT);
    private static final ResultSetHandler<List<OrderItem>> orderItemListResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.RESULT_SET_HANDLER_ORDER_ITEM);
    private static final ResultSetHandler<Order> orderResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSethandler(ResultSetHandlerFactory.RESULT_SET_HANDLER_ORDER);
    private static final ResultSetHandler<List<Order>> ordersResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.RESULT_SET_HANDLER_ORDER);

    private static final ResultSetHandler<Integer> countResultSetHandler = ResultSetHandlerFactory.getCountResultSet();


    private final DataSource dataSource;

    public OrderServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        try (Connection c = dataSource.getConnection()) {
            Product product =  JDBCUtil.select(c, "SELECT p.*, c.name as category, pr.name as producer FROM product p, producer pr, category c "
                    + "WHERE c.id=p.id_category and pr.id=p.id_producer and p.id=?", productsResultSetHandler, productForm.getIdProduct());
        if(product == null)
            throw new InternalServerErrorException("Product is not found in DataBase: id: " + productForm.getIdProduct());

        shoppingCart.addProduct(product,productForm.getCount());
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }

    @Override
    public long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount) {
        if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
            throw new InternalServerErrorException("Shopping cart is null or empty");
        }
        try (Connection c = dataSource.getConnection()) {
            // Добавление в базу заказа
//            System.out.println( new Timestamp(System.currentTimeMillis()));
//            System.out.println(currentAccount.getId());
//            System.out.println( new Timestamp(System.currentTimeMillis()));
            Order order = JDBCUtil.insert(c, "INSERT INTO \"order\" VALUES (nextval('order_seq'),?,?)",
                    orderResultSetHandler , currentAccount.getId(), new Timestamp(System.currentTimeMillis()));
            // добавление в базу элементов, созданного заказ
            JDBCUtil.insertBatch(c, "INSERT INTO order_item VALUES (nextval('order_item_seq'),?,?,?,?) ", toOrderItemParameterList(order.getId(),shoppingCart.getItems(), currentAccount));
            c.commit();
            return order.getId();
        } catch (SQLException e) {
            throw new InternalServerErrorException("Ошибка в добавлении заказа в Базу данных");
        }

    }

    private List<Object[]> toOrderItemParameterList(long idOrder, Collection<ShoppingCartItem> items, CurrentAccount currentAccount) {
        List<Object[]> parameterList = new ArrayList<>();
        for (ShoppingCartItem item: items) {
            parameterList.add(new Object[] {idOrder, item.getProduct().getId(), currentAccount.getId(), item.getCount()});
        }
        return parameterList;
    }


    @Override
    public CurrentAccount authenticate(SocialAccount socialAccount) {
        try (Connection c = dataSource.getConnection()) {
            Account account = JDBCUtil.select(c, "SELECT * FROM account WHERE email=?",
                    ResultSetHandlerFactory.getSingleResultSethandler(ResultSetHandlerFactory.RESULT_SET_HANDLER_ACCOUNT), socialAccount.getEmail());
            if (account == null) {
                account = new Account(socialAccount.getName(), socialAccount.getEmail());
                JDBCUtil.insert(c, "INSERT INTO account VALUES (nextval('account_seq'), ?, ?)",
                        ResultSetHandlerFactory.getSingleResultSethandler(ResultSetHandlerFactory.RESULT_SET_HANDLER_ACCOUNT), socialAccount.getName(), socialAccount.getEmail());
                c.commit();
            }
            return account;
        } catch (SQLException e) {
            throw  new InternalServerErrorException("SQL exception - cant add new Account");
        }
    }

    @Override
    public Order findOrderById(Long id, CurrentAccount currentAccount) {
        try (Connection c = dataSource.getConnection()) {
            Order order = JDBCUtil.select(c, "select * from \"order\" where id=?", orderResultSetHandler, id);
            if (order == null) {
                throw new ResourceNotFoundException("Order not found by id: " + id);
            }
            if (!order.getIdAccount().equals(currentAccount.getId())) {
                throw new AccessDeniedException("Account with id=" + currentAccount.getId() + " is not owner for order with id=" + id);
            }
            List<OrderItem> list = JDBCUtil.select(c,
                    "select o.id as oid, o.id_order as id_order, o.id_product, o.count, p.*, c.name as category, pr.name as producer from order_item o, product p, category c, producer pr "
                            + "where pr.id=p.id_producer and c.id=p.id_category and o.id_product=p.id and o.id_order=?",
                    orderItemListResultSetHandler, id);
            order.setProducts(list);
            return order;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Order> listMyOrders(CurrentAccount currentAccount, int page, int limit) {
        int offset = (page - 1) * limit;
        try (Connection c = dataSource.getConnection()) {
            List<Order> orders = JDBCUtil.select(c, "select * from \"order\" where id_account=? order by id desc limit ? offset ?", ordersResultSetHandler, currentAccount.getId(), limit, offset);
            return orders;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }

    @Override
    public int countMyOrders(CurrentAccount currentAccount) {
        try (Connection c = dataSource.getConnection()) {
            return JDBCUtil.select(c, "select count(*) from \"order\" where id_account=?", countResultSetHandler, currentAccount.getId());
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }

    @Override
    public String serializeShoppingCart(ShoppingCart shoppingCart) {
        StringBuilder res = new StringBuilder();
        for (ShoppingCartItem item : shoppingCart.getItems()) {
            res.append(item.getProduct().getId()).append("-").append(item.getCount()).append("|");
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }
}
