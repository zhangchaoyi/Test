package thread.es;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * @description: 支持重复执行的异步任务抽象类
 * 子类需要覆盖call方法，通过回调方式得到异步结果
 * @author: zhangchaoyi
 * @date: 2019/8/20
 */
public abstract class AsyncTask<V> implements Runnable, Callable<V> {

    protected final Executor executor;

    public AsyncTask(Executor executor){
        this.executor = executor;
    }

    public AsyncTask(){
        this(new Executor(){
            @Override public void execute(Runnable command) {
                command.run();
            }
        });
    }

    @Override public void run() {
        Exception exp = null;
        V r = null;
        try{
            r = call();
        } catch(Exception e){
            exp = e;
        }

        final V result = r;
        if (null == exp) {
            executor.execute(new Runnable() {
                @Override public void run() {
                    onResult(result);
                }
            });
        } else {
            final Exception exceptionCaught = exp;
            executor.execute(new Runnable() {
                @Override public void run() {
                    onError(exceptionCaught);
                }
            });
        }
    }

    /**
     * 留给子类实现任务执行结果的处理逻辑
     * @param result
     */
    protected abstract void onResult(V result);

    /**
     * 子类可覆盖该方法对任务执行过程中抛出的异常进行处理
     * @param e
     */
    protected void onError(Exception e){
        e.printStackTrace();
    }
}
