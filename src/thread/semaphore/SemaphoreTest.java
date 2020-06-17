package thread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by zcy on 18-4-23.
 */
public class SemaphoreTest {

    public static void main(String[] args) throws Exception{
        Semaphore s = new Semaphore(3);

        for(int i = 0 ; i<5; i++){
            s.acquire(1);
            final int fi = i;
            Thread t = new Thread(() -> run(fi));
            t.start();
        }

    }

    public static void run(int fi) {
        while(true) {
            System.out.println(fi);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {

            }
        }
    }
}
