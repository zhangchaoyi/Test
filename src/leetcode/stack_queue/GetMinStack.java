package leetcode.stack_queue;

import java.util.Iterator;
import java.util.Stack;

/**
 * 设计一个有getMin的栈，返回栈中最小的元素
 * 要求pop/push/getMin的时间复杂度都是O(1)
 *
 * 设计两个栈，正常栈stackData保存当前元素；另外一个栈stackMin用于保存每一步的最小值
 *
 * 思路1：在压入stackData时同时维护stackMin，且如果当前数据小于stackMin顶的数据，则压入当前数据;
 *       出栈时如果stackData顶的数据如果等于stackMin顶的数据则同时删除stackMin顶数据
 *
 * 思路2：在压入stackData时如果当前数据大于stackMin顶的数据，再压入一次stackMin顶的数据;
 *       出栈时同时出两个栈的数据
 *
 */
public class GetMinStack {

    public static class MinStack1{
        private Stack<Number> stackData = new Stack();

        private Stack<Number> stackMin = new Stack();

        public boolean push(Number n){
            stackData.push(n);
            if(stackMin.isEmpty()){
                stackMin.push(n);
            } else {
                Number min = stackMin.peek();
                if(n.intValue() <= min.intValue()){
                    stackMin.push(n);
                }
            }

            return true;
        }

        public Number pop(){
            Number head = stackData.pop();
            Number min = stackMin.peek();
            if(head.intValue() == min.intValue()){
                stackMin.pop();
            }

            return head;
        }

        public Number getMin(){
            return stackMin.peek();
        }

        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("stackData:[");
            if(!stackData.isEmpty()){
                Iterator<Number> iterator = stackData.iterator();
                while(iterator.hasNext()){
                    sb.append(iterator.next() + " ");
                }
            }
            sb.append("]");

            sb.append("\n stackMin:[");
            if(!stackMin.isEmpty()){
                Iterator<Number> iterator = stackMin.iterator();
                while(iterator.hasNext()){
                    sb.append(iterator.next() + " ");
                }
            }
            sb.append("]");

            return sb.toString();
        }
    }

    public static class MinStack2{

        private Stack<Number> stackData = new Stack();

        private Stack<Number> stackMin = new Stack();

        public boolean push(Number n){
            stackData.push(n);
            if(stackMin.isEmpty()){
                stackMin.push(n);
            } else {
                Number min = stackMin.peek();
                if (n.intValue() >= min.intValue()) {
                    stackMin.push(min);
                } else {
                    stackMin.push(n);
                }
            }

            return true;
        }

        public Number pop(){
            Number head = stackData.pop();
            stackMin.pop();

            return head;
        }

        public Number getMin(){
            return stackMin.peek();
        }

        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("stackData:[");
            if(!stackData.isEmpty()){
                Iterator<Number> iterator = stackData.iterator();
                while(iterator.hasNext()){
                    sb.append(iterator.next() + " ");
                }
            }
            sb.append("]");

            sb.append("\n stackMin:[");
            if(!stackMin.isEmpty()){
                Iterator<Number> iterator = stackMin.iterator();
                while(iterator.hasNext()){
                    sb.append(iterator.next() + " ");
                }
            }
            sb.append("]");

            return sb.toString();
        }
    }


    public static void main(String[] args){
        MinStack2 minStack = new MinStack2();
        minStack.push(3);
        minStack.push(5);
        minStack.push(5);
        minStack.push(3);
        minStack.push(3);
        minStack.push(2);
        minStack.push(4);
        minStack.push(5);
        System.out.println(minStack);
        minStack.pop();
        minStack.push(6);
        minStack.push(8);
        minStack.push(9);
        minStack.push(1);
        minStack.push(2);
        minStack.pop();
        minStack.pop();
        minStack.pop();

        System.out.println(minStack);
    }

}
