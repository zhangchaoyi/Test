package leetcode.array;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。(使用除法只需把总乘积/当前nums[i])
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 *
 * 原数组：       [1       2       3       4]
 * 左部分的乘积：   1       1      1*2    1*2*3
 * 右部分的乘积： 2*3*4    3*4      4      1
 * 结果：        1*2*3*4  1*3*4   1*2*4  1*2*3*1
 */
public class ProductExceptSelf {

    public static void main(String[] args){
        ProductExceptSelf pes = new ProductExceptSelf();
        int[] result = pes.productExceptSelf(new int[]{1,2,3,4});
        System.out.println(Arrays.toString(result));
    }

    /**
     * 分开左右两个部分乘积
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for(int i=0;i<result.length;i++){
            result[i] = 1;
        }
        int left = 1;//左累乘积
        int right = 1;//右累乘积
        for(int i=0;i<nums.length;i++){
            if(i!=0){
                left *= nums[i-1];
                right *= nums[nums.length-i];
            }
            result[i] *= left;

            result[nums.length-i-1] *= right;
        }

        return result;
    }
}
