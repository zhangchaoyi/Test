package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 18-3-9.
 * -XX:PermSize=10M -XX:MaxPermSize=10M  @Deprecated  JDK1.7后 不能设置永久区，要等一段时间才OOM
 * Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * heap OOM
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args){
        //使用List保持常量池的引用，避免Full GC 回收常量池
        List<String> list = new ArrayList<String>();
        //10MB的PermSize在integer范围足够产生OOM
        int i = 0;
        while(true){
            list.add(String.valueOf(i).intern());//intern方法会先查常量池是否有string，如果没有则在常量池新建
        }
    }
}
