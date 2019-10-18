package leecode.string;

/**
 * @description: 返回两个字符串的最长公共子序列
 * str1="1A2C3D4B56"; str2="B1D23CA45B6A"; 返回123456或者12C4B6
 *
 *    B 1 D 2 3 C A 4 5 B 6 A
 *  1 0 1 0 0 0 0 0 0 0 0 0 0
 *  A 0 0 0 0 0 0 1 0 0 0 0 1
 *  2 0 0 0 1 0 0 0 0 0 0 0 0
 *  C 0 0 0 0 0 1 0 0 0 0 0 0
 *  3 0 0 0 0 1 0 0 0 0 0 0 0
 *  D 0 0 1 0 0 0 0 0 0 0 0 0
 *  4 0 0 0 0 0 0 0 1 0 0 0 0
 *  B 1 0 0 0 0 0 0 0 0 1 0 0
 *  5 0 0 0 0 0 0 0 0 1 0 0 0
 *  6 0 0 0 0 0 0 0 0 0 0 1 0
 *
 *  dp[str1.length()][str2.length()]
 *  dp[i][j] 表示 str1[0....i] 和 str2[0....j] 的最长公共子序列的长度
 *  则满足 dp[i][j] = max{
 *                      dp[i-1][j],
 *                      dp[i][j-1],
 *                      dp[i-1][j-1] + 1 ,(要求str[i]==str[j])
 *                      }
 *
 * @author: zhangchaoyi
 * @date: 2019/10/16
 */
public class LongestCommonSubSequence {
}
