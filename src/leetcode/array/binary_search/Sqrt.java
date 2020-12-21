package leetcode.array.binary_search;

/**
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 * 思路：从x进行遍历，找到满足的值，二分O(logn)
 */
public class Sqrt {

    public int mySqrt(int x) {
        int left=0;
        int right=x;

        while(left<=right){
            int mid = (left+right)>>>1;
            long square = (long)mid*mid;//避免溢出，因此提前将mid转long
            if(square==x){
                return mid;
            }
            if(square > x) {
                right=mid-1;
            } else {
                left=mid+1;
            }
        }

        return left-1;
    }

    public static void main(String[] args){
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.mySqrt(2147395599));
    }
}
