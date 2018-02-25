package com.sjh.practice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段约束注解,作用于字段
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Constraints {

    boolean primaryKey() default false;//主键约束
    boolean notNull() default true;//是否为空
    boolean unique() default false;//唯一约束

}
