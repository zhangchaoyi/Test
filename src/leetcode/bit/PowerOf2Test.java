package leetcode.bit;

/**
 * @description: 判断一个数是否2的n次方
 * 因为如果是2的n次方，则换成二进制后只有某位等于1
 * @author: zhangchaoyi
 * @date: 2019/7/19
 */
public class PowerOf2Test {

    public static void main(String[] args){
//        System.out.println(isPowerOf2(8));

//        System.out.println(powerOf2(9));

//        int k = 1;
//        System.out.println(k<<34);
        System.out.println(Integer.toBinaryString(10));
    }

    public static boolean isPowerOf2(int n){
        int count = 0;
        int k = 1;
        /**
         * 当左移31位时二进制变成 1000 0000 0000 0000 0000 0000 0000 0000， 因为java32位中首位是符号位，因此该数的实际值是 -2147483648
         * 当上面数字继续左移一位则变成0
         * 如果继续左移超过32位则 实际的位移数最终变成对32取模， 比如左移33位实际上是左移1位
         */
        while(k != 0){//随着k不断左移，会变成负数
            if ((n & k) != 0) {//判断与k对应的位置上是否有1
                count++;
            }
            k = k << 1;
        }

        return count == 1;
    }

    public static boolean powerOf2(int n){
        return (n & (n-1)) == 0;
    }
}
