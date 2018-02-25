package com.sjh.practice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段详情注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FiledType {
    String filedTypeName(); //字段在表中的名称
    FiledTypeEnum filedType() default FiledTypeEnum.VARCHAR; //字段类型
    int filedSize() default 255;//字段大小
    Constraints constraints() default @Constraints;//字段约束
}
