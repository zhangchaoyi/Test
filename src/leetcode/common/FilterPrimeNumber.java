package leetcode.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛素数
 *
 * 1.暴力遍历  O(n * n)
 *
 * 2.普通筛， 根据 "一个素数的倍数一定是合数", 从i=2开始筛除 i*2 , 缺点是存在重复筛除  O(n*loglogn)
 *
 * 3.线性筛除素数 O(n)
 * 对于一个数n(无论质数或者合数)，一次循环筛除所有小于n的最小质因子的所有质数 * i
 *
 */
public class FilterPrimeNumber {

    public static void main(String[] args){
        System.out.println(getPrimeNumber(100));
    }

    public static List<Integer> getPrimeNumber(int n){
        int[] check = new int[n];
        List<Integer> primeNumbers = new ArrayList<>();

        //遍历每一个数
        for(int i=2; i<n ;i++){
            if (check[i] == 0) {
                primeNumbers.add(i);
            }
            //处理质数列表 * i
            for(int j=0;j<primeNumbers.size();j++){
                int curPrimeNumber = primeNumbers.get(j);
                if(i * curPrimeNumber >= n){
                    break;
                }
                check[i * curPrimeNumber] = 1;
                /**
                 * 由于primeNumbers是递增，因此如果 i 能整除小的质数，可以跳出
                 *
                 *  当 i % primeNumbers[j] == 0,
                 *  考虑 i * primeNumbers[j+1] = (k * primeNumbers[j]) * primeNumbers[j+1]
                 *                             = m * primeNumbers[j];
                 *   因为 primeNumbers[j] < primeNumbers[j+1]   , 因此必定存在一个 m > i
                 */
                if (i % curPrimeNumber == 0) {
                    break;
                }
            }
        }
        return primeNumbers;
    }

}
