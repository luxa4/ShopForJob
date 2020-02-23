/*
 * Created by Vologda developer.
 * User: Alexander
 * Date: 20.12.2019
 * Time: 8:56
 */

package ru.belyaev.framework.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssistantCountResultSet implements ResultSetHandler<Integer> {

    @Override
    public Integer handle(ResultSet rs) throws SQLException, IllegalAccessException, InstantiationException {
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return null;
        }
    }
}
