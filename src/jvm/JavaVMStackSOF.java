package jvm;

/**
 * Created by zcy on 18-3-9.
 * StackOverflowError 栈内存溢出
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args){
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try{
            oom.stackLeak();
        } catch (Exception e){
            System.out.println("stack length:" + oom.stackLength);
            throw e ;
        }
    }
}
