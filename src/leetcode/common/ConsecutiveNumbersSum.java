package leetcode.common;

/**
 * 给定一个正整数 N，试求有多少组连续正整数满足所有数字之和为 N?
 * 示例 1:
 *
 * 输入: 5
 * 输出: 2
 * 解释: 5 = 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
 * 示例 2:
 *
 * 输入: 9
 * 输出: 3
 * 解释: 9 = 9 = 4 + 5 = 2 + 3 + 4
 * 示例 3:
 *
 * 输入: 15
 * 输出: 4
 * 解释: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 * 说明: 1 <= N <= 10 ^ 9
 *
 *  找规律
 *  一个数时， 为本身  ---->  N - 0 可以被 1 整除
 *  两个数时， m + (m+1) = N  ---->  N - 1 可以被 2 整除
 *  三个数时， m + (m+1) + (m+2) = N  ------>  N - 1 - 2 可以被 3 整除
 *  四个数时， m + (m+1) + (m+2) + (m+3) ------> N - 1 - 2 - 3 可以被 4 整除
 *  i 个数时， N - (1+2..+ i-1) 可以被 i 整除
 */
public class ConsecutiveNumbersSum {

    public static void main(String[] args){
        ConsecutiveNumbersSum c = new ConsecutiveNumbersSum();

        System.out.println(c.consecutiveNumbersSum(15));
    }

    public int consecutiveNumbersSum(int N) {
        int result = 0;
        int i = 1;
        while(N > 0){
             if(N % i == 0){
                result += 1;
            }
            N -= i;
            i += 1;
        }
        return result;
    }
}
