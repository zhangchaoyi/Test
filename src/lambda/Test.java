package lambda;

/**
 * Created by zcy on 18-3-13.
 * 传方法引用
 */
public class Test {

    @FunctionalInterface
    public static interface TInterface {

        void interfaceTest1(int i);

//        void interfaceTest2(); //如果开启该注释则需要先去除@FunctionalInterface注解，在lambda中需要new Runnable
    }

    public static void test(TInterface tInterface){
        for(int i=0;i<100; i++){
            tInterface.interfaceTest1(i);
        }

    }

    public static void main(String[] args){
        test((i) -> System.out.println(i));
    }


}
