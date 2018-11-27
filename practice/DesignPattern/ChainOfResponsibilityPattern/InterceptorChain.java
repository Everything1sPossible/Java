package com.sjh.DesignPattern.ChainOfResponsibilityPattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author sjh
 * @Description: "责任链"
 * @date 2018/11/27 16:13
 */
public class InterceptorChain {
    private List<Interceptor> interceptors;

    public InterceptorChain() {
        this.interceptors = new ArrayList<>();
    }

    public void interceptorAll(Object target) {
        Iterator<Interceptor> iterator = this.interceptors.iterator();
        while (iterator.hasNext()) {
            Interceptor interceptor = iterator.next();
            interceptor.intercept(target);
        }
    }
    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }
    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }
    public List<Interceptor> getInterceptors() {
        return interceptors;
    }
}
