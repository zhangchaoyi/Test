package queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class NonBlockingQueueTest {

    private static List<String> nonBlockingQueue = new ArrayList<>(100);

    private static Logger logger = Logger.getAnonymousLogger();

    private static int count = 0;

    static {
        init();
    }

    public static void main(String[] args){

        Thread productor = new Thread(() -> {
            logger.info("productor started");
            while(true){
                try {
                    if (nonBlockingQueue.size() >= 100) {
                        logger.info("productor wait");
                        nonBlockingQueue.wait();
                    }
                    nonBlockingQueue.add((count + 1) + "");
                    TimeUnit.SECONDS.sleep(5);
                    logger.info("productor notify");
                    nonBlockingQueue.notify();
                } catch (Exception e){

                }
            }
        });

        Thread consumer = new Thread(() -> {
            logger.info("consumer started");
            while(true){
                try{
                    if (nonBlockingQueue.isEmpty()) {
                        logger.info("consumer wait");
                        nonBlockingQueue.wait();
                    }
                    System.out.println(nonBlockingQueue.remove(0));
                    TimeUnit.SECONDS.sleep(2);
                    logger.info("consumer notify");
                    nonBlockingQueue.notify();
                } catch (Exception e){

                }
            }

        });

        productor.start();
        consumer.start();


    }

    private static void init(){
        for(int i=0; i<100;i++){
            nonBlockingQueue.add(i + "");
        }
    }
}
