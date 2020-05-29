package thread.lock;

/**
 * 锁争用demo
 *
 * 如果把lockAccessFrequency调整为150， 即降低锁申请频率，此时锁争用次数上升，申请锁的平均等待时间减少
 * 如果把lockDuration调整为20， 即减少工作者线程对锁的持有时间，此时锁争用次数下降，申请锁的平均等待时间为1ms，几乎可以忽略不计
 */
public class LockContentionDemo {

    static long lockDuration = 100;
    static SharedResource sr = new SharedResource();
    static long lockAccessFrequency = 50;

    static class SharedResource{
        public synchronized void access(){
            try{
                Thread.sleep(lockDuration);
            }catch(Exception e){

            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        int N = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[N];
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(){
                public void run(){
                    for(;;){
                        sr.access();
                        //执行完access，SharedResource对象就可能被其他线程争用
                        try{
                            Thread.sleep(lockAccessFrequency);
                        }catch(Exception e){

                        }
                    }
                }
            };
        }

        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }
    }
}
