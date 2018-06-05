package gof.factory.abstractfactory;

/**
 * Created by zcy on 18-6-5.
 * 抽象工厂模式
 * 一个工厂生成多类产品 Factory接口定义生产多类产品 ， 有多类Product接口
 */
public class Test {

    public static void main(String[] args){
        RealFactory realFactory = new RealFactory();
        IProduct1 p1 = realFactory.createProduct1();
        IProduct2 p2 = realFactory.createProduct2();

        p1.show();
        p2.show();
    }
}
