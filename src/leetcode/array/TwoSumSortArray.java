package leetcode.array;

import java.util.Arrays;

/**
 * 一个有序数组，从中找出两个数等于target
 *
 * 思路：左右指针，因为数组有序，因此 nums[left]+nums[right]与target比较
 * 如果 nums[left]+nums[right] < target , left++;
 * 如果 nums[left]+nums[right] > target , right++;
 *
 * 如果left > right仍然未找到则没有结果
 *
 * @Author: chaoyi.zhang
 * @Date: 2021/1/29 15:00
 */
public class TwoSumSortArray {

    public static int[] twoSum(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;

        while(left<right){
            if (nums[left]+nums[right]==target) {
                return new int[]{nums[left], nums[right]};
            }
            else if(nums[left]+nums[right]>target){
                right--;
            }
            else if(nums[left]+nums[right]<target){
                left++;
            }
        }
        return null;
    }

    public static void main(String[] args){
        int[] a = new int[]{2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(a, 9)));
    }
}
