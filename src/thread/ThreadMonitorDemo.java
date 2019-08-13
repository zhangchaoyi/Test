package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 使用UncaughExceptionHandler进行检测线程报错，另起一个工作线程进行跑任务
 * @author: zhangchaoyi
 * @date: 2019/8/13
 */
public class ThreadMonitorDemo {
    volatile boolean               inited      = false;
    static   int                   threadIndex = 0;
    final    BlockingQueue<String> channel     = new ArrayBlockingQueue<String>(100);

    public static void main(String[] args) throws InterruptedException{
        ThreadMonitorDemo tmd = new ThreadMonitorDemo();
        tmd.init();

        for(int i=0; i<100; i++){
            tmd.service("test-" + i);
        }

        TimeUnit.SECONDS.sleep(1011);
        System.out.println("exit.....");
        System.exit(0);
    }

    public synchronized void init(){
        if(inited){
            return;
        }
        System.out.println("init >>>>");
        WorkerThread t = new WorkerThread();
        t.setName("Worker0-" + threadIndex++);
        t.setUncaughtExceptionHandler(new ThreadMonitor());
        t.start();
        inited = true;
    }

    public void service(String message) throws InterruptedException {
        channel.put(message);
    }

    private class ThreadMonitor implements Thread.UncaughtExceptionHandler {

        @Override public void uncaughtException(Thread _t, Throwable _e) {
                System.out.println("Current Thread is " + (Thread.currentThread() == _t) + " it is still alive " + _t.isAlive());

                String threadInfo = _t.getName();
                System.out.println("threadInfo:" + threadInfo);
                _e.printStackTrace();

                System.out.println("About to restart " + threadInfo);

                inited = false;
                init();
        }
    }

    private class WorkerThread extends Thread {

        @Override public void run() {
            System.out.println("Do something important.....");

            String msg;
            try{
                for(;;){
                    msg = channel.take();
                    process(msg);
                }
            } catch(InterruptedException e){
                /**
                 * 注意上面的不能使用Exception，否则线程抛出的UncaughException 被吞掉
                 */

            }
        }
    }

    private void process(String message) {
        System.out.println(message);

        if((int) Math.random() * 100 < 2){
            throw new RuntimeException("test");
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e){

        }
    }

}
