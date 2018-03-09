package gof.proxy;

/**
 * Created by chris on 10/10/17.
 * 代理类
 */
public class Proxy implements Interface {
    private Interface proxy;

    public Proxy(Interface proxy){
        this.proxy = proxy;
    }

    @Override
    public void doSomething(){
        System.out.println("gof.proxy doSomething");
        proxy.doSomething();
    }

    @Override
    public void somethingElse(String arg){

    }
}
