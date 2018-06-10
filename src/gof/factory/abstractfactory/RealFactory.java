package gof.factory.abstractfactory;

/**
 * Created by zcy on 18-6-5.
 */
public class RealFactory implements Factory {

    @Override
    public IProduct1 createProduct1() {
        return new Product1();
    }

    @Override
    public Product2 createProduct2() {
        return new Product2();
    }
}
