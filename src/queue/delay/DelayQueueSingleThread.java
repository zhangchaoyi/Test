package queue.delay;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zcy on 18-3-14.
 * 消费延迟队列的线程
 */
public class DelayQueueSingleThread {

    private DelayQueue<DelayTask<Runnable>> delayQueue = new DelayQueue<>();

    private final Thread daemon;

    private volatile boolean stop = false;

    private static final ExecutorService ES_POOL = Executors.newSingleThreadExecutor();

    public DelayQueueSingleThread(){
         daemon = new Thread(() -> execute());
         daemon.setDaemon(true);
         daemon.setName("daemon single delayQueue handler thread");
         daemon.start();
    }

    private void execute(){
        while(!stop){
            try {
                System.out.println("daemon running");
                DelayTask dt = delayQueue.take();//api已加锁  阻塞式获取，直到下一个元素可以获取
                Runnable r = dt.getTask();
                if(r == null){
                    continue;
                }
                ES_POOL.execute(r);
            } catch (Exception e){
                stop = true;
                System.out.println("unknown exception:" + e);
            }
        }
    }

    public void addTask(long timeout, Runnable runnable, TimeUnit timeUnit){
        DelayTask<Runnable> delayTask = new DelayTask<>(runnable, timeout, timeUnit);
        addTask(delayTask);
    }

    public void addTask(DelayTask<Runnable> delayTask){
        System.out.println("add delayTask to DelayQueue");
        delayQueue.add(delayTask);//api 已加锁
    }

    public void stop(){
        this.stop = true;
    }

    public boolean isDaemonAlive(){
        return daemon.isAlive();
    }

    public int getDelayQueueNum(){
        return delayQueue.size();
    }
}
