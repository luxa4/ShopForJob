/*
 * Created by Vologda developer.
 * User: Alexander
 * Date: 05.12.2019
 * Time: 9:32
 */

package ru.belyaev.framework.convert;

public interface Converter {
    <T> T convert(Class<T> entityClass, Object value);
}
