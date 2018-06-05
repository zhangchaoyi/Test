package gof.factory.common;

/**
 * Created by zcy on 18-6-5.
 */
public class Factory1 implements Factory{

    @Override
    public Product createProduct() {
        return new Product1();
    }
}
