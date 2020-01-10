/*
 * Created by Vologda developer.
 * User: Alexander
 * Date: 20.12.2019
 * Time: 8:56
 */

package ru.belyaev.framework.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssistantListResulSet<T> implements ResultSetHandler<List<T>> {

    ResultSetHandler<T> defaultResultSetHandler;

    public AssistantListResulSet(ResultSetHandler<T> defaultResultSetHandler) {
        this.defaultResultSetHandler = defaultResultSetHandler;
    }

    public AssistantListResulSet(Class<T> entityClass) {
        this.defaultResultSetHandler = new DefaultResultSetHandler<>(entityClass);
    }

    @Override
    public List<T> handle(ResultSet rs) throws SQLException, IllegalAccessException, InstantiationException {
        List<T> result = new ArrayList<>();
        while(rs.next()) {
            result.add(defaultResultSetHandler.handle(rs));
        }
        return result;
    }
}
