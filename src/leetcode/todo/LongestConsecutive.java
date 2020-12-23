package leetcode.todo;

import java.util.HashSet;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 *
 *
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * 思路：1.排序，再一次遍历找最大值 O(nlogn + n)
 *      2.hashSet，将所有元素放入set，遍历set时，找到一个子序列的最小值（即nums[i]-1在set中不存在） ， 往后+1在set中判断是否存在，不断累加 时间 O(n) 空间 O(n)
 * toSubmit
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:40
 */
public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>(nums.length);
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for(Integer num : set){
            if(set.contains(num-1)){
                continue;
            }
            int temp = num;
            int tempCount = 0;
            while(set.contains(temp)){
                tempCount++;
                temp++;
                max = Math.max(tempCount, max);
            }
        }

        return max;
    }

    public static void main(String[] args){
        int[] nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        LongestConsecutive lc = new LongestConsecutive();
        System.out.println(lc.longestConsecutive(nums));
    }
}
