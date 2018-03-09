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
 * 不能中断正在试图获取synchronied锁或者试图执行IO操作的线程
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
        test(new SynchronizedBlocked());
    }

    private static void print(String str){
        System.out.println(str);
    }

    static class SleepBlocked implements Runnable {
        @Override
        public void run(){
            try{
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e){
                print("InterruptedExcpetion");
            }
            print("Exiting SleepBlocked.run()");
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
        public synchronized void f(){
            while(true)
                Thread.yield();
        }
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
            }
            print("Exiting SynchronizedBlocked.run()");
        }
    }
}
