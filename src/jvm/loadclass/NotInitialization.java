package jvm.loadclass;

/**
 * Created by chris on 3/10/18.
 *
 */
public class NotInitialization {

    public static void main(String[] args) {
        //通过子类引用父类的静态字段 不会导致子类的初始化
//        System.out.println(SubClass.value);
        //通过数组定义来引用类不会触发初始化
//        SuperClass[] sca = new SuperClass[10];
        //常量(static final) 在编译期就存入调用类的常量池，不会发生初始化
        System.out.println(ConstClass.HELLOWORLD);

    }
}
