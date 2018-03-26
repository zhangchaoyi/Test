package queue.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zcy on 18-3-14.
 *
 */
public class DelayTask<T extends Runnable> implements Delayed  {

    private final T task;
    private volatile long time;//过期时间，定值

    private static final AtomicLong atomic = new AtomicLong(0);

    private final long n;

    public DelayTask(T task, long timeout, TimeUnit timeUnit){
        this.task = task;
        this.time = System.currentTimeMillis() + timeUnit.toMillis(timeout);
        n = atomic.getAndIncrement();
    }

    public T getTask(){
        return this.task;
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
    public int compareTo(Delayed other) {
        // TODO Auto-generated method stub
        if (other == this) // compare zero ONLY if same object
            return 0;
        if (other instanceof DelayTask) {
            DelayTask x = (DelayTask) other;
            long diff = time - x.time;
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else if (n < x.n)
                return -1;
            else
                return 1;
        }
        long d = (getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }
}
