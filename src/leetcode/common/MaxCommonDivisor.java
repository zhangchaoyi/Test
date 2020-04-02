package leetcode.common;

/**
 * 找出两个数的最大公约数
 * 假设 M N 两个数， 其中 M > N
 *
 * 1.分解两个数的质因数，使用公共的质因数相乘
 *
 *
 * 2.更相减损法
 * 	2.1 两数相减，大数减小数
 * 	2.2 得出的差与小数继续循环 2.1， 直到最后差与小数相等
 *
 *
 * 3.辗转相除法
 * 	两数消除，用大数/小数，如果余数r不为0，继续小数/r, 往复以上流程，直到最后的余数为0停止
 *
 *
 * 4.穷举法， 两个数从较小数开始遍历递减到0，看能否出现同时被两个数整除
 * 时间复杂度 O(N)
 */
public class MaxCommonDivisor {

    public static void main(String[] args){
        int a = 21;
        int b = 36;
//        System.out.println(findMaxCommonDivisor(a, b));
        System.out.println(findMaxCommonDivisor2(a, b));
    }

    /**
     * 更相减损法
     * 时间复杂度 O(n)
     * @param a
     * @param b
     * @return
     */
    public static int findMaxCommonDivisor(int a, int b){
        int max, min=0;
        if(a > b){
            max = a;min=b;
        } else {
            max = b;min=a;
        }

        int diff = max - min;

        while (diff != min) {
            if (min > diff) {
                max = min;
                min = diff;
            } else {
                max = diff;
                min = min;
            }
            diff = max - min;
        }
        return diff;
    }

    /**
     * 辗转相除法，返回最后一次的除数
     * 时间复杂度 O(n)
     * @param a
     * @param b
     * @return
     */
    public static int findMaxCommonDivisor2(int a, int b){
        int max, min=0;
        if(a > b){
            max = a;min=b;
        } else {
            max = b;min=a;
        }
        int reminder = max % min;
        while (reminder != 0) {
            if (min > reminder) {
                max = min;
                min = reminder;
            } else {
                max = reminder;
                min = min;
            }
            reminder = max % min;
        }
        return min;
    }
}
