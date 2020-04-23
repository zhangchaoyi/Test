package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LambdaTest {

    public static <T> void chainsConsumerExecutor(T t, List<Consumer<T>> functions) {
        Optional.ofNullable(functions).ifPresent(items -> items.forEach(consumer -> consumer.accept(t)));
    }

    public static void main(String[] args) {
//        LambdaTest lt = new LambdaTest();
//        chainsConsumerExecutor("11", Arrays.asList(lt::test1, lt::test2));

        List<String> stringList = Arrays.asList("abbb", "baaa", "csss");
        List<String> result = stringList.stream().collect(ArrayList::new, (z, y) -> z.add(y.substring(0,1)), ArrayList::addAll);
        System.out.println(result);
    }

    public void test1(String a){
        System.out.println(a);
    }

    public void test2(String b){
        System.out.println(b);
    }
}
