package binary;

/**
 * @description: 判断一个数是否2的n次方
 * 因为如果是2的n次方，则换成二进制后只有某位等于1
 * @author: zhangchaoyi
 * @date: 2019/7/19
 */
public class PowerOf2Test {

    public static void main(String[] args){
//        System.out.println(isPowerOf2(8));

        System.out.println(powerOf2(9));
    }

    public static boolean isPowerOf2(int n){
        int count = 0;
        int k = 1;
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
