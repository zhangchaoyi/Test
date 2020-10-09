package leetcode.DynamicPlanning;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

/**
 * 32. 最长有效括号
 *给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * 暴力破解O(n^2)
 *
 *
 */
public class LongestValidParentheses {

    public static void main(String[] args){
        String s = ")()())";
        LongestValidParentheses lv = new LongestValidParentheses();
        lv.longestValidParentheses(s);
    }

    /**
     * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/dong-tai-gui-hua-si-lu-xiang-jie-c-by-zhanganan042/
     *
     * if s[i] == '(' :
     *     dp[i] = 0
     * if s[i] == ')' :
     *     if s[i - 1] == '(' :
     *         dp[i] = dp[i - 2] + 2 #要保证i - 2 >= 0
     *
     *     if s[i - 1] == ')' and s[i - dp[i - 1] - 1] == '(' :
     *         dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2 #要保证i - dp[i - 1] - 2 >= 0
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {

    }

}
