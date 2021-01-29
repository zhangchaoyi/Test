package leetcode.bit;

/**
 * 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * 思路：两个数进行异或，然后判断1的个数
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:52
 */
public class HammingDistance {

    public int hammingDistance(int x, int y) {

        int res = x^y;
        System.out.println(Integer.bitCount(res));
        int count=0;
        while (res!=0) {
            count++;
            res = res&(res-1);//此位运算可以不断消除1
        }

        return count;
    }



    public static void main(String[] args){
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance(1, 4));
    }
}
