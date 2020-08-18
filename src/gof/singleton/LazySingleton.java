package gof.singleton;

/**
 * Created by zcy on 18-3-15.
 * 为什么需要volatile？
 * 禁止指令重排序， new是一个非原子过程，1.申请内存 2.初始化变量 3.引用指向内存，有可能出现重排序为1 3 2， 导致线程1执行到 3 ，线程2就可以看见一个未初始化的对象(到了3已经不为null)
 * 分别叙述每个判断空的语义
 * 第一个判空是优化减少调用，如果没有就直接synchronize，多线程下开销比较大
 * 第二个判空是阻止在synchronized上等待的线程唤醒后进入临界区再次new一个对象，比如线程1和线程2同时进入synchronized，线程1获得锁进入临界区，线程2阻塞发生上下文切换，线程1执行完临界区new对象后释放锁，线程2重新获得锁再次执行临界区代码，如果没有内层判空就会再new一个新对象
 *
 */
public class LazySingleton {

    private LazySingleton(){}

    private static volatile LazySingleton lazySingleton = null;

    public static LazySingleton getLazySingleton(){
        if(lazySingleton == null){
            synchronized(LazySingleton.class) {
                if(lazySingleton == null){
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}

