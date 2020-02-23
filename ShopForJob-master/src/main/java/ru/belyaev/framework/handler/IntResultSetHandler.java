/*
 * Created by Vologda Developer
 * Date: 07.12.2019
 * Time: 12:26
 */


package ru.belyaev.framework.handler;

import ru.belyaev.framework.convert.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntResultSetHandler implements ResultSetHandler {
    @Override
    public Integer handle(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }
}
