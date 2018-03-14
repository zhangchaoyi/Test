package queue;

import java.util.concurrent.TimeUnit;

/**
 * Created by chris on 3/14/18.
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueueSingleThread delayQueueSingleThread = new DelayQueueSingleThread();

        Runnable r = () -> {
            System.out.println("AAA:" + System.currentTimeMillis());
        };

        delayQueueSingleThread.addTask(10, r, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(60);

        delayQueueSingleThread.addTask(10, r, TimeUnit.SECONDS);

        delayQueueSingleThread.stop();
        TimeUnit.SECONDS.sleep(60);
        delayQueueSingleThread.addTask(10, r, TimeUnit.SECONDS);
        System.out.println("delayQueue num:" + delayQueueSingleThread.getDelayQueueNum() + "daemon isAlive:" + delayQueueSingleThread.isDaemonAlive());

        while (true) ;
//        TimeUnit.MINUTES.sleep(5);
    }
}
