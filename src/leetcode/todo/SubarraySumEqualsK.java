package leetcode.todo;

/**
 *560. 和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 * 思路：滑动窗口
 * 从左向右遍历，维护一个和为k的窗口，right满足k后进行记录，同时左指针移动下一位，右指针继续向右遍历
 * 当右指针遍历到区间和 > k , 移动左指针
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:53
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        int left=0;
        int right=1;

        int curSum=nums[0];
        while(right<nums.length){
            curSum+=nums[right];

            if (curSum==k) {
                ans++;
                curSum-=nums[left];
                left++;
            } else if(curSum > k){
                while(curSum>k){
                    curSum-=nums[left];
                    left++;
                    if(curSum==k){
                        ans++;
                        curSum-=nums[left];
                        left++;
                        break;
                    }
                }
            }
            right++;
        }

        return ans;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,1,1};
        SubarraySumEqualsK sse = new SubarraySumEqualsK();
        System.out.println(sse.subarraySum(nums, 2));
    }
}
