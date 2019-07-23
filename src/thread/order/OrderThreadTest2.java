package thread.order;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zcy on 18-3-28.
 *
 * condition 通知机制
 */
public class OrderThreadTest2 {
    private static Lock lock = new ReentrantLock();
    private static int count = 0;
    private static Condition CA = lock.newCondition();
    private static Condition CB = lock.newCondition();
    private static Condition CC = lock.newCondition();

    private static void executeA(){
        while(true){
            System.out.println("execute A count:"+count);
            lock.lock();
            if (count % 3 != 0) {
                try {
                    CA.await();
                } catch(InterruptedException e){

                }
            } else {
                System.out.println("********* A");
                CB.signal();
                count++;
            }
            lock.unlock();
        }
    }

    private static void executeB(){
        while(true){
            System.out.println("execute B count:"+count);
            lock.lock();
            if (count % 3 != 1) {
                try {
                    CB.await();
                } catch(InterruptedException e){

                }
            } else {
                System.out.println("********* B");
                CC.signal();
                count++;
            }
            lock.unlock();
        }
    }

    private static void executeC(){
        while(true){
            System.out.println("execute C count:"+count);
            lock.lock();
            if (count % 3 != 2) {
                try {
                    CC.await();
                } catch(InterruptedException e){

                }
            } else {
                System.out.println("********* C");
                CA.signal();
                count++;
                try {
                    Thread.sleep(2000);
                } catch(Exception e){

                }
            }
            lock.unlock();
        }
    }

    public static void main(String[] args){
        Thread A = new Thread(() -> executeA());
        Thread B = new Thread(() -> executeB());
        Thread C = new Thread(() -> executeC());

        A.start();
        B.start();
        C.start();
    }
}
