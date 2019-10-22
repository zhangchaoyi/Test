package leecode.DynamicPlanning;

import java.util.Arrays;

/**
 * @description: 返回两个字符串的最长公共子序列
 * str1="1A2C3D4B567"; str2="B1D23CA45B6A"; 返回123456或者12C4B6
 *
 *  dp[str1.length()][str2.length()]
 *  dp[i][j] 表示 str1[0....i] 和 str2[0....j] 的最长公共子序列的长度
 *  则满足 dp[i][j] = max{
 *                      dp[i-1][j],--- 代表str1[0...i-1]和str2[0...j]中的最长公共子序列长度
 *                      dp[i][j-1],--- 代表str1[0...i]和str2[0...j-1]中的最长公共子序列长度
 *                      dp[i-1][j-1] + 1 ,(要求str[i]==str[j])
 *                      }
 * 时间复杂度 O(M*N) + O(max{M,N})
 * @author: zhangchaoyi
 * @date: 2019/10/16
 */
public class LongestCommonSubSequence {

    public static void main(String[] args) {
        String str1="1A2C3D4B567";
        String str2="B1D23CA45B6A";

        int[][] dp = getDp(str1, str2);

        LongestCommonSubstring.printArray(dp);

        System.out.println(Arrays.toString(getRes(dp)));

    }

    public static int[][] getDp(String str1, String str2){
        int[][] dp = new int[str1.length()][str2.length()];

        //特殊处理边界
        if(str1.charAt(0) == str2.charAt(0)){
            dp[0][0] = 1;
        }
        /*
        处理第一列，此时比较 str1[i] 和 str2[0]，因此dp[i][0]最多是1
         */
        for(int i=1;i<str1.length();i++){
            dp[i][0] = Math.max(dp[i-1][0], str1.charAt(i)==str2.charAt(0)?1:0);
        }
        /*
        处理第一行
         */
        for(int j=1;j<str2.length();j++){
            dp[0][j] = Math.max(dp[0][j-1], str1.charAt(0)==str2.charAt(j)?1:0);
        }

        for(int i=1;i<str1.length();i++){
            for(int j=1;j<str2.length();j++){
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp;
    }

    /**
     * 将dp中的数据取出
     * dp中右下角的数字代表 str1[0...i]、str2[0...j] 中的最大子序列的长度，
     * 以(r,c) 代表当前的位置，以int[][] res保存最长子序列的数字， 则有三种移动位置，向左，向上，向左上
     * 1.如果 dp[r][c] > dp[r-1][c] && dp[r][c] > dp[r][c-1] ，则当前位置(r,c)一定有str1[r]==str2[c], 此时记录值到res，同时往左上移动 r-1, c-1
     * 2.如果 dp[r][c] == dp[r-1][c], 则往上移动 r-1
     * 3.如果 dp[r][c] == dp[r][c-1], 则往左移动 c-1
     * @param dp
     * @return
     */
    public static int[] getRes(int[][] dp){
        int r = dp.length - 1;
        int c = dp[0].length -1 ;
        int len = dp[r][c];

        int[] res = new int[len];

        while(r >=0 && c>=0 && len >= 0){
            if(r-1>=0 && dp[r][c] == dp[r-1][c]){
                r--;
            } else if(c-1>=0 && dp[r][c] == dp[r][c-1]){
                c--;
            } else {
                res[--len] = dp[r][c];
                r--;
                c--;
            }
        }

        return res;
    }
}
