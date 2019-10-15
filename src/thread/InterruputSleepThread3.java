package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by zcy on 18-3-13.
 * 每个线程都有一个interrupted状态标志位，如果线程响应了中断会把interrupted设置为true，同时抛出InterruptedException
 *
 * 如果执行 t.interrupt(),会抛出InterruptedException，同时interrupt标志位复位
 * 解释：这是因为在sleep()中已经实现了检测线程中断机制，即检测
 *  if(isInterrupted()){
 *      Thread.interrupted();//进行获取线程中断并且重置中断标记位为false
 *      throw new InterruptedException();//往上一层抛出异常
 *  }
 *
 *  通常在抛出InterruptedException()前，会进行重置线程的中断标记位的操作
 */
public class InterruputSleepThread3 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //while在try中，通过异常中断就可以退出run循环
                try {
                    while (true) {
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interruted When Sleep");
                    boolean interrupt = this.isInterrupted();
                    //中断状态在抛出exception时被复位
                    System.out.println("interrupt:"+interrupt);
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断处于阻塞状态的线程
        t1.interrupt();

        /**
         * 输出结果:
         Interruted When Sleep
         interrupt:false
         */
    }
}
