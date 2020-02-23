/*
 * Created by Vologda developer.
 * User: Alexander
 * Date: 20.12.2019
 * Time: 8:55
 */

package ru.belyaev.framework.handler;

import ru.belyaev.framework.convert.Converter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssistantSingleResultSet<T> implements ResultSetHandler<T> {

    DefaultResultSetHandler<T> defaultResultSetHandler;

    public AssistantSingleResultSet(DefaultResultSetHandler<T> defaultResultSetHandler) {
        this.defaultResultSetHandler = defaultResultSetHandler;
    }

    @Override
    public T handle(ResultSet rs) throws SQLException, IllegalAccessException, InstantiationException {
        if (rs.next()) {
            return defaultResultSetHandler.handle(rs);
        } else {
            return null;
        }

    }
}
