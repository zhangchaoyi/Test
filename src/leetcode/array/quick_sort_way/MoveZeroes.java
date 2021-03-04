package leetcode.array.quick_sort_way;

import java.util.Arrays;

/**
 * Created by zcy on 18-5-18.
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

 示例:

 输入: [0,1,0,3,12]
 输出: [1,3,12,0,0]

 类似快排思想
 将非0元素原地移动：时间复杂度O(n) ,  空间复杂度 O(1)
  左指针表示指向非零元素的最后一位，最终可以保证左指针左边的都是非零元素
  右指针用于指向遍历，每遍历到一个非0元素赋值 nums[left++], 则等到右指针到length结束

 */
public class MoveZeroes {

    public void moveZeroes2(int[] nums) {
        int left = 0;
        int right = 0;

        while(right<nums.length){
            if(nums[right] != 0){
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        //一次遍历后将left右边的置零
        for(int i=left;i<nums.length;i++){
            nums[i]=0;
        }
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args){
        int[] a = new int[]{0,1,0,3,12};

        MoveZeroes mz = new MoveZeroes();
        mz.moveZeroes2(a);
        System.out.println(Arrays.toString(a));
    }
}
