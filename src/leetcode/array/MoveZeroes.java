package leetcode.array;

import java.util.Arrays;

/**
 * Created by zcy on 18-5-18.
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

 示例:

 输入: [0,1,0,3,12]
 输出: [1,3,12,0,0]
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        for(int i=0; i<nums.length - 1; i++){
            if (nums[i] == 0) {
                int next = i+1;
                while(next <= nums.length - 1 && nums[next] == 0){
                    next++;
                }
                if(next > nums.length - 1) {
                    break;
                }
                //swap i next
                swap(nums, i, next);
            }
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
        mz.moveZeroes(a);
        System.out.println(Arrays.toString(a));
    }
}
