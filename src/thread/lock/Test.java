package thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zcy on 18-4-21.
 */
public class Test {

    public static void main(String[] args){
        AtomicInteger atomicInteger = new AtomicInteger(10);
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(11, 1));
    }
}
