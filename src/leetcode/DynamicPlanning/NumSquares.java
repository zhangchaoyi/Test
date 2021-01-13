package leetcode.DynamicPlanning;

import java.util.ArrayList;
import java.util.List;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * 思路：找到n的平方根 num， 将(1,num)的所有平方数找出来 List<Integer>
 *     相当于从list中找到最少的满足target的个数
 *     类似于零钱兑换，恰好装满的完全背包
 * dp[i][j] 表示使用（0，i）种元素组成j
 * dp[i][j]=min{dp[i-1][j], dp[i][j-num[i]]+1 (j>num[i])}
 *
 * 最终解即 dp[完全平方数size][target]
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:43
 */
public class NumSquares {

    public int numSquares(int n) {

        int number = (int)Math.sqrt(n);
        List<Integer> list = new ArrayList<>();
        for(int i=1;i<=number;i++){
            list.add(i*i);
        }
        int[][] dp = new int[list.size()][n+1];
        //第一行，dp[0][j]表示使用第一个元素恰好组成不同的j的组合数
        for(int j=0;j<n+1;j++){
            if(j%list.get(0)==0) {
                dp[0][j] = j/list.get(0);
            } else {
                dp[0][j] = Integer.MAX_VALUE;//不能整除不存在
            }
        }
        //第一列，dp[i][0]表示使用不同的（0..i）元素恰好组成0的组合数
        for(int i=0;i<list.size();i++){
            dp[i][0]=0;
        }
        for(int i=1;i<list.size();i++){
            for(int j=1;j<n+1;j++){
                if(j>=list.get(i)){
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-list.get(i)]+1);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        LongestCommonSubstring.printArray(dp);
        return dp[list.size()-1][n];
    }

    public static void main(String[] args){
        NumSquares ns = new NumSquares();
        System.out.println(ns.numSquares(12));
    }
}
