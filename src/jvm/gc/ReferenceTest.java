package jvm.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @description: 软引用、弱引用
 * @author: zhangchaoyi
 * @date: 2019/7/22
 */
public class ReferenceTest {

    public static void main(String[] args) throws Exception {
//        weak();
//        soft();
    }

    public static void soft() throws Exception {
        SoftReference<Object> sr = new SoftReference<Object>(new Object());
        System.gc();//只有在gc时内存不足才会对软引用进行回收
        Thread.sleep(10000);

        int i = 0;
        while(true){
            Object sfObj = sr.get();

            if (sfObj == null) {
                System.out.println("null");
                break;
            } else {

                System.out.println("not null:" + i);
                i++;
            }
        }
    }

    public static void weak() throws Exception {
        /**
         * 不能 Object o = new Object();
         *      WeakReference<Object> sr = new WeakReference<>(o);
         * 因为此时的 Object o实际上是一个强引用，永远不会被回收， 如果按上述写法需要指定 o = null;
         */
        WeakReference<Object> sr = new WeakReference<>(new Object());
        System.gc();//只要jvm进行gc则弱引用被回收
        Thread.sleep(10000);

        int i=0;
        while(true){
            Object sfObj = sr.get();

            if (sfObj == null) {
                System.out.println("null");
                break;
            } else {

                System.out.println("not null:" + i);
                i++;
            }
        }


    }
}
