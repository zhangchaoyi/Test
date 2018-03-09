package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by chris on 3/3/18.
 * 设置为daemon线程后 finally 中的代码不一定会执行
 */
public class Daemon {

    public static void main(String[] args){
        Thread thread = new Thread(new DaemonRunner(), "daemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run(){
            try {
                System.out.println("start");
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e){

            } finally {
                System.out.println("sdfsdfsdfsdfsdf");
            }
        }
    }
}
