/*
 * Created by Vologda Developer
 * Date: 05.12.2019
 * Time: 22:48
 */


package ru.belyaev.framework.util;

import ru.belyaev.framework.FrameworkSystemException;
import ru.belyaev.framework.annotation.Column;
import ru.belyaev.framework.annotation.Transient;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
    public static List<Field> getAccessibleEntityFields (Class<?> entityClass) {
        List<Field> res = new ArrayList<>();
        while (entityClass != Object.class) {
            for (Field field: entityClass.getDeclaredFields()) {
                if(shouldFieldBeIncluded(field)) {
                    field.setAccessible(true);
                    res.add(field);
                }
            }
            entityClass = entityClass.getSuperclass(); // сущностью становиться класс родитель
        }
        return res;
    }


    private static boolean shouldFieldBeIncluded(Field field) {
        int modifiers = field.getModifiers();
        return (modifiers & (Modifier.STATIC | Modifier.FINAL)) == 0 && field.getAnnotation(Transient.class) == null;
    }


    public static Field findField(Class<?> fieldClass, List<Field> fields, String fieldName) {
        for (Field field: fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        throw new FrameworkSystemException("Can't find this field" + fieldName);
    }

    public static String getColumnNameForField(Field field) {
        Column column = field.getAnnotation(Column.class);
        if (column != null) {
            return column.value();
        } else {
            return field.getName();
        }
    }

}
