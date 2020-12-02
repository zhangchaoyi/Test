package leetcode.array;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:33
 *
 * 思路：1.暴力解法 O(n^3)
 *
 * 2.动态规划 dp[i][j] 表示数组（i,j）的乘积 , dp[i][j] = dp[i][j-1] * nums[i] , i<j
 *
 * 3.[题解] 动态规划，因为有负数的存在，设置两个dp[], 一个记录最大乘积，一个记录最小乘积
 *          maxDp[i]表示以i结尾的子数组的最大乘积 maxDp[i] = max{maxDp[i-1]*nums[i], nums[i], minDp[i-1]*nums[i]}
 *          maxDp[i]表示以i结尾的子数组的最大乘积 minDp[i] = min{maxDp[i-1]*nums[i], nums[i], minDp[i-1]*nums[i]}
 */
public class MaxProduct {

    /**
     * dp超出内存限制 O(n^2)
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int[][] dp = new int[nums.length][nums.length];

        //init 实际上二维数组使用了上半部分，因为要求 (i<j)
        for(int i=0;i<nums.length;i++){
            dp[i][i] = nums[i];
            max = Math.max(max, dp[i][i]);
        }
        for(int i=1;i<nums.length;i++){
            dp[0][i] = dp[0][i-1] * nums[i];
            max = Math.max(max, dp[0][i]);
        }

        for(int i=1;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                dp[i][j] = dp[i][j-1] * nums[j];
                max = Math.max(max, dp[i][j]);
            }
        }

//        LongestCommonSubstring.printArray(dp);

        return max;
    }

    public int maxProduct(int[] nums) {
        if(nums.length == 0)
            return 0;
        int ans = nums[0];
        //两个mDP分别定义为以i结尾的子数组的最大积与最小积；
        int[] maxDP = new int[nums.length];
        int[] minDP = new int[nums.length];
        //初始化DP；
        maxDP[0] = nums[0]; minDP[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            //最大积的可能情况有：元素i自己本身，上一个最大积与i元素累乘，上一个最小积与i元素累乘；
            //与i元素自己进行比较是为了处理i元素之前全都是0的情况；
            maxDP[i] = Math.max(nums[i], Math.max(maxDP[i-1]*nums[i], minDP[i-1]*nums[i]));
            minDP[i] = Math.min(nums[i], Math.min(maxDP[i-1]*nums[i], minDP[i-1]*nums[i]));
            //记录ans；
            ans = Math.max(ans, maxDP[i]);
        }
        return ans;
    }

    public static void main(String[] args){
        int[] array = new int[]{-2,0,-1};
        MaxProduct mp = new MaxProduct();
        System.out.println(mp.maxProduct(array));
    }


}
