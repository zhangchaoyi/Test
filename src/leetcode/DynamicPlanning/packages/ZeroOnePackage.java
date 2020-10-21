package leetcode.DynamicPlanning.packages;

import leetcode.DynamicPlanning.LongestCommonSubstring;

import java.util.Arrays;

/**
 * 01背包问题
 * 有 N 件物品和一个容量为 V 的背包。放入第 i 件物品耗费的费用是 Ci，得到的 价值是 Wi。求解将哪些物品装入背包可使价值总和最大。
 * 特点是每个物品只能取一次
 * 定义F[i][j]表示（0....i）的物品中背包当前体积是j 的最大价值
 * F[i][j] = max{F[i-1][j] + F[i-1][j-Ci]+Wi, j>=Ci} 表示当前第i个物品 取或者不取 的最大值
 * F[N][V] 即所求值
 *
 * ==============================================================================================================================
 * 时间复杂度为O(NV), 空间复杂度也是O(NV)
 * 空间复杂度可以优化为 O(V) 使用一维数组 dp[V] = max{dp[V], dp[V-Ci]+Wi} ,
 *  注意遍历二维数组时要 for i (1...N)
 *                     for v (V...1)  -- 这里倒序才能满足max{dp[V], dp[V-Ci]+Wi} 中实际上是 max{dp[i-1][V], dp[i-1][V-Ci]+Wi}, 在当次循环中dp[j]实际上存储的是上一行的数值
 *   如果使用顺序遍历则表示 max{dp[V], dp[V-Ci]+Wi} 取的是 max{dp[i][V], dp[i][V-Ci]+Wi}
 *
 * ==============================================================================================================================
 * 问题变种
 * 有 N 件物品和一个容量为 V 的背包。放入第 i 件物品耗费的费用是 Ci，得到的 价值是 Wi。求解将哪些物品装入背包且恰好装满背包可使价值总和最大。
 * 这里和原题的区别在于恰好装满背包, 跟初始化的状态有关
 * 区别在于初始化dp数组: 初始化的 F数组事实上就是在没有任何物品可以放入背包时的合法状态。如果要求背包恰好装满，那么此时只有容量为 0 的背包可以在什么也不装且价值为 0 的情况下被“恰好装满”，其它容量的背包均没有合法的解，属于未定义的状态，应该被赋值为 -∞ 了。
 *                      如果背包并非必须被装满，那么任何容量的背包 都有一个合法解“什么都不装”，这个解的价值为 0，所以初始时状态的值也就全部为 0 了。
 *
 * 对于装满背包求最大值  初始化 dp[0]=0 , dp[1...N]=负无穷
 * 对于装满背包求最小值  初始化 dp[0]=0 , dp[1...N]=正无穷
 * 对于无需装满背包的情况   dp[0..N]=0
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/20 17:03
 */
public class ZeroOnePackage {

    public static void main(String[] args){
        ZeroOnePackage zop = new ZeroOnePackage();
        int[] c = new int[]{2, 2, 6, 5, 4};
        int[] w = new int[]{6, 3, 5, 4, 6};
        int[][] dp = zop.getDp3(c, w, 10);
        LongestCommonSubstring.printArray(dp);
//        System.out.println(dp[c.length-1][12]);

//        int[] dp = zop.getDp3(c, w, 10);
//        System.out.println(Arrays.toString(dp));

    }

    /**
     * 二维数组解决 不装满01背包
     * @param c
     * @param w
     * @param V
     * @return
     */
    public int[][] getDp(int[] c, int[] w, int V){
        int[][] dp = new int[c.length][V+1];
        //init dp[0][0]=0
        //以下为第一行和第一列初始
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
            for(int j=c[i];j<V+1;j++){
                if (j>=c[i]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-c[i]]+w[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp;
    }

    /**
     * 使用一维数组 解决不装满01背包
     * @param c
     * @param w
     * @param V
     * @return
     */
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
            for(int j=V;j>c[i];j--){
                if (j>=c[i]) {
                    dp[j] = Math.max(dp[j], dp[j-c[i]]+w[i]);
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp;
    }

    /**
     * 二维数组解决 装满01背包
     * @param c
     * @param w
     * @param V
     * @return
     */
    public int[][] getDp3(int[] c, int[] w, int V){
        int[][] dp = new int[c.length][V+1];
        //init
        //以下为第一行和第一列初始
        //dp[0][j]表示只取第一个物品组成[0..j]的体积物品，如果满足体积要求都是w0
        for(int j=0;j<V+1;j++){
            if(c[0]==j){
                dp[0][j]=w[0];
            }
        }
        //dp[i][0] 表示从[0...i]组成体积为0的物品的价值, 不存在的情况
        for(int i=0;i<c.length;i++){
            dp[i][0] = Integer.MIN_VALUE;
        }
        for(int i=1;i<c.length;i++){
            for(int j=c[i];j<V+1;j++){
                if (j>=c[i]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-c[i]]+w[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp;
    }

    /**
     * 一维数组解决 装满背包
     * 与原题的区别是初始化时 除了dp[0]=0 , 其余都设置为负无穷
     */
    public int[] getDp4(int[] c, int[] w, int V){
        int[] dp = new int[V+1];
        //init dp[0]=0, dp[1...N]=负无穷
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        //以下相当于二维第一列初始化, 只有第一个物品时的情况
        for(int j=0;j<V+1;j++){
            if(c[0]==j){
                dp[j]=w[0];
            }
        }

        for(int i=1;i<c.length;i++){
            for(int j=V;j>c[i];j--){
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
