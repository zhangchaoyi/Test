package leetcode.DynamicPlanning;

/**
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
 *      dp[i][j]的值来自于以上的最小值  dp[i][j] = min{dp[i-1][j- k * arr[i]] + k (0=<k)}
 *
 *      => dp[i][j] = min{dp[i-1][j], min{dp[i-1][j - x * arr[i]] + x (1<=x)}}
 *
 *      => dp[i][j] = min{dp[i-1][j], min{dp[i-1][j-arr[i]-y*arr[i]] + y+1 (0<=y)}}
 *
 *      又有 min{dp[i-1][j-arr[i]-y*arr[i]] + y (0<=y)} => dp[i][j-arr[i]]
 *
 *      所以 最终有 dp[i][j] = min{dp[i-1][j], dp[i][j-arr[i]]+1}, 如果j - arr[i] < 0 , 数组发生越界，用一张arr[i]都超过了j，此时取dp[i-1][j]
 *
 * =======================================================================================================================
 * 补充题目
 * 给定数组arr,arr中所有的值为正数，每个值仅代表一张钱的面值，在给定整数aim代表要找的钱，求组成aim的最少货币数
 * arr=[5,2,3], aim=20， 5元，2元，3元各有一张，所以无法组成20元，因此返回-1
 * arr=[5,2,5,3],aim=10, 5元的货币有两张，可以组成10元，且是最少的，因此返回2
 * arr=[5,2,5,3],aim=15, 所有的钱加起来组成15元，返回4
 * arr=[5,2,5,3],aim=0 不用任何货币就可以组成0，返回0
 *
 * @author: zhangchaoyi
 * @date: 2019/10/23
 */
public class LeastCurrency {

    public static void main(String[] args){

    }
}