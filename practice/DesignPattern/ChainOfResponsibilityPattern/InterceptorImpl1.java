package com.sjh.DesignPattern.ChainOfResponsibilityPattern;

/**
 * @author sjh
 * @Description: “拦截器”接口实现类1
 * @date 2018/11/27 16:19
 */
public class InterceptorImpl1 implements Interceptor {
    @Override
    public Object intercept(Object target) {
        System.out.println("进入拦截器1");
        String targetStr = String.valueOf(target);
        if (targetStr != null && ("张三").equals(targetStr)) {
            System.out.println("目标字符串不能为‘张三’");
        }
        return null;
    }
}
