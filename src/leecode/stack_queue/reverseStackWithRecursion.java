package leecode.stack_queue;

import java.util.Stack;

/**
 * 使用递归逆序一个栈, 即将栈中的元素完全反转
 * 思路：设计一个递归函数getAndRemoveLastElement获取栈底元素并删除
 *      设计一个递归函数reverse
 *
 */
public class reverseStackWithRecursion {

    public static Number getAndRemoveLastElement(Stack<Number> stack){
        Number n = null;
        if(stack.size() == 1){
            return stack.pop();
        } else {
            n = stack.pop();//要将stack的元素出栈，size才能减1，递归才能进行；同时弹出的stack元素在递归的栈内存中
        }
        Number last = getAndRemoveLastElement(stack);
        stack.push(n);

        return last;
    }

    public static void reverse(Stack<Number> stack){
        if (stack.isEmpty()) {
            return;
        }
        Number last = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(last);
    }

    public static void main(String[] args){
        Stack<Number> stack = new Stack<Number>(){
            {push(1);push(2);push(3);push(4);push(5);}
        };

        reverse(stack);

        System.out.println("==================");
    }
}
