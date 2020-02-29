package ru.belyaev.shop.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.entity.OrderItem;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.exception.AccessDeniedException;
import ru.belyaev.shop.exception.InternalServerErrorException;
import ru.belyaev.shop.exception.ResourceNotFoundException;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.model.ShoppingCartItem;
import ru.belyaev.shop.model.SocialAccount;
import ru.belyaev.shop.repositories.*;
import ru.belyaev.shop.service.OrderService;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProducerDao producerDao;


    @Override
    public void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        shoppingCart.removeProduct(productForm.getIdProduct(), productForm.getCount());
    }

    @Override
    public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        try  {
            Product product =  productDao.findProductById(productForm.getIdProduct());
        if(product == null)
            throw new InternalServerErrorException("Product is not found in DataBase: id: " + productForm.getIdProduct());

        shoppingCart.addProduct(product,productForm.getCount());
        } catch (Exception e) {
            throw new InternalServerErrorException("Can't execute sql query addProductToShoppingCart: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public long makeOrder(ShoppingCart shoppingCart, Account currentAccount) {
        if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
            throw new InternalServerErrorException("Shopping cart is null or empty");
        }
        try {
            // Добавление в базу заказа
            LOGGER.info("-->>> Создаем объект заказа");
            Order order = new Order();
            order.setAccount(currentAccount);
            LOGGER.info("-->>> Присваиваем Id_account {}", currentAccount.getId());
            order.setCreated(new Timestamp(System.currentTimeMillis()));
            LOGGER.info("-->>> Присваиваем время заказа {}", new Timestamp(System.currentTimeMillis()));
            LOGGER.info("-->>> Saving order ...");
            orderDao.save(order);
            LOGGER.info("-->>> Заказ сохранен - номер {}", order.getId());
            // добавление в базу элементов, созданного заказ
            List<OrderItem> listOrderItems = toOrderItemParameterList(order,shoppingCart.getItems());
            orderItemDao.saveAll(listOrderItems);

            return order.getId();
        } catch (Exception e) {
            throw new InternalServerErrorException("Ошибка в добавлении заказа в Базу данных -> makeOrder");
        }

    }

    private List<OrderItem> toOrderItemParameterList(Order order, Collection<ShoppingCartItem> items) {
        List<OrderItem> parameterList = new ArrayList<>();
        int i = 1;
        LOGGER.info("-->>> Beginning to execute Products from Cart...  ");
        for (ShoppingCartItem item: items) {
            LOGGER.info("-->>> НОВЫЙ ПРОДУКТ - {} - ", i);
            OrderItem orderItem = new OrderItem();
            LOGGER.info("-->>> Создали объект order_item ");
            orderItem.setOrder(order);
            LOGGER.info("-->>> Присвоиили номер заказа :{}", order.getId());
            orderItem.setProduct(item.getProduct());
            LOGGER.info("-->>> Присвоиили продукт - {}", item.getProduct().getName());
            orderItem.setCount(item.getCount());
            LOGGER.info("-->>> Присвоиили кол-во данного продукта - {}", item.getCount());
            i++;
            parameterList.add(orderItem);
        }
        return parameterList;
    }


    @Transactional
    @Override
    public Account authenticate(SocialAccount socialAccount) {
        try {
            Account account = accountDao.findAccountByEmail(socialAccount.getEmail());
            if (account == null) {
                account = new Account(socialAccount.getName(), socialAccount.getEmail());
                accountDao.save(account);
            }
            return account;
        } catch (Exception e) {
            throw  new InternalServerErrorException("SQL exception - cant add new Account");
        }
    }

    @Override
    public Order findOrderById(Long id, Account currentAccount) {
        try  {
            Order order = orderDao.findOrderById(id);
            if (order == null) {
                throw new ResourceNotFoundException("Order not found by id: " + id);
            }
            if (!order.getAccount().getId().equals(currentAccount.getId())) {
                throw new AccessDeniedException("Account with id=" + currentAccount.getId() + " is not owner for order with id=" + id);
            }
            List<OrderItem> list =  orderItemDao.findOrderItemByOrder_Id(id);
            order.setProducts(list);
            return order;
        } catch (Exception e) {
            throw new InternalServerErrorException("Can't execute SQL request findOrderById: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Order> listMyOrders(Account currentAccount, int page, int limit) {
        try  {
            Pageable pageable = PageRequest.of(page-1, limit);
            List<Order> orders = orderDao.listMyOrders(currentAccount.getId(), pageable);
            return orders;
        } catch (Exception e) {
            throw new InternalServerErrorException("Can't execute SQL request listMyOrders: " + e.getMessage(), e);
        }
    }

    @Override
    public int countMyOrders(Account currentAccount) {
        try  {
            return orderDao.countOrderByAccount_Id(currentAccount.getId());
        } catch (Exception e) {
            throw new InternalServerErrorException("Can't execute SQL request countMyOrders: " + e.getMessage(), e);
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
