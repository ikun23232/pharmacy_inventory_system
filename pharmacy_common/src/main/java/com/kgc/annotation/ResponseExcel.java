package com.kgc.annotation;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.lang.annotation.*;

/**
 *
 * @author
 * @date 2024/05/06
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseExcel {
    String name() default "";
    ExcelTypeEnum suffix() default ExcelTypeEnum.XLSX;
    String[] sheet() default {};
    boolean inMemory() default false;
    String template() default "";
    String[] include() default {};
    String[] exclude() default {};
    Class<? extends Converter>[] converter() default {};
}
