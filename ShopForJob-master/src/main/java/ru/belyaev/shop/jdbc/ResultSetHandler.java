// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:38

package ru.belyaev.shop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T> {

    T handle(ResultSet rs) throws SQLException;

}
