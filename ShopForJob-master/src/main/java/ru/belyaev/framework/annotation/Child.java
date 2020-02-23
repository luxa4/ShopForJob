package ru.belyaev.framework.annotation;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Child {
    String columnName();

    String idFieldName() default "id";
}
