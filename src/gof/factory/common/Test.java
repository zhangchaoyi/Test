package gof.factory.common;

/**
 * Created by zcy on 18-6-5.
 * 一类工厂生产一类产品 只有一个Factory接口 一个Product接口
 * 工厂1专门生产产品1
 * 工厂2专门生产产品2
 */
public class Test {

    public static void main(String[] args){
        Product p1 = new Factory1().createProduct();
        Product p2 = new Factory2().createProduct();

        p1.show();
        p2.show();
    }
}
