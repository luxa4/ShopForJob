//// Created by Vologda developer.
//// Date: 16.10.2019
//// Time: 12:30
//
//package ru.belyaev.shop.jdbc;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//public class JDBCUtil {
//    public static <T> T select (Connection c, String sql, ResultSetHandler<T> resultSetHandler, Object ... parameters) throws SQLException {
//        PreparedStatement ps = c.prepareStatement(sql);
//        pasteInPrepareStatement(ps,parameters);
//        ResultSet rs = ps.executeQuery();
//        return resultSetHandler.handle(rs);
//    }
//
//
//    public static <T> T insert (Connection c, String sql, ResultSetHandler<T> resultSetHandler, Object ... parmeters) throws SQLException {
//        try(PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//            pasteInPrepareStatement(ps, parmeters);
//            int result = ps.executeUpdate();
//            if (result != 1) {
//                throw new SQLException("Can't insert row to dataBase. Result: " + result);
//            }
//            ResultSet rs = ps.getGeneratedKeys(); // Возвращает список полей, где генерировались ключи id (order_seq)
//            return resultSetHandler.handle(rs);
//        }
//    }
//
//    public static void insertBatch (Connection c, String sql, List<Object[]> parameterList) throws SQLException {
//        try (PreparedStatement ps = c.prepareStatement(sql)) {
//            for (Object[] parameters : parameterList) {
//                pasteInPrepareStatement(ps, parameters);
//                ps.addBatch();
//            }
//            ps.executeBatch();
//        }
//    }
//
//
//    public static void pasteInPrepareStatement (PreparedStatement ps, Object ... parameters) throws SQLException {
//      if (parameters != null) {
//          for (int i = 0; i < parameters.length; i++) {
//              ps.setObject(i + 1, parameters[i]);
//          }
//      }
//    }
//
//    public static void pasteInSqlAndParams(StringBuilder sql, List<Object> params ,List<Integer> list , String expression) {
//           if (list != null && !list.isEmpty()) {
//               sql.append(" and (" );
//               for (int i = 0; i < list.size(); i++) {
//                   sql.append(expression);
//                   params.add(list.get(i));
//                   if ( i !=  list.size()-1) {
//                       sql.append(" or ");
//                   }
//
//               }
//               sql.append(")");
//
//           }
//    }
//
//}
