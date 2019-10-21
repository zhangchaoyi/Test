package leecode.DynamicPlanning.recursion;

/**
 * @description: 斐波那契数列  1,1,2,3,5,8..... 求它的第N项
 *
 * @author: zhangchaoyi
 * @date: 2019/10/21
 */
public class Fibonacci {

    public static void main(String[] args){
        System.out.println(orderFi(7));
    }

    /**
     * 递归解决，O(N*N)
     * @param n
     * @return
     */
    public static int fi(int n){
        if (n==1||n==2) {
            return 1;
        } else {
            return fi(n-1) + fi(n-2);
        }

    }

    /**
     * 直接遍历 O(N)
     * @return
     */
    public static int orderFi(int n){
        if(n<=2){
            return 1;
        }

        int pre2 = 1;//前两项
        int pre1 = 1;//前一项

        int cur = 0;
        for(int i=2;i<n;i++){
            cur = pre1 + pre2;
            //为下一次记录
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}
