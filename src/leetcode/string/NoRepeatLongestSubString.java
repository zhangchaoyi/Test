package leetcode.string;

import leetcode.DynamicPlanning.LongestCommonSubstring;

import java.util.Arrays;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 *  示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * dp[i][j] 表示 s(i,j) 的无重复字符的最长子串长度 i<j
 * dp[i][j] = dp[i][j-1] + 1 , s(i,j)无重复
 *          = 1 , dp[i][j-1] 包含 s(j)
 *
 *  @Author: chaoyi.zhang
 * @Date: 2020/11/7 11:42
 */
public class NoRepeatLongestSubString {

    /**
     * 动态规划，超出内存限制，超出时间限制
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if(s==null || s.length()==0){
            return 0;
        }
        if(s.length()==1){
            return 1;
        }
        int[][] dp = new int[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            dp[i][i] = 1;
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
                if (!s.substring(i,j).contains(String.valueOf(s.charAt(j)))) {
                    dp[i][j] = dp[i][j-1] + 1;
                } else {
                    dp[i][j] = 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        LongestCommonSubstring.printArray(dp);

        return max;
    }

    /**
     * 动态规划，压缩空间， 超出时间限制
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        if(s==null || s.length()==0){
            return 0;
        }
        if(s.length()==1){
            return 1;
        }
        int[] dp = new int[s.length()];
        /**
         * 压缩一维数组时，在外层循环要清空状态重新赋值，同时注意赋值dp[i]=1
         */
        int max = Integer.MIN_VALUE;
        for(int i=0;i<s.length();i++){
            Arrays.fill(dp, 0);
            dp[i]=1;
            for(int j=i+1;j<s.length();j++){
                if (!s.substring(i,j).contains(String.valueOf(s.charAt(j)))) {
                    dp[j] = dp[j-1] + 1;
                } else {
                    dp[j] = 1;
                }
                max = Math.max(max, dp[j]);
            }
        }
        System.out.println(Arrays.toString(dp));

        return max;
    }

    /**
     * 左右双指针，右指针不断每右移一位， 判断是否包含，如果包含，左指针移到相同的字符的后一位； 中途不断记录区间的大小
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        return 0;
    }

    public static void main(String[] args){
        String s = new String("pwwkew");

        NoRepeatLongestSubString nlss = new NoRepeatLongestSubString();
        System.out.println(nlss.lengthOfLongestSubstring(s));
    }
}
