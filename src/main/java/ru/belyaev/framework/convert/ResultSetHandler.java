/*
 * Created by Vologda Developer
 * Date: 07.12.2019
 * Time: 12:22
 */

package ru.belyaev.framework.convert;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T> {
    T handle(ResultSet rs) throws SQLException, IllegalAccessException, InstantiationException;
}
