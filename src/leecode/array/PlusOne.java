package leecode.array;

import java.util.Arrays;

/**
 * Created by zcy on 18-5-17.
 * 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。

 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。

 你可以假设除了整数 0 之外，这个整数不会以零开头。

 输入: [1,2,3]
 输出: [1,2,4]
 解释: 输入数组表示数字 123。

 输入: [4,3,2,1]
 输出: [4,3,2,2]
 解释: 输入数组表示数字 4321。
 */
public class PlusOne {

    public static int[] plusOne(int[] digits) {
        int add = 1;
        int highBit = digits[0];

        for(int i=digits.length - 1; i>=0; i--){
            if(digits[i] + add == 10){
                digits[i] = 0;
                add = 1;
            } else {
                digits[i] += add;
                add = 0;
            }
        }

        if(add == 1){
            int[] result = new int[digits.length + 1];
            if(highBit == 9){
                result[0] = 1;
            } else {
                result[0] = highBit + 1;
            }
            for(int i=1;i<result.length;i++){
                result[i] = digits[i-1];
            }

            return result;
        }

        return digits;
    }

    public static void main(String[] args){
        int[] a = new int[]{9, 9, 9, 9};
        print(PlusOne.plusOne(a));

    }

    public static void print(int[] a){
        for(int n : a){
            System.out.print(n + " ");
        }
    }
}
