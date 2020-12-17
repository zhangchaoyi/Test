package leetcode.array;

import java.util.Arrays;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/10 16:44
 *
 * 思路 1.排序后中位数 O(nlogn)
 *     2.Map 时间复杂度O(n) 空间复杂度O(n)
 *     3.选举算法，一次遍历，先记录一个数作为选举人，如果后面的数与选举人一致 count+1, 如果不一致count-1, 当count=0时更换下一个数作为选举人
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    public int majorityElement1(int[] nums) {
        int count = 1;
        int major = nums[0];
        for(int i=1;i<nums.length;i++){
            if (count==0) {
                count++;
                major=nums[i];
                continue;
            }
            if(nums[i]==major){
                count++;
            } else {
                count--;
            }
        }
        return major;
    }

    public static void main(String[] args){
        int[] nums = new int[]{3,2,3};

        MajorityElement me = new MajorityElement();
        System.out.println(me.majorityElement1(nums));

        System.out.println(Arrays.toString(nums));
    }
}
