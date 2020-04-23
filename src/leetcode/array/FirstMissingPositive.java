package leetcode.array;

import java.util.*;

/**
 * 41.缺失的第一个正数
 *
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 *
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 *
 *  示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *  
 *
 * 提示：
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 *
 * 思路：可知 所求的最小正数 <= n+1 ， 因此将数组中的负数和大于n的数都转成 1
 *    转换后数组中的数都在 1，n 之间， 此时可以使用鸽巢原理标记出存在的数
 *
 * 类似hash的原理
 *
 */
public class FirstMissingPositive {

    public static void main(String[] args){
        int[] nums = new int[]{1};

        FirstMissingPositive fmp = new FirstMissingPositive();
        System.out.println(fmp.firstMissingPositive2(nums));

    }

    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }

        //从1开始遍历，找到不存在的数
        int count = 1;
        while(true){
            if(!set.contains(count)){
                break;
            }
            count++;
        }
        return count;
    }

    public int firstMissingPositive2(int[] nums) {
        boolean hasOne = false;
        //数据预处理，找出是否有 1 存在，因为 1 在后面要替换
        for(int i=0;i<nums.length;i++){
            if (nums[i] == 1) {
                hasOne = true;
            }
        }
        if(!hasOne){
            return 1;
        }


        for(int i=0;i<nums.length;i++){
            if (nums[i] < 0 || nums[i] > nums.length) {
                nums[i] = 1;
            }
        }
        //鸽巢，由于有n个数，下标范围 1,n
        int[] mark = new int[nums.length+1];
        Arrays.fill(mark, 0);
        for(int i=0;i<nums.length;i++){
            int val = nums[i];
            mark[val] = 1;
        }

        for(int i=1;i<mark.length;i++){
            if (mark[i] == 0) {
                return i;
            }
            //特殊处理 输入为[1]
            if (i+1 == mark.length) {
                return i+1;
            }
        }

        return -1;
    }
}
