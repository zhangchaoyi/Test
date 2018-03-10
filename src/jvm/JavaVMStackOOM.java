package jvm;

/**
 * Created by zcy on 18-3-9.
 * 堆OOM,不能新建线程
 */
public class JavaVMStackOOM {

    private void dontStop(){
        while(true){
        }
    }

    public void stackLeakByThread(){
        while (true) {
            Thread thread = new Thread(() -> {
                dontStop();
            });
            thread.start();
        }
    }

    public static void main(String[] args){
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
