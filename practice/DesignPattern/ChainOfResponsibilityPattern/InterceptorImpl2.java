package com.sjh.DesignPattern.ChainOfResponsibilityPattern;

/**
 * @author sjh
 * @Description: “拦截器”接口实现类1
 * @date 2018/11/27 16:19
 */
public class InterceptorImpl2 implements Interceptor {
    @Override
    public Object intercept(Object target) {
        System.out.println("进入拦截器2");
        String targetStr = String.valueOf(target);
        if (targetStr != null && ("王二麻子").equals(targetStr)) {
            System.out.println("目标字符串不能为‘王二麻子’");
        }
        return null;
    }
}
