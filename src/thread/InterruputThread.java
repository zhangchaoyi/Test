package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by zcy on 18-3-13.
 * 对于非阻塞状态的线程 无法使用t.interrupt()进行中断
 * 需要在run()方法中加 isInterrupted() 进行判断
 */
public class InterruputThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(){
            @Override
            public void run(){
                while(true){
//                    if(this.isInterrupted()) {
//                        System.out.println("线程中断");
//                        break;
//                    }
                    System.out.println("未被中断");
                }
            }
        };
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
}
