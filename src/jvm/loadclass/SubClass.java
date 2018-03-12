package jvm.loadclass;

/**
 * Created by chris on 3/10/18.
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init!");
    }

    //不能使用main，否则就初始化 SubClass
//    public static void main(String[] args){
//        System.out.println(SubClass.class);
//    }
}
