/*
 * Created by Vologda Developer
 * Date: 07.12.2019
 * Time: 12:24
 */


package ru.belyaev.framework.handler;

import ru.belyaev.framework.convert.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultUniqueResultSetHandler<T> implements ResultSetHandler<T> {

    ResultSetHandler<T> oneRowResultSethandler;

    public DefaultUniqueResultSetHandler(ResultSetHandler oneRowResultSethandler) {
        this.oneRowResultSethandler = oneRowResultSethandler;
    }

    @Override
    public T handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        if (rs.next()) {
            return oneRowResultSethandler.handle(rs);
        } else {
            return null;
        }
    }
}
