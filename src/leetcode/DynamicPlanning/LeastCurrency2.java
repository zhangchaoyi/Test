package leetcode.DynamicPlanning;

import java.util.Arrays;

/**
 *  给定数组arr,arr中所有的值为正数，每个值仅代表一张钱的面值，在给定整数aim代表要找的钱，求组成aim的最少货币数
 *  arr=[5,2,3], aim=20， 5元，2元，3元各有一张，所以无法组成20元，因此返回-1
 *  arr=[5,2,5,3],aim=10, 5元的货币有两张，可以组成10元，且是最少的，因此返回2
 *  arr=[5,2,5,3],aim=15, 所有的钱加起来组成15元，返回4
 *  arr=[5,2,5,3],aim=0 不用任何货币就可以组成0，返回0
 *
 *  类似01背包的恰好填满背包情况
 *  dp[i][j] = min{dp[i-1][j], dp[i-1][j-arr[i]]+1, j>=arr[i]}
 *  压缩空间 dp[j] = min{dp[j], d[j-arr[i]]+1， j>=arr[i]} //遍历时内层倒序遍历，所以总的顺序是从右到左自上而下
 *  初始化注意 dp[]
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/21 17:05
 */
public class LeastCurrency2 {

    public static void main(String[] args) {
        int[] arr = new int[]{5,2,5,3};
        int aim = 0;

        LeastCurrency2 lc2 = new LeastCurrency2();

        System.out.println(Arrays.toString(lc2.getDp(arr, aim)));

//        System.out.println(lc2.getMin(arr, aim));

    }

    public int getMin(int[] arr, int aim){
        int[][] dp = getDp2(arr, aim);
        LongestCommonSubstring.printArray(dp);
        if(dp[arr.length-1][aim] > aim){
            return -1;
        }
        return dp[arr.length-1][aim];
    }

    public int[][] getDp2(int[] arr, int aim){
        int[][] dp = new int[arr.length][aim+1];
        //dp[0][j]初始化, 表示只使用第一个零钱组成不同的金额
        for(int j=0;j<aim+1;j++){
            if(j==arr[0]){
                dp[0][j] = 1;
            } else {
                dp[0][j] = (int)Math.pow(10, 4)+1;
            }
        }
        //dp[i][0]初始化, 表示使用（0...i）货币组成0，此时都是0
        for(int i=0;i<arr.length;i++){
            dp[i][0] = 0;
        }
        for(int i=1;i<arr.length;i++){
            for(int j=1;j<aim+1;j++){
                if(j>=arr[i]){
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-arr[i]]+1);
                } else {
                    //表示当前货币面额比目标金额还大
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp;
    }

    public int[] getDp(int[] arr, int aim){
        int[] dp = new int[aim+1];
        //第一列零钱初始化
        for(int j=0;j<aim+1;j++){
            if(j==arr[0]){
                dp[j] = 1;
            } else {
                dp[j] = (int)Math.pow(10, 4)+1;
            }
        }
        dp[0]=0;

        for(int i=1;i<arr.length;i++){
            for(int j=aim;j>0;j--){
                if (j>=arr[i]) {
                    dp[j] = Math.min(dp[j], dp[j-arr[i]]+1);
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp;
    }
}
