package queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by zcy on 18-3-14.
 *
 */
public class DelayTask<T extends Runnable> implements Delayed  {

    private final T task;
    private volatile long time;//过期时间，定值

    public DelayTask(T task, long timeout, TimeUnit timeUnit){
        this.task = task;
        this.time = System.currentTimeMillis() + timeUnit.toMillis(timeout);
    }

    /**
     * 返回剩余时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {

        return unit.convert(this.time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {

        return 0;
    }
}
