package thread.order;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zcy on 18-3-28.
 * 三个线程顺序输出 ABC
 * 思想：三个线程加锁共享一个变量，取模运算 %
 */
public class OrderThreadTest {
    private int count = 0;
    Lock lock = new ReentrantLock();

    private void executeA(){
        while(true){
            lock.lock();
            if (count % 3 == 0){
                System.out.println("********* A count:" + count);
                count ++ ;
            }
            lock.unlock();
        }
    }

    private void executeB(){
        while(true){
            lock.lock();
            if (count % 3 == 1) {
                System.out.println("********* B count:" + count);
                count++;
            }
            lock.unlock();
        }
    }

    private void executeC(){
        while(true) {
            lock.lock();
            if (count % 3 == 2) {
                System.out.println("********* C count:" + count);
                count++;
            }
            lock.unlock();
        }
    }

    public static void main(String[] args){
        OrderThreadTest ott = new OrderThreadTest();
        Thread A = new Thread(() -> ott.executeA());
        Thread B = new Thread(() -> ott.executeB());
        Thread C = new Thread(() -> ott.executeC());

        A.start();

        B.start();

        C.start();

    }
}
