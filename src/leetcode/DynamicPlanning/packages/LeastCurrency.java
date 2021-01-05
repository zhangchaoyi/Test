package leetcode.DynamicPlanning.packages;

import leetcode.DynamicPlanning.LongestCommonSubstring;

import java.util.Arrays;

/**
 * 322. 零钱兑换, 类似于完全背包，套公式取最小值
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 示例 4：
 * 输入：coins = [1], amount = 1
 * 输出：1
 *
 * 示例 5：
 * 输入：coins = [1], amount = 2
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 * =============================================================================================================================
 * 官方思路：类似完全背包
 * dp[i] 表示组成i元的最少货币数, 则考虑最后一张组成的货币可能是arr[0..j]的其中一种
 * dp[i] = min{dp[i], dp[i-coins[j]] + 1， (0<=j<=coins.length && i>=coins[j])} , 表示dp[i]由所有最后一张货币的情况中的最小值 +1 ；
 * dp[length-1]即所求值
 * dp[0]=0
 *
 * =============================================================================================================================
 * 书本思路：
 * @description:换钱最少货币数
 * 给定数组arr,arr中所有的值都为正数且不重复，每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个整数aim代表要找的钱数，
 * 求组成aim的最少币数
 * arr=[5,2,3],aim=20. 4张5元可以组成20元，其他的找钱方案都要使用更多张的货币，所以返回4
 * arr=[5,2,3],aim=0，不用任何货币就可以组成0元，返回0
 * arr=[3,5],aim=2，根本无法组成2元，钱不能找开的情况下默认返回-1.
 *
 * 思路：dp[i][j]  表示在可以使用arr[0.....i]的货币下组成j所需的最小张数
 *     dp[0...i][0] 第一列 表示找的钱数为0时需要的最少张数, 因此都是0
 *     dp[0][0...j]表示只能使用arr[0]组成货币的最小张数，因此使用每一位去除arr[0]得到对应值，如果除不尽则设为最大值
 *     剩下的位置假设到dp[i][j], 他的值可能来自以下的情况：
 *      1.完全不使用当前货币arr[i]的情况下  dp[i][j] = dp[i-1][j]
 *      2.只使用一张当前货币arr[i]：  dp[i][j] = dp[i-1][j - 1 * arr[i]] + 1
 *      3.只使用两张当前货币arr[i]：  dp[i][j] = dp[i-1][j - 2 * arr[i]] + 2
 *      4.只使用N张当前货币arr[i]：  dp[i][j] = dp[i-1][j - N * arr[i]] + N
 *
 *      dp[i][j]的值来自于以上的最小值  dp[i][j] = min{dp[i-1][j- k * arr[i]] + k (0=<k)}
 *
 *      => dp[i][j] = min{dp[i-1][j], min{dp[i-1][j - x * arr[i]] + x (1<=x)}}
 *
 *      => dp[i][j] = min{dp[i-1][j], min{dp[i-1][j-arr[i]-y*arr[i]] + y+1 (0<=y)}}   //将 y+1 代入上述的 x
 *
 *      又有 min{dp[i-1][j-arr[i]-y*arr[i]] + y (0<=y)} => dp[i][j-arr[i]]
 *
 *      所以 最终有 dp[i][j] = min{dp[i-1][j], dp[i][j-arr[i]]+1}, 如果j - arr[i] < 0 , 数组发生越界，用一张arr[i]都超过了j，此时取dp[i-1][j]
 *
 * =======================================================================================================================
 * @author: zhangchaoyi
 * @date: 2019/10/23
 */
public class LeastCurrency {

    public static void main(String[] args){
        int[] arr=new int[]{1,4,9};
        int aim=12;

        LeastCurrency lc = new LeastCurrency();
//        int[] dp = lc.getDp(arr, aim);
//        System.out.println(Arrays.toString(dp));
//        System.out.println(dp[dp.length-1]);
//        LongestCommonSubstring.printArray(lc.getDp2(arr, aim));
        System.out.println(lc.coinChange2(arr, aim));
    }

    public int coinChange(int[] coins, int amount){
        int[] dp = getDp(coins, amount);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange2(int[] coins, int amount){
        int[][] dp = getDp2(coins, amount);
        return dp[coins.length-1][amount] > amount ? -1 : dp[coins.length-1][amount];
    }

    /**
     * 官方思路 dp[i] 表示组成i元的最少货币数
     * @param arr
     * @param aim
     * @return
     */
    public int[] getDp(int[] arr, int aim){
        int[] dp = new int[aim+1];//aim+1 的原因是考虑第一位为0，比如aim=10，是【0，1,2.....10】 共11位
        Arrays.fill(dp, (int)Math.pow(10, 4)+1);
        //组成0元所需货币数为0
        dp[0] = 0;
        for(int i=1;i<dp.length;i++){
            //组成i元最后一张货币可能是arr中的任意一种， 取所有情况的min
            for(int j=0;j<arr.length;j++){
                if(i>=arr[j]){
                    dp[i] = Math.min(dp[i], dp[i-arr[j]] + 1);
                } else {
                    //当前货币面额比所求组成金额大，说明当前情况不满足
                }
            }
        }
        return dp;
    }

    public int[][] getDp2(int[] c, int V){
        int[][] dp = new int[c.length][V+1];
        int max = (int) Math.pow(10, 4)+1;
        //init dp[0][0]=0
        //以下为第一行和第一列初始
        //dp[0][j]表示只取第一个物品组成[0..j]的最小值，如果满足面额要求都是 j/c[0]
        for(int j=0;j<V+1;j++){
            if(c[0]<=j && j%c[0]==0){
                dp[0][j]=j/c[0];
            } else {
                dp[0][j]=max;
            }
        }
        //dp[i][0] 表示从[0...i]种货币组成面额为0的物品的价值, 都是0  -- 根据题目题意
        for(int i=0;i<c.length;i++){
            dp[i][0] = 0;
        }
        for(int i=1;i<c.length;i++){
            for(int j=1;j<V+1;j++){
                if (j>=c[i]) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-c[i]]+1);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        LongestCommonSubstring.printArray(dp);
        return dp;
    }
}
