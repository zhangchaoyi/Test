package leetcode.array;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *
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
 * todo
 * 思路：暴力解法 O(n^3)
 * 动态规划 dp[i][j] 表示数组（i,j）的乘积 , dp[i][j] = dp[i][j-1]
 *
 */
public class MaxProduct {

    public int maxProduct(int[] nums) {
        return 0;
    }
}
