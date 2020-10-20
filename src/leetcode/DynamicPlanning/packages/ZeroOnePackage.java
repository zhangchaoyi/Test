package leetcode.DynamicPlanning.packages;

import leetcode.DynamicPlanning.LongestCommonSubstring;

/**
 * 01背包问题
 * 有 N 件物品和一个容量为 V 的背包。放入第 i 件物品耗费的费用是 Ci，得到的 价值是 Wi。求解将哪些物品装入背包可使价值总和最大。
 * 特点是每个物品只能取一次
 * 定义F[i][j]表示（0....i）的物品中背包当前体积是j 的最大价值
 * F[i][j] = max{F[i-1][j] + F[i-1][j-Ci]+Wi, j>=Ci} 表示当前第i个物品 取或者不取 的最大值
 * F[N][V] 即所求值
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/20 17:03
 */
public class ZeroOnePackage {

    public static void main(String[] args){
        ZeroOnePackage zop = new ZeroOnePackage();
        int[] c = new int[]{4, 6, 2, 2, 5, 1};
        int[] w = new int[]{8, 10, 6, 3, 7, 2};
        int[][] dp = zop.getDp(c, w, 12);
        LongestCommonSubstring.printArray(dp);
    }

    public int[][] getDp(int[] c, int[] w, int V){
        int[][] dp = new int[c.length][V+1];
        //init
        //dp[0][j]表示只取第一个物品组成[0..j]的体积物品，如果满足体积要求都是w0
        for(int j=0;j<V+1;j++){
            if(c[0]<=j){
                dp[0][j]=w[0];
            }
        }
        //dp[i][0] 表示从[0...i]组成体积为0的物品的价值, 都是0
        for(int i=0;i<c.length;i++){
            dp[i][0] = 0;
        }
        for(int i=1;i<c.length;i++){
            for(int j=1;j<V+1;j++){
                if (j>=c[i]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-c[i]]+w[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp;
    }

}
