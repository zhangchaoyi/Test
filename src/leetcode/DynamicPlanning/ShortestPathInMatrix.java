package leetcode.DynamicPlanning;

import java.util.Arrays;

/**
 * @description:矩阵的最小路径和
 *  给定一个矩阵m，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径上的所有数字累加起来就是路径和，返回所有的路径中最小的路径和
 *  如果给定的m如下：
 *  1 3 5 9
 *  8 1 3 4
 *  5 0 6 1
 *  8 8 4 0
 *  路径 1,3,1,0,6,1,0是所有路径和最小的，所以返回12
 *  dp[i][j] 表示在位置(i,j)的最小路径和，因此dp[i][j] = min{dp[i-1][j], dp[i][j-1]} + m[i][j]
 *  dp[][]中的右下角值及最小路径和，反推找最小路径，找向上和向左较小的值 ，直到（0,0）
 * @author: zhangchaoyi
 * @date: 2019/10/22
 */
public class ShortestPathInMatrix {

    public static void main(String[] args){
        int[][] m = new int[][]{{1,3,5,9},{8,1,3,4},{5,0,6,1},{8,8,4,0}};

        int[][] dp = getDp(m);

        int[] path = getMinPath(dp, m);
        System.out.println(Arrays.toString(path));
    }

    public static int[] getMinPath(int[][] dp, int[][] m){
        int rowLimit = dp.length;
        int colLimit = dp[0].length;
        int len = rowLimit + colLimit - 1;
        int[] path = new int[len];//长度是固定的，跟矩阵大小有关

        int row = rowLimit-1;
        int col = colLimit-1;

        path[--len] = m[row][col];
        while(row!=0&&col!=0){
            int up = Integer.MAX_VALUE;
            int left = Integer.MAX_VALUE;
            if(row>0){
                up = dp[row-1][col];
            }
            if(col>0){
                left = dp[row][col-1];
            }

            if(up > left){
                path[--len] = m[row][col-1];
                col--;
            } else {
                path[--len] = m[row-1][col];
                row--;
            }
        }
        path[--len] = m[0][0];
        return path;
    }

    public static int[][] getDp(int[][] m){
        int row = 0;//row只允许递增
        int col = 0;//col只允许递增

        int rowLimit = m.length;
        int colLimit = m[0].length;

        int[][] dp = new int[rowLimit][colLimit];//dp[i][j]表示在(i，j)的最小路径和，则有dp[i][j] = min(dp[i][j-1], dp[i-1][j]) + m[i][j]

        dp[0][0] = m[0][0];
        //初始化边界
        for(int i=1;i<rowLimit;i++){
            dp[i][0] = dp[i-1][0] + m[i][0];
        }
        for(int j=1;j<colLimit;j++){
            dp[0][j] = dp[0][j-1] + m[0][j];
        }

        for(int i=1;i<rowLimit;i++){
            for(int j=1;j<colLimit;j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + m[i][j];
            }
        }

        LongestCommonSubstring.printArray(dp);

        return dp;
    }

}
