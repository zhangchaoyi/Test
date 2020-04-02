package leetcode.common;

import java.util.Arrays;

/**
 * 给你一个整数 num，请你找出同时满足下面全部要求的两个整数：
 *
 * 两数乘积等于  num + 1 或 num + 2
 * 以绝对差进行度量，两数大小最接近
 * 你可以按任意顺序返回这两个整数。
 *
 *
 * 示例 1：
 *
 * 输入：num = 8
 * 输出：[3,3]
 * 解释：对于 num + 1 = 9，最接近的两个因数是 3 & 3；对于 num + 2 = 10, 最接近的两个因数是 2 & 5，因此返回 3 & 3 。
 * 示例 2：
 *
 * 输入：num = 123
 * 输出：[5,25]
 * 示例 3：
 *
 * 输入：num = 999
 * 输出：[40,25]
 *
 *  思路，由于要求两数最接近，可以考虑 从sqrt(num+2)倒叙遍历，找出可以被 num+1 或 num+2 整除的两个数
 *
 *  为什么选sqrt(num+2)？因为 n * n = num，此时两个数的绝对差值最接近，从sqrt(num+2)倒叙出发，两个数的绝对差值只会越来越大
 *
 *  为什么选sqrt(num+2)不选sqrt(num+1)？ 因为sqrt(num+2)的值比sqrt(num+1)大, 涵盖了范围
 */
public class NearestFactor {

    public static void main(String[] args){
        NearestFactor nf = new NearestFactor();
        int[] result = nf.closestDivisors(999);
        System.out.println(Arrays.toString(result));
    }

    public int[] closestDivisors(int num) {
        int[] result = new int[2];
        int sqrt = (int)Math.ceil(Math.sqrt(num+2));

        for(int i=sqrt;i>1;i--){
            if((num+2)%i==0){
                result[0] = i;
                result[1] = (num+2)/i;
                break;
            }
            if((num+1)%i==0){
                result[0] = i;
                result[1] = (num+1)/i;
                break;
            }
        }

        return result;
    }
}
