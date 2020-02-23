package ru.belyaev.framework.annotationJDBC;

import java.lang.annotation.*;


// annotation to avoid including this field

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Transient {
}
