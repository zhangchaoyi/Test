package leetcode.array;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 * 思路：倒序遍历
 * 观察总结规律
 * 1 2 3 4 5
 * 1 2 3 5 4
 * 1 2 4 3 5
 * 1 2 4 5 3
 * 1 2 5 3 4
 * 1 2 5 4 3
 * 1 3 2 4 5
 * 1 3 2 5 4
 * 。。。。
 *
 * 当发现nums[i] > nums[i-1] ，将 (i,length-1) 中的比 i-1 大的最小值 放到i-1
 * 同时将剩余的元素升序排序放到 i,length-1 的位置
 *
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:27
 */
public class NextPermuataion {

    public static void main(String[] args){
        int[] nums = new int[]{1,2,4,3,5};
        NextPermuataion np = new NextPermuataion();
        np.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }



    public void nextPermutation(int[] nums) {
        boolean shouldOrder = true;
        for(int i=nums.length-1;i>0;i--){
            if(nums[i] > nums[i-1]){
                shouldOrder=false;
                //从i->length-1 中找到比i-1大的最小元素
                int shouldSwapIndex = i-1;
                int tempMaxIndex = i;
                for(int j=i;j<nums.length;j++){
                    if(nums[j]>nums[shouldSwapIndex] && nums[j]<nums[tempMaxIndex]) {
                        tempMaxIndex = j;
                    }
                }
                swap(nums, tempMaxIndex, shouldSwapIndex);
                //剩余元素升序放到 i,length-1 位置
                Arrays.sort(nums, i, nums.length);
                return;
            }
        }
        if(shouldOrder){
            Arrays.sort(nums);
        }
    }

    private void swap(int[] nums, int k, int p){
        int temp = nums[k];
        nums[k]=nums[p];
        nums[p]=temp;
    }
}
