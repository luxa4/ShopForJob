/*
 * Created by Vologda developer.
 * User: Alexander
 * Date: 05.12.2019
 * Time: 9:32
 */

package ru.belyaev.framework.convert;

import ru.belyaev.framework.FrameworkSystemException;

public class DefaultConverter implements Converter {
    @Override
    public <T> T convert(Class<T> entityClass, Object value) {
        if (value == null) {
            return null;
        } else if (entityClass == Object.class || entityClass == value.getClass()) {
            return (T) value;
        } else if (entityClass == String.class) {
            return (T) value.toString();
        } else {
            throw new FrameworkSystemException("Can't convert " + value.getClass().getSimpleName() + " from DataBase TO " + entityClass.getSimpleName() );
        }
    }
}
