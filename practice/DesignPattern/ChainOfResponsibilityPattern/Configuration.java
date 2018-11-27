package com.sjh.DesignPattern.ChainOfResponsibilityPattern;

/**
 * @author sjh
 * @Title: Configuration
 * @ProjectName com.sjh.DesignPattern.ChainOfResponsibilityPattern
 * @Description: TODO
 * @date 2018/11/27 16:18
 */
public class Configuration {
    private InterceptorChain interceptorChain;
    private String name;
    public Configuration(InterceptorChain interceptorChain, String name) {
        this.interceptorChain = interceptorChain;
        this.name = name;
    }
    public InterceptorChain getInterceptorChain() {
        return interceptorChain;
    }
    public String getName() {
        return name;
    }
    public static void main(String[] args) {
        Interceptor interceptor1 = new InterceptorImpl1();
        Interceptor interceptor2 = new InterceptorImpl2();
        Interceptor interceptor3 = new InterceptorImpl3();
        InterceptorChain interceptorChain = new InterceptorChain();
        interceptorChain.addInterceptor(interceptor1);
        interceptorChain.addInterceptor(interceptor2);
        interceptorChain.addInterceptor(interceptor3);
        Configuration configuration = new Configuration(interceptorChain, "张三");
        configuration.getInterceptorChain().interceptorAll(configuration.getName());
    }
}
