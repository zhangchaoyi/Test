package jvm.loadclass;

/**
 * Created by chris on 3/10/18.
 */
public class ConstClass {

    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLOWORLD = "hello world";
}
