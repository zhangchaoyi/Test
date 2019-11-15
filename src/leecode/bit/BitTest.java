package leecode.bit;

/**
 * @description: 位运算技巧
 * @author: zhangchaoyi
 * @date: 2019/10/22
 */
public class BitTest {

    public static void main(String[] args) {
        System.out.println(isTwoPower(7));

        int len = getTwoPower(5);
        System.out.println(len);
        System.out.println(getRemainder(100, len));
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
     * 对2的n次方取余
     * @param h  被除数
     * @param len   模(要求是2的n次幂)
     * @return
     */
    public static int getRemainder(int h, int len){
        return h&(len-1);
    }
}
