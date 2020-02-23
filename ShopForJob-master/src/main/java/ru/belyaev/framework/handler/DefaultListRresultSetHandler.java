/*
 * Created by Vologda Developer
 * Date: 07.12.2019
 * Time: 12:25
 */


package ru.belyaev.framework.handler;

import ru.belyaev.framework.convert.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultListRresultSetHandler<T> implements ResultSetHandler {

    ResultSetHandler<T> oneRowResultSetHandler;

    @Override
    public List<T> handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            list.add(oneRowResultSetHandler.handle(rs));
        }
        return list;

    }
}
