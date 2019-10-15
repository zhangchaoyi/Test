package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by zcy on 18-3-13.
 * 需要在run()方法中加 isInterrupted() 进行判断, 因为调用线程对象的interrupted()方法相当于修改该线程的中断标记为true
 *
 * thread obj.interrupted()中断线程，设置线程的中断标记为true
 * thread obj.isInterrupted()进行查询线程的中断标记位
 * Thread.interrupted()用于获取线程的中断标记位并重置标记位为false
 * Thread.currentThread().isInterrupted()用于获取main线程中断标记位
 * Thread.currentThread().interrupted()用于中断main线程
 *
 * 目标线程被中断，如果当前目标线程正在running，则它的interrupted状态被设置为 true
 * 如果当前目标线程正处于可以响应中断的阻塞状态(如 sleep()/lockInterruptibly())，则抛出一个InterruptedException
 * 如果当前目标线程正处于不响应中断的阻塞状态（如lock/synchronized/Inputstream.read()）, 则不响应
 *
 */
public class InterruputThread {
    public static void main(String[] args) throws InterruptedException {
            Thread t1 = new MyThread();
            t1.start();
            TimeUnit.SECONDS.sleep(2);
            t1.interrupt();

        /**
         * 输出结果(无限执行):
         未被中断
         未被中断
         未被中断
         ......
         */
    }

    public static class MyThread extends Thread {

        @Override public void run() {
            while(true){
                if(this.isInterrupted()) {//如果这个判断使用this.isInterrupted()，则下面的逻辑中需要加上Thread.interrupted()
                    System.out.println("线程中断");
//                    System.out.println("第一次打印thread中断" + Thread.interrupted());//此操作相当于重置当前线程中断标志
                    System.out.println("第一次打印thread中断" + this.isInterrupted());
                    break;
                }
                System.out.println("未被中断");
            }
            System.out.println("退出run方法");
        }
    }

    public static void test2(){
        Thread myThread = new Thread(() -> {while(true){}});
        myThread.start();
        myThread.interrupt();
        System.out.println("第一次调用:" +Thread.interrupted());//false 实际上查询的是currentThread （main）的状态，因为main的线程不是中断状态
        System.out.println("第二次调用:" +Thread.interrupted());//false
    }

    public static void test3(){
        Thread myThread = new Thread(() -> {while(true){}});
        myThread.start();
        myThread.interrupt();
        Thread.currentThread().interrupt();//此步触发了main的中断
        System.out.println("第一次调用:" +Thread.interrupted());//true  main线程被中断了，所以返回true，并且重置为false
        System.out.println("第二次调用:" +Thread.interrupted());//false  因为上一步被重置了
    }

    public static void test4(){
        Thread myThread = new Thread(() -> {while(true){}});
        myThread.start();
        myThread.interrupt();
        System.out.println("第一次调用:" +myThread.isInterrupted());//true
        System.out.println("第二次调用:" +myThread.isInterrupted());//true
    }

    public static void test5(){
        Thread myThread = new Thread(() -> {while(true){}});
        myThread.start();
        myThread.interrupt();
        System.out.println("第一次调用:" +Thread.currentThread().isInterrupted());//false  获取main线程的中断标记
        System.out.println("第二次调用:" +Thread.currentThread().isInterrupted());//false
    }

    public static void test6(){
        Thread myThread = new Thread(() -> {while(true){}});
        myThread.start();
        Thread.currentThread().interrupt();//此步触发了main的中断
        System.out.println("第一次调用:" + Thread.currentThread().isInterrupted());//true
        System.out.println("第二次调用:" + Thread.currentThread().isInterrupted());//true
    }
}
