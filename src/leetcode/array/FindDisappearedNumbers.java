package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *  4,3,2,4,8,2,3,1 -> 4,3,2,4,8,2,7,1 -> 4,3,2,4,8,2,7,1 -> 4,3,3,4,8,2,7,1 -> 4,2,3,4,8,2,7,1
 *
 *  4,2,3,4,8,2,7,8 -> 1,2,3,4,8,2,7,8
 *
 *  遍历一次数组，找出 num[i]-1 != i 的元素
 *
 *
 * 输出:
 * [5,6]
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:37
 *
 * 思路：1.借助一个map，计数排序，时间复杂度O(n), 空间复杂度O(n)
 *      2.原地换位，鸽巢原理  将位置i的数放到num[i]-1
 */
public class FindDisappearedNumbers {

    /**
     * 使用思路2实现
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i=0;i<nums.length;i++){
            while(nums[i] != i+1){
                //判断nums[i]-1是否已经有了正确的元素
                if(nums[nums[i]-1] == nums[i]){
                    break;
                }
                //将当前位置i的元素移动到应该的位置 nums[i]-1
                swap(nums, i, nums[i]-1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i] != i+1){
                list.add(i+1);//说明位置i的元素错误，记录i+1
            }
        }

        return list;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args){
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        FindDisappearedNumbers fd = new FindDisappearedNumbers();
        System.out.println(fd.findDisappearedNumbers(nums));
    }
}
