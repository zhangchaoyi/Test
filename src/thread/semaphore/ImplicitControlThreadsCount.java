package thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 使用 semaphore 进行流量控制
 */
public class ImplicitControlThreadsCount {

    final ExecutorService executorService = Executors.newCachedThreadPool();
    final Semaphore semaphore = new Semaphore(Runtime.getRuntime().availableProcessors()*2);

    public void doSomething(final String data) throws InterruptedException {
        semaphore.acquire();
        Runnable task = () -> {
            try {
                process(data);
            } finally {
                semaphore.release();
            }
        };
        executorService.submit(task);
    }

    private void process(String data){}
}
