package ru.belyaev.framework.annotationJDBC;


import java.lang.annotation.*;


// annotation use when title of column from DB don't match with name of class entity
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String value();

}
