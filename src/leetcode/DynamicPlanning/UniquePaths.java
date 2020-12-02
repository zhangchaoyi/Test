package leetcode.DynamicPlanning;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 *
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:26
 *
 * dp[i][j]表示（i,j）的不同路径
 * dp[i][j] = dp[i][j-1] + dp[i-1][j]
 *
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        //row=0 第一行
        for(int i=0;i<n;i++){
            dp[0][i] = 1;
        }
        //col=0 第一列
        for(int i=0;i<m;i++){
            dp[i][0] = 1;
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args){
        UniquePaths up = new UniquePaths();
        System.out.println(up.uniquePaths(1, 1));
    }
}
