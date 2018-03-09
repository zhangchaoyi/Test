package gof.proxy;

/**
 * Created by chris on 10/10/17.
 */
public class ProxyDemo {

    public static void main(String[] args){
        RealObject ro = new RealObject();
        ro.doSomething();

        System.out.println("******************************************************************");

        Proxy p = new Proxy(ro);
        p.doSomething();
    }
}
