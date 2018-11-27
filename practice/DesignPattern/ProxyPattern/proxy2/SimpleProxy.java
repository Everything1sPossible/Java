package com.sjh.DesignPattern.ProxyPattern.proxy2;

/**
 * @author sjh
 * @Title: SimpleProxy
 * @ProjectName com.sjh.demo.proxy2
 * @Description: TODO
 * @date 2018/11/11 18:54
 */
public interface SimpleProxy {
    Object getProxy(ClassLoader classLoader, Class[] interfaces);
}
