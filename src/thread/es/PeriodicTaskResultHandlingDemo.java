package thread.es;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zhangchaoyi
 * @date: 2019/8/20
 */
public class PeriodicTaskResultHandlingDemo {

    final static ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        final String host = args[0];
        final AsyncTask<Integer> asyncTask = new AsyncTask<Integer>(ses) {
            final Random rnd = new Random();
            final String targetHost = host;

            @Override
            public Integer call() throws Exception {
                return pingHost();
            }

            private Integer pingHost() throws Exception {
                TimeUnit.SECONDS.sleep(2000);
                Integer r = Integer.valueOf(rnd.nextInt(4));
                return r;
            }

            @Override
            protected void onResult(Integer result){
                saveToDatabase(result);
            }

            private void saveToDatabase(Integer result){
                System.out.println("");
            }

            @Override
            public String toString(){
                return "ping " + targetHost + "," + super.toString();
            }
        };

        ses.scheduleAtFixedRate(asyncTask, 0, 3, TimeUnit.SECONDS);
    }

}
