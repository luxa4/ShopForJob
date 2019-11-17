// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:43

package ru.belyaev.shop.jdbc;

import ru.belyaev.shop.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetHandlerFactory  {

    // БЕРЕМ информацию из базы и создаем Объекты (сущности)


    // создани объекта типа конструктора
    public static ResultSetHandler<Product> RESULT_SET_HANDLER_PRODUCT = new ResultSetHandler<Product>() {
        @Override
        public Product handle(ResultSet rs) throws SQLException {
            Product p = new Product();
            p.setProducer(rs.getString("producer"));
            p.setPrice(rs.getBigDecimal("price"));
            p.setName(rs.getString("name"));
            p.setImageLink(rs.getString("image_link"));
            p.setDescription(rs.getString("description"));
            p.setId(rs.getInt("id"));
            p.setCategory(rs.getString("category"));
            return p;
        }
    };

    public static ResultSetHandler<Category> RESULT_SET_HANDLER_CATEGORY = new ResultSetHandler<Category>() {
        @Override
        public Category handle(ResultSet rs) throws SQLException {
            Category c = new Category();
            c.setName(rs.getString("name"));
            c.setId(rs.getInt("id"));
            c.setUrl(rs.getString("url"));
            c.setProductCount(rs.getInt("product_count"));
            return c;
        }
    };

    public static ResultSetHandler<Producer> RESULT_SET_HANDLER_PRODUCERS = new ResultSetHandler<Producer>() {
        @Override
        public Producer handle(ResultSet rs) throws SQLException {
            Producer pr = new Producer();
            pr.setName(rs.getString("name"));
            pr.setId(rs.getInt("id"));
            pr.setProductCount(rs.getInt("product_count"));
            return pr;
        }
    };

    public static ResultSetHandler<Account> RESULT_SET_HANDLER_ACCOUNT = new ResultSetHandler<Account>() {
        @Override
        public Account handle(ResultSet rs) throws SQLException {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setName(rs.getString("name"));
            account.setEmail(rs.getString("email"));
            return account;
        }
    };

    public static ResultSetHandler<Order> RESULT_SET_HANDLER_ORDER = new ResultSetHandler<Order>() {
        @Override
        public Order handle(ResultSet rs) throws SQLException {
            Order order = new Order();
            order.setCreated(rs.getTimestamp("created"));
            order.setIdAccount(rs.getInt("id_account"));
            order.setId(rs.getLong("id"));
            return order;
        }
    };

    public static ResultSetHandler<OrderItem> RESULT_SET_HANDLER_ORDER_ITEM = new ResultSetHandler<OrderItem>() {
        @Override
        public OrderItem handle(ResultSet rs) throws SQLException {
            OrderItem orderItem = new OrderItem();

            return orderItem;
        }
    };

    // метод получения одного значения из ResultSet
    public static <T> ResultSetHandler<T> getSingleResultSethandler (ResultSetHandler<T> oneRowResultSetHandler)  {

        return new ResultSetHandler<T>() {
            @Override
            public T handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return oneRowResultSetHandler.handle(rs);
                }else {
                    return null;
                }
            }
        };

    }
    // метод получения списка значений из ResultSet
    public static <T> ResultSetHandler<List<T>> getListResultSetHandler (ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<List<T>>() {
            @Override
            public List<T> handle(ResultSet rs) throws SQLException {
                List<T> listResulthandler = new ArrayList<>();
                while(rs.next()) {
                    listResulthandler.add(oneRowResultSetHandler.handle(rs));
                }
                return listResulthandler;
            }
        };
    }

    public static ResultSetHandler<Integer> getCountResultSet () {
        return new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
            }
        };
    }

}
