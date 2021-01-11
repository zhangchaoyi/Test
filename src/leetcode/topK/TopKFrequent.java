package leetcode.topK;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 *
 * 思路：1.使用空间换时间， HashMap记录次数
 *      2.排序
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:49
 */
public class TopKFrequent {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int count = countMap.getOrDefault(nums[i], 0);
            countMap.put(nums[i], count+1);
        }
        PriorityQueue<Holder> queue = new PriorityQueue<>(k, Comparator.comparingInt(Holder::getCount));

        countMap.entrySet().stream().forEach(entry->{
            if(queue.size()<k){
                queue.add(new Holder(entry.getKey(), entry.getValue()));
            } else {
                Holder top = queue.peek();
                if(entry.getValue() > top.getCount()) {
                    queue.poll();
                    queue.add(new Holder(entry.getKey(), entry.getValue()));
                }
            }
        });
        int[] res = new int[k];
        for(int i=0;i<res.length;i++){
            res[i]=queue.poll().element;
        }
        return res;
    }

    static class Holder{
        private int element;
        private int count;

        private int getCount(){
            return count;
        }

        Holder(int element, int count){
            this.element = element;
            this.count = count;
        }
    }

    public static void main(String[] args){
        int[] nums = new int[]{3,0,1,0};
        TopKFrequent tk = new TopKFrequent();
        System.out.println(Arrays.toString(tk.topKFrequent(nums, 1)));
    }
}
