package leetcode.DynamicPlanning;

import java.util.Arrays;

/**
 * @description: 300.最长递增子序列
 * arr=[2,1,5,3,6,4,8,9,7] 返回的最长递增子序列[1,3,4,8,9]
 *
 * 动态规划：生成长度为 N 的数据dp[], dp[i]表示在arr[i]这个数的结尾下 arr[0.....i]中的最大递增子序列长度
 * 从左到右依次遍历，计算出每个位置的最大长度 dp[]
 * 假设到位置 i , 如果最长递增子序列以arr[i]结尾，那么arr[0.... i-1]中所有比arr[i]小的数都可以作为倒数第二个数，所以dp[i]是以倒数第二个数结尾的数中最大的递增子序列 + 1， dp[i] = max{dp[j]+1 (0<=j<i, arr[j]<arr[i])}
 * 如果arr[0.... i-1]中所有数都不比arr[i]小，则 dp[i]=1即可
 *
 * 得到了dp[]数组之后，对dp进行一次遍历得到dp最大的数即最大递增子序列的长度 len 和该数所在的 index， index表示 最大递增子序列是以arr[index]结尾
 *
 * 对arr从index进行倒序遍历，寻找最大子序列倒数第二个数 arr[j]， 满足一个条件 arr[index] > arr[j] && dp[index] = dp[j] + 1
 *
 * 寻找倒数第三个数同上
 *
 * 时间复杂度：O(N * N + N)
 *
 * @author: zhangchaoyi
 * @date: 2019/10/14
 */
public class LongestIncreaseSubArray {

    public static void main(String[] args){
        int[] arr = new int[]{2,1,5,3,6,4,8,9,7};

        int[] dp = getDp(arr);

        System.out.println(Arrays.toString(dp));

        int[] result = getMaxSubArray(arr, dp);

        System.out.println(Arrays.toString(result));
    }

    /**
     * 获取遍历的每一位的最长递增子序列长度
     * @return
     */
    public static int[] getDp(int[] arr){
        int[] dp = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            dp[i] = 1;
            for(int j=0; j<i; j++){
                if (arr[j] < arr[i]) {//只要比arr[i]小的数都有可能是以arr[i]结尾的最大子序列的倒数第二个数，所以我们要取最大的倒数第二个数的子序列
                    dp[i] = Math.max(dp[i], dp[j] + 1);//取内层遍历以来的最大dp[i] 和 dp[j]+1 的最大值
                }
            }
        }
        return dp;
    }

    public static int[] getMaxSubArray(int[] arr, int[] dp){
        //找出dp中最大的数所在的位置
        int index = 0;
        int len = 0;
        for(int i=0; i<dp.length; i++){
            if (dp[i] >= len) {
                len = dp[i];
                index = i;
            }
        }
        //已知最大的子序列长度和最后一个数所在的位置，找它倒数第二个数，倒数第二个数满足 d[i] = dp[j] + 1 & arr[i] > arr[j]
        int[] result = new int[len];
        result[--len] = arr[index];
        for(int j=index-1; j>0; j--){
            if (arr[j] < arr[index] && dp[index] == dp[j] + 1) {
                result[--len] = arr[j];
                index = j;
            }
        }
        return result;
    }



}
