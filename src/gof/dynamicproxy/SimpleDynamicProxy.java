package gof.dynamicproxy;

import gof.proxy.Interface;
import gof.proxy.RealObject;

import java.lang.reflect.Proxy;

/**
 * Created by chris on 10/11/17.
 */
public class SimpleDynamicProxy {

    public static void main(String[] args){
        RealObject ro = new RealObject();
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(ro));
        proxy.doSomething();

        proxy.somethingElse("hello");
    }
}
