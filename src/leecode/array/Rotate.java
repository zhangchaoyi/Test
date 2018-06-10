package leecode.array;

import java.util.Arrays;

/**
 * Created by zcy on 18-5-28.
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 输出: [5,6,7,1,2,3,4]
 解释:
 向右旋转 1 步: [7,1,2,3,4,5,6]
 向右旋转 2 步: [6,7,1,2,3,4,5]
 向右旋转 3 步: [5,6,7,1,2,3,4]


 输入: [-1,-100,3,99] 和 k = 2
 输出: [3,99,-1,-100]
 解释:
 向右旋转 1 步: [99,-1,-100,3]
 向右旋转 2 步: [3,99,-1,-100]
 */
public class Rotate {

    public void rotate(int[] nums, int k) {
        if(nums == null){
            return;
        }
        int h = nums.length - k % nums.length;
        reverse(nums, 0, h-1);
        reverse(nums, h, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int[] nums, int l, int r){
        while(l < r){
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args){
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        Rotate rotate = new Rotate();
        rotate.rotate(nums, 9);

        System.out.println(Arrays.toString(nums));
    }
}
