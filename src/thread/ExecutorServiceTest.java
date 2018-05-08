package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zcy on 18-3-13.
 */
public class ExecutorServiceTest {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    /**
     * 核心调用方法
     * ThreadPoolExecutor(int corePoolSize,//核心线程数，相当于线程池空闲时的最小线程数
                          int maximumPoolSize,//最大线程数，线程池的线程数量上限
                          long keepAliveTime,//当最大线程数超过核心线程数时，空闲线程的存活时间
                          TimeUnit unit,//时间单位
                          BlockingQueue<Runnable> workQueue//使用的队列
                        )
     * @param args
     */
    public static void main(String[] args) {
        //核心线程数 = 最大线程数
        ExecutorService es1 = Executors.newFixedThreadPool(10);

        //核心线程数=0, 最大线程数 = 无限
        ExecutorService es2 = Executors.newCachedThreadPool();

        //核心线程数 = 最大线程数 = 1
        ExecutorService es3 = Executors.newSingleThreadExecutor();

        ScheduledExecutorService es4 = Executors.newScheduledThreadPool(10);


        System.out.println(CAPACITY);
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);

        System.out.println(ctlOf(RUNNING, 0));

    }
}
