package ru.belyaev.shop.service.impl;

import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.exception.InternalServerErrorException;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.jdbc.JDBCUtil;
import ru.belyaev.shop.jdbc.ResultSetHandler;
import ru.belyaev.shop.jdbc.ResultSetHandlerFactory;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.service.OrderService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class OrderServiceImpl implements OrderService {

    private static final ResultSetHandler<Product> productsResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSethandler(ResultSetHandlerFactory.RESULT_SET_HANDLER_PODUCT);

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
}
