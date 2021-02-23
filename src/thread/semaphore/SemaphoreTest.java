package thread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by zcy on 18-4-23.
 */
public class SemaphoreTest {

    private Semaphore s = new Semaphore(1);

    public static void main(String[] args) throws Exception{
        SemaphoreTest st = new SemaphoreTest();

        for(int i = 0 ; i<1; i++){
            final int fi = i;
            Thread t = new Thread(() -> st.run(fi));
            t.start();
        }

    }

    public void run(int fi) {
        while(true) {
            try {
                s.acquire();//向 s 进行申请一个许可，如果在下方没有释放则无法获取
                System.out.println(fi);
                TimeUnit.SECONDS.sleep(1);
                s.release();
            } catch(Exception e){

            }
        }
    }
}
