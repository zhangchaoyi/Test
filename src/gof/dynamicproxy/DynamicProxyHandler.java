package gof.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chris on 10/10/17.
 * obj 是实现接口的类对象，被代理对象
 * 每次方法被执行都先经过invoke
 */
public class DynamicProxyHandler implements InvocationHandler {
    private Object obj;

    public DynamicProxyHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("******* gof.proxy: " + proxy.getClass().getCanonicalName() + ", method: " + method.getName() + ", args: " + args);

        if(method.getName().equals("doSomething")){
            System.out.println("method doSomething");
        }

        if(method.getName().equals("somethingElse")){
            System.out.println("method somethingElse");
        }

        if(args!=null){
            for(Object arg : args){
                System.out.println(" " + arg);
            }
        }
        return method.invoke(obj, args);
    }

}
