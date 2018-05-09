package jvm.loadclass;

/**
 * Created by zcy on 18-5-9.
 * 类加载过程中，可能调用了实例化过程（因为static可以修饰方法，属性，代码块，内部类），
 * 此时则会暂停类加载过程而先执行实例化过程（被打断），执行结束再进行类加载过程
 */
public class Test {
    public static int k = 0;
    public static Test t1 = new Test("t1");
    public static Test t2 = new Test("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");

    {
        print("构造块");
    }
    static {
        print("静态块");
    }

    public Test(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String args[]) {
        Test t = new Test("init");
    }
}
