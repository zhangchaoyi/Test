package leetcode.DynamicPlanning;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 *
 * 思路：dp[i]表示到达i层阶梯的方法数
 *      dp[i]=dp[i-1] + dp[i-2]; i>2
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:31
 */
public class ClimbStairs {

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        if (n<=2) {
            return dp[n];
        }
        for(int i=3;i<n+1;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    /**
     * 压缩空间，实际上 n = sum(n-1) + sum(n-2)
     * 实际上dp的压缩空间
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        if (n<=2) {
            if(n==0){
                return 0;
            } else if(n==1){
                return 1;
            } else if(n==2) {
                return 2;
            }
        }
        int sum1 = 2;
        int sum2 = 1;
        int sum = 0;

        for(int i=3;i<n+1;i++){
            sum = sum1 + sum2;

            sum2 = sum1;
            sum1 = sum;

        }
        return sum;
    }

    public static void main(String[] args){
        ClimbStairs climbStairs = new ClimbStairs();
        System.out.println(climbStairs.climbStairs1(4));
    }
}
