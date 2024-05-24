package com.kgc.annotation;

import java.lang.annotation.*;

/**
 *
 * @author
 * @date 2024/05/06
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {java.lang.annotation.ElementType.FIELD})
@Inherited
public @interface ExcelFiled {
    String value() default "";

    Class type() default String.class;
}

