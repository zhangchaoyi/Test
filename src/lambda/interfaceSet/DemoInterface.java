package lambda.interfaceSet;

/**
 * @description:
 * @author: zhangchaoyi
 * @date: 2018/8/9
 */
public interface DemoInterface {

    void test();

    default void additionalMethod() {
        System.out.println("java8 支持接口中添加默认方法 不影响原有实现");
    };
}
