package leetcode.bit;

/**
 * @description: 位运算技巧
 * @author: zhangchaoyi
 * @date: 2019/10/22
 */
public class BitTest {

    public static void main(String[] args) {
//        System.out.println(isTwoPower(7));
//
//        int len = getTwoPower(5);
//        System.out.println(len);
//        System.out.println(getRemainder(100, len));
        rightMove();
    }

    /**
     * 判断一个数是否2的n次幂
     * @param num  传入数
     * @return
     */
    public static boolean isTwoPower(int num){
        return (num & (num-1)) == 0;
    }

    /**
     * 计算2的n次方
     * @param n  次幂
     * @return
     */
    public static int getTwoPower(int n){
        return 1 << n;//2<<n-1
    }

    /**
     * 对2的n次方取余, hashMap
     * @param h  被除数
     * @param len   模(要求是2的n次幂)
     * @return
     */
    public static int getRemainder(int h, int len){
        return h&(len-1);
    }

    /**
     * 判断十进制数字 num 转为二进制后 在某位的 position 的数字是 1 or 0
     * 将num右移 (position-1) 即将position位放到第一位， 此时再和 0001 做与运算
     * @param num 十进制数
     * @param position 二进制第几位
     * @return
     */
    public static int getBit(int num, int position){
        return (num >> (position-1)) & 1;
    }

    /**
     * 比较 >>> 和 >>
     * >>> 是无符号右移动
     * >> 是有符号右移
     * @return
     */
    public static void rightMove(){
        System.out.println((1+Integer.MIN_VALUE)>>1);//因为1+max之后已经移除，符号位变成1，此时如果右移，则符号位的左侧进行补充1
        System.out.println((1+Integer.MIN_VALUE)>>>1);//无符号右移则在符号位左侧补充0，此时变成正数
    }


}
