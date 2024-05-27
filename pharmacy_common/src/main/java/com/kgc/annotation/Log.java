package com.kgc.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author Tellsea
 * @date 2022/12/15
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 操作描述
     */
    String value();
}
