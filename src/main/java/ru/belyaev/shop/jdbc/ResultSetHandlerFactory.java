// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:43

package ru.belyaev.shop.jdbc;

import ru.belyaev.shop.entity.Category;
import ru.belyaev.shop.entity.Producer;
import ru.belyaev.shop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetHandlerFactory  {

    // создани объекта типа конструктора
    public static ResultSetHandler<Product> RESULT_SET_HANDLER_PODUCT = new ResultSetHandler<Product>() {
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
