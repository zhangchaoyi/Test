package queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 使用双向队列保持数组中的单向有序性
 * 与singleStack结构相比 可以O(1)复杂度获取最 大/小 值
 * @Author: chaoyi.zhang
 * @Date: 2020/11/6 10:36
 */
public class SingleOrderQueue {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            pushAfterPopMinValue(queue, nums[i]);
        }
    }

    /**
     * 从双向队列的一端进行操作，比如尾部
     * @param queue
     * @param num
     */
    private static void pushAfterPopMaxValue(Deque<Integer> queue, Integer num){
        while(!queue.isEmpty()){
            Integer queueLast = queue.peekLast();
            if(queueLast > num){
                queue.pollLast();
            } else {
                break;
            }
        }
        queue.addLast(num);
    }

    private static void pushAfterPopMinValue(Deque<Integer> queue, Integer num){
        while(!queue.isEmpty()){
            Integer queueLast = queue.peekLast();
            if(queueLast < num){
                queue.pollLast();
            } else {
                break;
            }
        }
        queue.addLast(num);
    }
}
