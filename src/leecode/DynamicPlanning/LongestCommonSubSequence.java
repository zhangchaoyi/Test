package leecode.DynamicPlanning;

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
 *
 * @author: zhangchaoyi
 * @date: 2019/10/16
 */
public class LongestCommonSubSequence {

    public static void main(String[] args) {
        String str1="1A2C3D4B56";
        String str2="B1D23CA45A";

        int[][] dp = getDp(str1, str2);

        LongestCommonSubstring.printArray(dp);
    }

    public static int[][] getDp(String str1, String str2){
        int[][] dp = new int[str1.length()][str2.length()];

        //特殊处理边界
        if(str1.charAt(0) == str2.charAt(0)){
            dp[0][0] = 1;
        }
        for(int i=1;i<str1.length();i++){
            dp[i][0] = Math.max(dp[i-1][0], str1.charAt(i)==str2.charAt(0)?1:0);
        }
        for(int j=1;j<str2.length();j++){
            dp[0][j] = Math.max(dp[0][j-1], str1.charAt(0)==str2.charAt(j)?1:0);
        }

        for(int i=1;i<str1.length();i++){
            for(int j=1;j<str2.length();j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
                }
//                if(i==0||j==0){
//                    if(str1.charAt(i)==str2.charAt(j)){
//                        dp[i][j] = 1;
//                    }
//                } else {
//                    if (str1.charAt(i) == str2.charAt(j)) {
//                        dp[i][j] = dp[i-1][j-1] + 1;
//                    } else {
//                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
//                    }
//                }
            }
        }

        return dp;
    }
}
