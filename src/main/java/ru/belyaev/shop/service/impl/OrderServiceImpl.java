package ru.belyaev.shop.service.impl;

import ru.belyaev.framework.annotation.Transactional;
import ru.belyaev.framework.factory.JDBCConnectionUtils;
import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.exception.InternalServerErrorException;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.jdbc.JDBCUtil;
import ru.belyaev.shop.jdbc.ResultSetHandler;
import ru.belyaev.shop.jdbc.ResultSetHandlerFactory;
import ru.belyaev.shop.model.CurrentAccount;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.model.ShoppingCartItem;
import ru.belyaev.shop.model.SocialAccount;
import ru.belyaev.shop.service.OrderService;
import ru.belyaev.shop.util.SessionUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
class OrderServiceImpl implements OrderService {

    private static final ResultSetHandler<Product> productsResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSethandler(ResultSetHandlerFactory.RESULT_SET_HANDLER_PRODUCT);

    private final DataSource dataSource;

    public OrderServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        try (Connection c = dataSource.getConnection()) {
            Product product =  JDBCUtil.select(JDBCConnectionUtils.getConnection(), "SELECT p.*, c.name as category, pr.name as producer FROM product p, producer pr, category c "
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
            System.out.println( new Timestamp(System.currentTimeMillis()));
            System.out.println(currentAccount.getId());
            System.out.println( new Timestamp(System.currentTimeMillis()));
            Order order = JDBCUtil.insert(JDBCConnectionUtils.getConnection(), "INSERT INTO \"order\" VALUES (nextval('order_seq'),?,?)",
                    ResultSetHandlerFactory.getSingleResultSethandler(ResultSetHandlerFactory.RESULT_SET_HANDLER_ORDER) , currentAccount.getId(), new Timestamp(System.currentTimeMillis()));
            // добалвение в базу элементов, созданного заказ
            JDBCUtil.insertBatch(JDBCConnectionUtils.getConnection(), "INSERT INTO order_item VALUES (nextval('order_item_seq'),?,?,?) ", toOrderItemParameterList(order.getId(),shoppingCart.getItems()));
            c.commit();
            return order.getId();
        } catch (SQLException e) {
            throw new InternalServerErrorException("Ошибка в добавлении заказа в Базу данных");
        }

    }

    private List<Object[]> toOrderItemParameterList(long idOrder, Collection<ShoppingCartItem> items) {
        List<Object[]> parameterList = new ArrayList<>();
        for (ShoppingCartItem item: items) {
            parameterList.add(new Object[] {idOrder, item.getProduct().getId(), item.getCount()});
        }
        return parameterList;
    }


    @Override
    public CurrentAccount authenticate(SocialAccount socialAccount) {
        try (Connection c = dataSource.getConnection()) {
            Account account = JDBCUtil.select(JDBCConnectionUtils.getConnection(), "SELECT * FROM account WHERE email=?",
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
        return null;
    }
}
