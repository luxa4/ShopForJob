/*
 * Created by Vologda developer.
 * User: Alexander
 * Date: 05.12.2019
 * Time: 9:37
 */

package ru.belyaev.framework.handler;

import ru.belyaev.framework.FrameworkSystemException;
import ru.belyaev.framework.annotationJDBC.Child;
import ru.belyaev.framework.convert.Converter;
import ru.belyaev.framework.convert.DefaultConverter;
import ru.belyaev.framework.utils.ReflectionUtils;


import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultResultSetHandler<T> implements ResultSetHandler<T> {

    protected final Class<T> entityClass;
    protected final Converter converter;

    public DefaultResultSetHandler(Class<T> entityClass, Converter converter) {
        this.entityClass = entityClass;
        this.converter = converter;
    }

    public DefaultResultSetHandler(Class<T> entityClass) {
        this(entityClass, new DefaultConverter());
    }


    @Override
    public T handle(ResultSet rs) throws SQLException {
        try {
            T entity = entityClass.newInstance();
            List<Field> entityField = ReflectionUtils.getAccessibleFields(entityClass);
            List<String> column = getAllColumns(rs);
            populateFields(entityField, entity, column, rs);
            return entity;
        } catch (InstantiationException e) {
            throw new FrameworkSystemException("");
        } catch (IllegalAccessException e) {
            throw new FrameworkSystemException("");
        }

    }

    List<String> getAllColumns(ResultSet rs) throws SQLException {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < rs.getMetaData().getColumnCount();i++) {
            res.add(rs.getMetaData().getColumnLabel(i + 1));
        }
        return res;
    }


    protected void populateFields(List<Field> fields, Object entity, List<String> columns, ResultSet rs) throws InstantiationException, IllegalAccessException, SQLException {
           for (Field field: fields) {
               Class<?> fieldClass = field.getType(); // возвращает тип поля - String, int, double
               Child child = field.getAnnotation(Child.class); // получаем ссылку на анотацию Child, если она есть
               if (child != null) {
                   populateChildFields(child, fieldClass, field, entity, rs, columns);
               } else {
                   populateSimpleField(fieldClass, field, entity, rs, columns);
               }
           }
    }

    protected void populateChildFields (Child child, Class<?> filedClass, Field field, Object entity, ResultSet rs, List<String> columns) throws IllegalAccessException, InstantiationException, SQLException {
        Object embeddedInstance = filedClass.newInstance();
        field.set(entity, embeddedInstance);
        List<Field> embeddedInstanceFields = ReflectionUtils.getAccessibleFields(filedClass);
        populateFields(embeddedInstanceFields, embeddedInstance, columns, rs);
        Field idField = ReflectionUtils.findField(filedClass, embeddedInstanceFields, child.idFieldName());
        idField.set(embeddedInstance, rs.getObject(child.ColumnName()));
    }

    protected void populateSimpleField (Class<?> filedClass, Field field, Object entity, ResultSet rs, List<String> columns) throws SQLException, IllegalAccessException {
        String columnName = ReflectionUtils.getColumnNameForDB(field);
        if (columns.contains(columnName)) {
            Object valueFromDB = rs.getObject(columnName);
            Object convertedValueFromDB = converter.convert(filedClass, valueFromDB);
            field.set(entity, convertedValueFromDB);
        }
    }

}
