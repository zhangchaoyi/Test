package leetcode.DynamicPlanning.packages;

import leetcode.DynamicPlanning.LongestCommonSubstring;

import java.util.Arrays;

/**
 * 完全背包
 * 有 N 种物品和一个容量为 V 的背包，每种物品都有无限件可用。放入第 i 种物品 的费用是 Ci，价值是 Wi。
 * 求解：将哪些物品装入背包，可使这些物品的耗费的费用总 和不超过背包容量，且价值总和最大。
 *
 * 对于第i中物品，策略是 取1件，2件.. v/Ci 件的情况，然后判断这些情况的最大值
 * 定义F[i][j]表示（0....i）的物品中背包当前体积是j 的最大价值
 * F[i][j] = max{F[i-1][j-k*Ci]+KWi，0 ≤ k ≤ v/Ci}  时间复杂度O(nvk)
 *
 * 依据01背包的公式推导
 *  for i (0..N)
 *      for j (1...V+1)
 *          F[i][j] = max{F[i-1][j], F[i-1][j-Ci]+Wi}
 * 因为在01背包中 F[i-1][j-Ci]+Wi 表示选择第i个物品时需要依据"不包含第i个物品的子问题"
 * 而完全背包中我们恰恰需要在第i个物品时考虑第i个物品的子问题，因此有 F[i][j-Ci]+Wi ， 所以最终 F[i][j] = max{F[i-1][j], F[i][j-Ci]+Wi}
 *
 * 压缩成一维数组
 * for i (0...N)
 *   for j (1...N+1)
 *      F[v] = max{F[v], F[j-Ci]+Wi}  //由遍历顺序可知 F[v]实际上是F[i-1][v], F[j-Ci]是F[i][j-Ci]
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/21 14:18
 */
public class CompletePackage {

    public static void main(String[] args){
        CompletePackage cp = new CompletePackage();
        int[] c = new int[]{1, 2, 3, 4};
        int[] w = new int[]{2, 4, 4, 5};
        LongestCommonSubstring.printArray(cp.getDp(c, w, 5));
//        System.out.println(Arrays.toString(cp.getDp2(c, w, 5)));
    }

    public int[][] getDp(int[] c, int[] w, int V){
        int[][] dp = new int[c.length][V+1];
        //init dp[0][0]=0
        //以下为第一行和第一列初始
        //dp[0][j]表示只取第一个物品组成[0..j]的体积物品，如果满足体积要求都是 (j/c[0]) * w[0]
        for(int j=0;j<V+1;j++){
            if(c[0]<=j){
                int num = j/c[0];
                if (num>0) {
                    dp[0][j]=num*w[0];
                }
            }
        }
        //dp[i][0] 表示从[0...i]组成体积为0的物品的价值, 都是0
        for(int i=0;i<c.length;i++){
            dp[i][0] = 0;
        }
        for(int i=1;i<c.length;i++){
            for(int j=c[i];j<V+1;j++){
                if (j>=c[i]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-c[i]]+w[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp;
    }

    public int[] getDp2(int[] c, int[] w, int V){
        int[] dp = new int[V+1];
        //init dp[0..N]=0
        //以下相当于二维第一列初始化
        for(int j=0;j<V+1;j++){
            if(c[0]<=j){
                dp[j]=w[0];
            }
        }

        for(int i=1;i<c.length;i++){
            for(int j=c[i];j<V+1;j++){
                if (j>=c[i]) {
                    dp[j] = Math.max(dp[j], dp[j-c[i]]+w[i]);
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp;
    }
}
