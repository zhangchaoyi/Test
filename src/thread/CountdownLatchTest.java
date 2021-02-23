package thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: chaoyi.zhang
 * @Date: 2021/2/23 15:45
 * 线程A B C D 对4个数统计 ， 线程5做聚合
 */
public class CountdownLatchTest {

    private static int numA = 0;
    private static int numB = 0;
    private static int numC = 0;
    private static int numD = 0;

    static CountDownLatch cdl = new CountDownLatch(5);

    private static void executeA(){
        for(int i=0;i<100;i++){
            numA++;
        }
        cdl.countDown();
    }
    private static void executeB(){
        for(int i=0;i<100;i++){
            numB++;
        }
        cdl.countDown();
    }
    private static void executeC(){
        for(int i=0;i<100;i++){
            numC++;
        }
        cdl.countDown();
    }
    private static void executeD(){
        for(int i=0;i<100;i++){
            numD++;
        }
        cdl.countDown();
    }



    public static void main(String[] args){
        Thread A = new Thread(() -> executeA());
        Thread B = new Thread(() -> executeA());
        Thread C = new Thread(() -> executeA());
        Thread D = new Thread(() -> executeA());
        A.start();
        B.start();
        C.start();
        D.start();
        try {
            cdl.await();
        } catch (Exception e) {

        }


        System.out.println(numA + numB + numC + numD);
    }
}
