package leecode.common;

import java.util.*;

/**
 * 找出一个数的所有因数
 * 初始除数为2，使用当前值不断 /除数，如果可以整除，记录除数和商，商继续 /除数
 * 如果不能整除，除数+1继续 /除数，直到除数为该数本身
 *
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
            if (num % i == 0) {
                result.add(i);
                int quotient = num / i;
                result.add(quotient);
                num = quotient;
            } else {
                i++;
            }
        }
        return result;
    }

}
