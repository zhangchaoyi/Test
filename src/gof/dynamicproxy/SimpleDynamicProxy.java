package gof.dynamicproxy;

import gof.proxy.Interface;
import gof.proxy.RealObject;

import java.lang.reflect.Proxy;

/**
 * Created by chris on 10/11/17.
 * JDK 动态代理
 * Proxy.newProxyInstance(interface.class.getClassLoader(), new Class[]{interface.class}, 实现了InvocationHandler的类对象)  创建一个代理
 */
public class SimpleDynamicProxy {

    public static void main(String[] args){
        RealObject ro = new RealObject();
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(ro));
        proxy.doSomething();

        proxy.somethingElse("hello");
    }
}
