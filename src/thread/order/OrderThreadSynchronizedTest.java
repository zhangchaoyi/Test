package thread.order;

import java.util.concurrent.TimeUnit;

/**
 * @Author: chaoyi.zhang
 * @Date: 2021/2/23 14:56
 */
public class OrderThreadSynchronizedTest {

    private static Integer count = 0;

    private static void executeA(){
        while(true){
            synchronized (count) {
                if(count % 3 == 0){
                    System.out.println("******* A");
                    count++;
                }
            }
        }

    }

    private static void executeB(){
        while (true){
            synchronized (count) {
                if(count % 3 == 1){
                    System.out.println("******* B");
                    count++;
                }
            }
        }

    }

    private static void executeC(){
        while (true) {
            synchronized (count) {
                if(count % 3 == 2){
                    System.out.println("******* C");
                    count++;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch(Exception e){

                }
            }
        }

    }

    public static void main(String[] args){

        Thread A = new Thread(() -> executeA());
        Thread B = new Thread(() -> executeB());
        Thread C = new Thread(() -> executeC());
        A.start();
        B.start();
        C.start();
    }
}
