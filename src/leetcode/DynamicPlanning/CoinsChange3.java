package leetcode.DynamicPlanning;

/**
 * 518. 零钱兑换 II
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 *
 * 示例 1:
 *
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * dp[i][j] 表示[0..i]种货币组成j金额的组合数
 * dp[i][j]=dp[i-1][j]+dp[i][j-coins[i]]  -- 由"不取货币i组成j" + “取货币i组成j” ， 其中 “取货币i组成j” ==》"[0..i]组成 j-coins[i]"
 *
 * 已知coins[i]=2, j=5,   dp[5] = dp[j-coins[i]] = dp[3]
 * 比如说，你想用面值为 2 的硬币凑出金额 5，那么如果你知道了凑出金额 3 的方法，再加上一枚面额为 2 的硬币，不就可以凑出 5 了。
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/21 18:26
 */
public class CoinsChange3 {

    public static void main(String[] args){
        int amount = 0; int[] coins = new int[]{};

        CoinsChange3 cc3 = new CoinsChange3();
        System.out.println(cc3.change(amount, coins));
    }

    public int change(int amount, int[] coins) {
        if(amount==0){
            return 1;
        }
        if(coins.length == 0){
            return 0;
        }
        int[][] dp = getDp(coins, amount);
        return dp[coins.length-1][amount];
    }

    public int[][] getDp(int[] coins, int amount){
        int[][] dp = new int[coins.length][amount+1];
        //init
        //dp[i][0] 使用[0..i]货币组成0的组合数, 都有一种组合方式
        for(int i=0;i<coins.length;i++){
            dp[i][0] = 1;
        }
        //dp[0][j] 只使用第一个货币组成不同 j, 可以整除说明有一种组合方式
        for(int j=0;j<amount+1;j++){
            if(j%coins[0]==0){
                dp[0][j]=1;
            }
        }
        for(int i=1;i<coins.length;i++){
            for(int j=1;j<amount+1;j++){
                if(j>=coins[i]){
                    dp[i][j]=dp[i-1][j]+dp[i][j-coins[i]];
                } else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp;
    }
}
