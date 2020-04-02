package leetcode.common;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 输入：nums = [21,4,7]
 * 输出：32
 * 解释：
 * 21 有 4 个因数：1, 3, 7, 21
 * 4 有 3 个因数：1, 2, 4
 * 7 有 2 个因数：1, 7
 * 答案仅为 21 的所有因数的和。
 *
 * 解法一：分解质因数的基础上 同时保留除数和商
 *
 * 解法二：一个数n只有四个因数，除去 1 和 n 本身，只有互为乘积=n的两个数，而且两个数一定不相等
 *  可以遍历从 2 到 开平方sqrt(n)，判断i是否能被n整除， 记录所有的 i 值 与 n/i 值，保留只有两个的情况
 *
 *  为什么可以遍历到 sqrt(n) ?  假设 m=sqrt(n), 则 m * m = n ， 由于两个因数不相等，则一定有"一个因数小于m ， 另一个因数大于m"
 *  因此只需要在遍历到 sqrt(n) 的时候，对于每个可以整除的i，同时记录 i 与 n/i ,
 *
 *  数组长度为 N，数组中最大的数为 M， 时间复杂度 O(N * sqrt(n)), 空间复杂度 O(1)
 *
 */
public class FourFactors {

    public static void main(String[] args){
        int[] nums = new int[]{21,4,7};

        FourFactors ff = new FourFactors();
        System.out.println(ff.sumFourDivisors(nums));
    }

    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for(int num : nums){
            Set<Integer> factors = MathematicalFactor.getAllFactors(num);
            if (factors.size() == 4) {
                sum += factors.stream().collect(Collectors.summingInt(factor -> factor.intValue()));
            }
        }
        return sum;
    }



}
