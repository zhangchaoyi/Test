package leetcode.common;

import java.util.*;

/**
 * 找出一个数的所有因数, 在分解质因数(只记录除数)的基础上记录 (除数和商)
 * 初始除数为2，使用当前值不断 /除数，如果可以整除，记录除数和商，商继续 /除数
 * 如果不能整除，除数+1 继续 /除数，直到除数为该数本身
 *
 * 时间复杂度 大约是 O(sqrt(n)) , 因为 假设 m=sqrt(n), 则 m * m = n ， 两个互为乘积且不相等的数=n 必定存在 一个因数 < m ， 另一个因数 > m
 *
 * 空间复杂度 O(n)
 *
 *  此方法可以分解质因数
 */
public class MathematicalFactor {

    public static void main(String[] args){

        System.out.println(getAllFactors(16));


    }

    public static Set<Integer> getAllFactors(int num){
        Set<Integer> result = new TreeSet<>(Comparator.comparing(Integer::intValue));
        result.add(1);
        result.add(num);

        int i = 2;
        while (num > i){
            if (num % i == 0) {//判断是否可以整除
                //记录除数
                result.add(i);
                int quotient = num / i;
                //记录商
                result.add(quotient);
                num = quotient;
            } else {
                i++;
            }
        }
        return result;
    }

}
