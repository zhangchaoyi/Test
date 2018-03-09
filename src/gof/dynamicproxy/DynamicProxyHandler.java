package gof.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chris on 10/10/17.
 */
public class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("******* gof.proxy: " + proxy.getClass() + ", method: " + method + ", args: " + args);
        if(args!=null){
            for(Object arg : args){
                System.out.println(" " + arg);
            }
        }
        return method.invoke(proxied, args);
    }

}
