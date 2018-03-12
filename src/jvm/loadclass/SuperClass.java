package jvm.loadclass;

/**
 * Created by chris on 3/10/18.
 * 被动引用
 *
 */
public class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}
