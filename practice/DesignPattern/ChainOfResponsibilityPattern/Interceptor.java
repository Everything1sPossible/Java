package com.sjh.DesignPattern.ChainOfResponsibilityPattern;

/**
 * @author sjh
 * @Description: “拦截器”接口
 * @date 2018/11/27 16:14
 */
public interface Interceptor {
    Object intercept(Object target);
}
