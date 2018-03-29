package thread.order;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zcy on 18-3-28.
 */
public class OrderThreadTest2 {
    private static Lock lock = new ReentrantLock();
    private static int count = 0;
    private static Condition CA = lock.newCondition();
    private static Condition CB = lock.newCondition();
    private static Condition CC = lock.newCondition();

    private static void executeA(){
        lock.lock();
        for(int i=0; i<10; i++){
            while (count % 3 != 0){
                try {
                    CA.await();//释放lock
                } catch (InterruptedException e){

                }
            }
            System.out.println("********* A");
            count ++ ;
            CB.signal();
        }
        lock.unlock();
    }

    private static void executeB(){
        lock.lock();
        for(int i=0; i<10; i++){
            while (count % 3 != 1){
                try {
                    CB.await();
                } catch (InterruptedException e){

                }
            }
            System.out.println("********* B");
            count ++ ;
            CC.signal();
        }
        lock.unlock();
    }

    private static void executeC(){
        lock.lock();
        for(int i=0; i<10; i++){
            while (count % 3 != 2){
                try {
                    CC.await();
                } catch (InterruptedException e){

                }
            }
            System.out.println("********* C");
            count ++ ;
            CA.signal();
        }
        lock.unlock();
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
