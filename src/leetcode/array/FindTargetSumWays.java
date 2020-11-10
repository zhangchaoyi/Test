package leetcode.array;

import leetcode.DynamicPlanning.LongestCommonSubstring;

/**
 * 494 目标和
 *
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 *  
 * dp[i][j]表示 nums（0....i） 组成 j 的方法数
 * dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]]
 *
 * 示例：
 *
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *  
 *
 * 提示：
 *
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 *
 * todo
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/9 10:42
 */
public class FindTargetSumWays {

    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][S+1];

        //边界 dp[i][0] 表示(0..i)组成0
//        for(int i=0;i<nums.length;i++){
//            dp[i][0] =
//        }
        //边界 dp[0][j] 表示 使用第一个元素组成 j
        for(int j=0;j<S+1;j++){
            if(nums[0]==j){
                dp[0][j] = 1;
            }
        }

        for(int i=1;i<nums.length;i++){
            for(int j=0;j<S+1;j++){
                if (j>=nums[i] && j+nums[i] <= S) {
                    dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]];
                } else if(j>=nums[i]){
                    dp[i][j] = dp[i-1][j-nums[i]];
                } else if(j+nums[i] <= S){
                    dp[i][j] = dp[i-1][j+nums[i]];
                }
            }
        }
        LongestCommonSubstring.printArray(dp);
        return 0;
    }

    /**
     * https://leetcode-cn.com/problems/target-sum/solution/dong-tai-gui-hua-si-kao-quan-guo-cheng-by-keepal/
     * @param nums
     * @param s
     * @return
     */
    public static int findTargetSumWays2(int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 绝对值范围超过了sum的绝对值范围则无法得到
        if (Math.abs(s) > Math.abs(sum)) return 0;

        int len = nums.length;
        // - 0 +
        int t = sum * 2 + 1;
        int[][] dp = new int[len][t];
        // 初始化
        if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            dp[0][sum + nums[0]] = 1;
            dp[0][sum - nums[0]] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < t; j++) {
                // 边界
                int l = (j - nums[i]) >= 0 ? j - nums[i] : 0;
                int r = (j + nums[i]) < t ? j + nums[i] : 0;
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }
        }
        return dp[len - 1][sum + s];
    }

    public static void main(String[] args){
        FindTargetSumWays fts = new FindTargetSumWays();
        int[] array = new int[]{1, 1, 1, 1, 1};
        fts.findTargetSumWays(array, 3);
    }

}
