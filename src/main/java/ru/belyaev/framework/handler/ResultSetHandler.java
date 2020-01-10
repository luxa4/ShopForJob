/*
 * Created by Vologda developer.
 * User: Alexander
 * Date: 19.12.2019
 * Time: 10:01
 */

package ru.belyaev.framework.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T> {
    T handle(ResultSet rs) throws SQLException, IllegalAccessException, InstantiationException;
}
