package leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 *
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 *
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 *
 * 示例：
 *
 * 输入：19
 * 输出：true
 *
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 */
public class HappyNumber {

    public static void main(String[] args){
        System.out.println(isHappy2(82));
    }

    /**
     * 快慢指针破循环, 慢指针走一步，快指针走两步，如果出现循环，两个指针一定会在某一个数相遇；如果不循环，其中一个指针先到 1
     * @param number
     * @return
     */
    public static boolean isHappy2(int number){
        int slow = number;
        int fast = number;

        do {
            slow = countHappy(slow);
            fast = countHappy(countHappy(fast));
        } while(slow != fast && slow != 1 && fast != 1);

        if(fast == 1 || slow == 1){
            return true;
        }
        return false;
    }

    public static boolean isHappy(int number){
        Set<Integer> set = new HashSet<>();

        do{
            set.add(number);
            number = countHappy(number);
        } while(number != 1 && !set.contains(number));

        if(number == 1){
            return true;
        }
        return false;
    }

    public static int countHappy(int number){
        int sum=0;
        while(number>0){
            //获取个位数字
            int bit = number%10;
            sum += bit*bit;
            number /= 10;//十进制/10相当于右移一位
        }
        return sum;
    }
}
