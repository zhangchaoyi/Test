package thread;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by chris on 3/4/18.
 * 中断测试
 * 不能中断正在试图获取synchronied锁 或者 已经获得锁 或者试图执行IO操作的线程
 *
 * 目标线程被中断，如果当前目标线程正在running，则它的interrupted状态被设置为 true
 * 如果当前目标线程正处于可以响应中断的阻塞状态(如 sleep()/lockInterruptibly())，则抛出一个InterruptedException
 * 如果当前目标线程正处于不响应中断的阻塞状态（如lock/synchronized/Inputstream.read()）, 则不响应
 */
public class Interrupting {
    private static ExecutorService es = Executors.newCachedThreadPool();
    static void test(Runnable r) throws InterruptedException {
        Future<?> f = es.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        print("Interrupting " + r.getClass().getName());
        f.cancel(true);
        print("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws Exception{
//        test (new SleepBlocked());
//        test(new IOBlocked(System.in));
//        test(new SynchronizedBlocked());
        Thread t = new Thread(new SleepBlocked());
        t.start();
        TimeUnit.SECONDS.sleep(2);
        t.interrupt();
    }

    private static void print(String str){
        System.out.println(str);
    }

    static class SleepBlocked implements Runnable {
        @Override
        public void run(){
            print("Exiting SleepBlocked.run()");
            try{
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e){
                print("InterruptedExcpetion");
            }
        }
    }

    static class IOBlocked implements Runnable{
        private InputStream in;
        public IOBlocked(InputStream in){
            this.in = in;
        }
        @Override
        public void run(){
            try{
               print("Waiting for read()");
               in.read();
            }catch(IOException e){
                if(Thread.currentThread().isInterrupted()){
                   print("Interrupted from blocked IO");
                } else {
                    throw new RuntimeException(e);
                }
            }
            print("Exiting IOBlocked.run()");
        }
    }


    static class SynchronizedBlocked implements Runnable {
        /**
         * 进行锁控制，如果下面构造方法没有注释，则代表试图获取锁等待锁场景；如果下面构造方法注释，则代表获取锁场景
         */
        public synchronized void f(){
            System.out.println("call yield(), curThread:" + Thread.currentThread().getName());
            while(true)
                Thread.yield();//yield不会释放锁
        }

        //开启一个线程占有锁
        public SynchronizedBlocked(){
            new Thread(() -> {
                f();
            }).start();
        }

        @Override
        public void run(){
            while(!Thread.currentThread().isInterrupted()){
                print("Trying to call f()");
                f();
                System.out.println("running");
            }
            print("Exiting SynchronizedBlocked.run()");
        }
    }
}
