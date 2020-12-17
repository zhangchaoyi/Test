package leetcode.bit;

import java.util.Arrays;

/**
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:30
 *
 */
public class CountBits {

    public int[] countBits(int num) {
        int[] nums = new int[num+1];
        for(int i=0;i<=num;i++){
            nums[i] = countBitsForEachNum(i);
        }
        return nums;
    }

    /**
     * 为某一个数字计算1的个数， 使用位运算 x&x-1
     *
     * 假如 x 二进制的最后一位为 1， 则 x&x-1 = x-1 , 此时正好消除了最后一位1
     * 如果 x 二进制的最后一位为 0， 则 x-1 正好会对x的最后一个1进行借位 ，x&x-1恰好消除了x的最后一个1，比如 x = 10101100 , x-1 = 10101011 , x&x-1 = 10101000
     *
     * 时间复杂度 O(nk) , n是num的位数
     *
     * @param num
     * @return
     */
    private int countBitsForEachNum(int num){
        int count = 0;
        while(num != 0){
            count++;
            num = num&num-1;
        }
        return count;

    }

    /**
     * 动态规划，根据最高位判断
     * 假如当前数是 x = 11001101 ，考虑除了最后一位的剩余数字是 1100110 + 最后一位是否为 1
     * dp[i] 表示当前数字的1的个数
     * 所以有 dp[i] = dp[i/2] + i%2  ==>  dp[i>>1] + i&1
     *
     * O(n)
     * @param num
     * @return
     */
    public int[] countBits1(int num) {
        int[] dp = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            dp[i] = dp[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
        return dp;
    }

    public static void main(String[] args){
        CountBits cb = new CountBits();
        System.out.println(Arrays.toString(cb.countBits(64)));
    }
}
