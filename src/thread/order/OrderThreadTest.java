package thread.order;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zcy on 18-3-28.
 * 三个线程顺序输出 ABC
 */
public class OrderThreadTest {
    private int count = 0;
    Lock lock = new ReentrantLock();

    private void executeA(){
        for(int i=0; i<10; ){//i++不能写在for中，要求在print后才可以i++
            lock.lock();
            if (count % 3 == 0){
                System.out.println("********* A");
                count ++ ;
                i++;
            }
            lock.unlock();
        }
    }

    private void executeB(){
        for(int i=0; i<10;) {
            lock.lock();
            if (count % 3 == 1) {
                System.out.println("********* B");
                count++;
                i++;
            }
            lock.unlock();
        }
    }

    private void executeC(){
        for(int i=0; i<10;) {
            lock.lock();
            if (count % 3 == 2) {
                System.out.println("********* C");
                count++;
                i++;
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
