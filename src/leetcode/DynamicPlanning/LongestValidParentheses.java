package leetcode.DynamicPlanning;

import java.util.Arrays;
import java.util.Objects;

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
        char[] chars = s.toCharArray();
        //maxLength[i]表示以i开头的最长有效括号长度
        int[] maxLength = new int[chars.length];
        int maxLen = 0;
        for(int i=0;i<chars.length;i++){
            char last = chars[i];
            if (Objects.equals(last, ')')) {
                maxLength[i]=0;
                continue;
            }
            int curMaxLen = 1;
            for(int j=i+1;j<chars.length;j++){
                if (Objects.equals(last, '(') && Objects.equals(chars[j], ')')) {
                    curMaxLen++;
                } else if(Objects.equals(last, ')') && Objects.equals(chars[j], '(') && curMaxLen > 0){
                    curMaxLen++;
                }
                last = chars[j];
            }
            if(curMaxLen > maxLen){
                maxLen = curMaxLen;
            }
            maxLength[i]=curMaxLen;
        }

        System.out.println(Arrays.toString(maxLength));
        System.out.println(maxLen);
    }


}
