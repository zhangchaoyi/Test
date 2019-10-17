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
 * 矩阵使用数据 z[][] 记录
 * 设置 dp[][]， dp[i][]代表记录 在遍历到z[i][j]下的最长公共子序列
 *
 * 动态规划，根据矩阵可知，只要满足 z[i][j] > z[k][n] (0<k<i , o<n<j) 有dp[i][j] > dp[k][n] + 1
 *
 * @author: zhangchaoyi
 * @date: 2019/10/16
 */
public class LongestCommonSubSequence {
}
