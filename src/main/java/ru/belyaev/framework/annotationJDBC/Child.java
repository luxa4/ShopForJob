package ru.belyaev.framework.annotationJDBC;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Child {
    String ColumnName();
    String idFieldName() default "id";
}
