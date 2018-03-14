package gof.proxy;

/**
 * Created by chris on 10/10/17.
 * 真正类
 */
public class RealObject implements Interface {

    @Override
    public void doSomething(){
        System.out.println("real object");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("do something else " + arg);
    }
}
