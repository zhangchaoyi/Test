package leetcode.DynamicPlanning;

/**
 * 213. 打家劫舍 II
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 *  Rob的变形，设置两个dp1、dp2， 一个求 [0...n-1], 另一个求[1...n] , 再 max{dp1, dp2} 即可
 *  dp[i] = max{dp[i-1], dp[i-2]+num[i]}
 *  result[i] = max{dp[0...i-1], dp[1, i]}
 */
public class Rob2 {

    public static void main(String[] args){
        int[] array = new int[]{1, 2 ,1, 1};
        Rob2 rob2 = new Rob2();
        System.out.println(rob2.rob(array));
    }

    public int[] getDp(int[] nums, int start, int end){
        int size = end-start+1;
        int[] dp = new int[size+1];//多申请1位空间, 为了与原数组的下标保持一致，防止下标溢出
        for(int i=start;i<=end;i++){
            if (i==start) {
                dp[i]=nums[i];
            } else if(i==start+1){
                dp[start+1]=nums[start]>nums[start+1]?nums[start]:nums[start+1];
            } else {
                dp[i] = dp[i-1] > (dp[i-2]+nums[i]) ? dp[i-1] : (dp[i-2]+nums[i]);
            }
        }
        return dp;
    }

    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }
        int[] dp1 = getDp(nums, 0, nums.length-2);
        int[] dp2 = getDp(nums, 1, nums.length-1);

        int max = 0;
        for(int i=0;i<dp1.length;i++){
            int maxDp = dp1[i] > dp2[i] ? dp1[i] : dp2[i];
            if(maxDp > max){
                max = maxDp;
            }
        }
        return max;
    }
}
