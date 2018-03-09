package gof.factory;

/**
 * Created by chris on 10/15/17.
 * 简单工厂模式
 */
public class SimpleFactory {

    public static FactortInterface newInstance(String type){
        switch(type){
            case "A":
                return new ComponentA();
            case "B":
                return new ComponentB();
        }

        return null;
    }

    public static void main(String[] args){
        FactortInterface fi = SimpleFactory.newInstance("A");
        fi.doSomething();

        System.out.println("----------------------------------------------------------");

        FactortInterface fib = SimpleFactory.newInstance("B");
        fib.doSomething();
    }
}
