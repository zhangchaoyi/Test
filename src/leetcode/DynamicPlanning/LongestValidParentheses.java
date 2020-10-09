package leetcode.DynamicPlanning;

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
 * 假设dp[i]为以i结尾的有效括号子串长度，则只需找出dp[]的 max
 *
 * 状态方程：
 * 则对于 i='(' , dp[i]=0
 * 对于 i=')' , 如果前一位是'(' , 则dp[i]=dp[i-2]+2 ;
 *              如果前一位是')' , 则位置i的对应左括号位置是 i-dp[i-1]-1 , 要满足括号有效子串，要求 s[i-dp[i-1]-1] == '(' , 此时 dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]，   -- dp[i-dp[i-1]-2]是之前的独立有效括号段
 *
 *
 *
 */
public class LongestValidParentheses {

    public static void main(String[] args){
        String s = "(()";
        LongestValidParentheses lvp = new LongestValidParentheses();
        System.out.println(lvp.longestValidParentheses(s));

    }

    public int longestValidParentheses(String s){
        int[] dp = new int[s.length()];
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            if (chars[i]=='(') {
                dp[i] = 0;
                continue;
            }
            if(chars[i]==')' && i>1){
                //判断前一位
                if(chars[i-1]=='('){
                    dp[i]=dp[i-2]+2;
                } else {
                    if(chars[i-dp[i-1]-1]=='('){
                        dp[i] = dp[i-1]+2 + dp[i-dp[i-1]-2];
                    }
                }
            } else {
                if (i==0) {
                    dp[0]=0;
                } else if(i==1){
                    if(chars[0]=='('){
                        dp[1]=2;
                    }
                }
            }
        }
        //找出dp中的最大值
        int max = dp[0];
        for(int i=1;i<dp.length;i++){
            if (dp[i]>max) {
                max=dp[i];
            }
        }
        return max;
    }

}
