/*
 * Created by Vologda developer.
 * User: Alexander
 * Date: 05.12.2019
 * Time: 9:23
 */

package ru.belyaev.framework.utils;

import ru.belyaev.framework.FrameworkSystemException;
import ru.belyaev.framework.annotationJDBC.Column;
import ru.belyaev.framework.annotationJDBC.Transient;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
    public static List<Field> getAccessibleFields(Class<?> entityClass) {
        List<Field> fields = new ArrayList<>();
        while (entityClass != Object.class) {
            for (Field field: entityClass.getDeclaredFields()) {
                if (fieldShouldBeIncluded(field)) {
                field.setAccessible(true);
                fields.add(field);
                }
            }
            entityClass = entityClass.getSuperclass();
        }
        return fields;
    }

    private static boolean fieldShouldBeIncluded(Field field) {
        int modifiers = field.getModifiers();
        return (modifiers & (Modifier.STATIC | Modifier.FINAL)) == 0 && field.getAnnotation(Transient.class) == null;
    }

    public static Field findField (Class<?> fieldClass ,List<Field> fields, String fieldName) {
        for (Field field: fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        throw new FrameworkSystemException("Field not found in class " + fieldClass);
    }

    public static String getColumnNameForDB(Field field) {
        Column columnAnnotation = field.getAnnotation(Column.class);
        if (columnAnnotation !=null) {
            return columnAnnotation.value();
        } else {
            return field.getName();
        }
    }

}
