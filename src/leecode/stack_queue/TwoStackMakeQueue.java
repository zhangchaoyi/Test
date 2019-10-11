package leecode.stack_queue;

import java.util.Iterator;
import java.util.Stack;

/**
 * 两个栈实现一个队列
 * 一个栈stackIn 用于入栈使用
 * 一个栈stackOut 用于出栈使用，如果stackOut为空，则将stackIn中所有数据出栈迁移stackOut，再将stackOut出栈
 */
public class TwoStackMakeQueue {

    private Stack<Number> stackIn = new Stack<>();
    private Stack<Number> stackOut = new Stack<>();

    public boolean push(Number n){
        stackIn.push(n);
        return true;
    }

    public Number pop() {
        if(stackOut.isEmpty()){
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }

        return stackOut.pop();
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("stackIn:[");
        if(!stackIn.isEmpty()){
            Iterator<Number> iterator = stackIn.iterator();
            while(iterator.hasNext()){
                sb.append(iterator.next() + " ");
            }
        }
        sb.append("]");

        sb.append("\n stackOut:[");
        if(!stackOut.isEmpty()){
            Iterator<Number> iterator = stackOut.iterator();
            while(iterator.hasNext()){
                sb.append(iterator.next() + " ");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    public static void main(String[] args){
        TwoStackMakeQueue q = new TwoStackMakeQueue();
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        q.push(5);
        q.push(6);

        q.pop();
        q.pop();

    }
}
