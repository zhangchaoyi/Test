package thread.order;

/**
 * @description: wait()/notify()机制
 * @author: zhangchaoyi
 * @date: 2019/7/23
 */
public class OrderThreadTest4 {

    private static Object objLock = new Object();
    private static int count = 0;

    private static void executeA(){
        while(true){
            System.out.println("execute A count:"+count);
            synchronized(objLock){
                if (count % 3 != 0) {
                    try {
                        objLock.wait();//挂起当前线程
                    } catch(InterruptedException e){

                    }
                } else {
                    System.out.println("********* A");
                    objLock.notifyAll();
                    count++;
                }
            }
        }
    }

    private static void executeB(){
        while(true){
            System.out.println("execute B count:"+count);
            synchronized(objLock){
                if (count % 3 != 1) {
                    try {
                        objLock.wait();
                    } catch(InterruptedException e){

                    }
                } else {
                    System.out.println("********* B");
                    objLock.notifyAll();
                    count++;
                }
            }
        }
    }

    private static void executeC(){
        while(true){
            System.out.println("execute C count:"+count);
            synchronized(objLock){
                if (count % 3 != 2) {
                    try {
                        objLock.wait();
                    } catch(InterruptedException e){

                    }
                } else {
                    System.out.println("********* C");
                    objLock.notify();
                    count++;
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
