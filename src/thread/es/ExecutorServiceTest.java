package thread.es;

import java.util.concurrent.*;
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
//        //核心线程数 = 最大线程数
//        ExecutorService es1 = Executors.newFixedThreadPool(10);
//
//        //核心线程数=0, 最大线程数 = 无限
//        ExecutorService es2 = Executors.newCachedThreadPool();
//
//        //核心线程数 = 最大线程数 = 1
//        ExecutorService es3 = Executors.newSingleThreadExecutor();
//
//        ScheduledExecutorService es4 = Executors.newScheduledThreadPool(10);
//
//
//        System.out.println(CAPACITY);
//        System.out.println(RUNNING);
//        System.out.println(SHUTDOWN);
//        System.out.println(STOP);
//        System.out.println(TIDYING);
//        System.out.println(TERMINATED);
//
//        System.out.println(ctlOf(RUNNING, 0));

        /**
         * 测试当rejectHandler为new ThreadPoolExecutor.CallerRunsPolicy()时
         * 会使用当前的线程来运行run方法而非ThreadPoolExecutor
         */
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1), new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i=0; i<10; i++){
            Runnable testRun = new TestHH();
            tpe.execute(testRun);
        }
    }

    public static class TestHH implements Runnable{

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName());
        }


    }
}
