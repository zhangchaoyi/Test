package stack;

import java.util.Stack;

/**
 *  使用stack保存一个单向顺序数组
 * @Author: chaoyi.zhang
 * @Date: 2020/11/6 10:30
 */
public class SingleOrderStack {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            pushAfterPopMaxValue(stack, nums[i]);
        }
    }

    /**
     * 让stack保持一个 从栈底到栈顶 从大到小 的结构
     * @param stack
     * @param num
     */
    private static void pushAfterPopMinValue(Stack<Integer> stack, Integer num) {
        while (!stack.isEmpty()) {
            Integer stackHead = stack.peek();
            if (stackHead < num) {
                stack.pop();
            } else {
                break;
            }
        }
        stack.push(num);
    }

    /**
     * 让stack保持一个 从栈底到栈顶 从小到大 的结构
     * @param stack
     * @param num
     */
    private static void pushAfterPopMaxValue(Stack<Integer> stack, Integer num) {
        while (!stack.isEmpty()) {
            Integer stackHead = stack.peek();
            if (stackHead > num) {
                stack.pop();
            } else {
                break;
            }
        }
        stack.push(num);
    }

}
