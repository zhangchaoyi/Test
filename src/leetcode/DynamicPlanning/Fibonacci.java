package leetcode.DynamicPlanning;

/**
 * @description:动态规划解决斐波那契数列问题
 * 1.返回斐波那契数列第N项 F(N)=F(N-1) + F(N-2)
 *   O(logN)方法，如果递归式严格遵循F(N)=F(N-1)+F(N-2),对于求第N项，有矩阵乘法方式可以将时间复杂度降低至O(logN),
 *
 *                                                              n-2
 *   (F(n), F(n-1)) = (F(n-1), F(n-2)) * |1 1| =  (1, 1) * |1 1|
 *                                       |1 0|             |1 0|
 *   所以求斐波那契数列第N项变成如何用最快的方法求一个矩阵的N次方
 *
 *
 * 2.给定整数N，代表台阶数，一次可以跨2个或者1个台阶，返回有多少种走法
 *     N=3, 可以三次都跨1个台阶；也可以先跨2个台阶，再跨1个台阶；也可以先跨1个台阶，再跨2个台阶
 *     如果台阶有N级，最后跳上第N级的一步，一定来源于两种情况之一：1.从N-2直接跨两级  2.从N-1直接跨一级。因此F(N) = F(N-1) + F(N-2)
 *
 * 3.母牛每年只生育一头小母牛，并且永远不会死，第一年农场有1只成熟的母牛；从第二年开始，母牛开始生小母牛，每只小母牛三年后成熟又可以生小母牛
 *  给定整数N，求N年后牛的数量
 *      N=6,第一年1头成熟的母牛为a，第二年a生了新的小母牛，记为b，总牛数为2；第三年a生了新的小母牛，记为c，总牛数为3；第四年a生了新的小母牛，记为d，
 *      总牛数为4；第五牛b成熟了，a和b分别生了新的小母牛，总牛数为6；第六年a b c 分别生了新的小母牛，总牛数为9，返回9
 *
 *      考虑N, 因为所有牛都不会死，所以第N-1年的牛会活到第N年，同时所有成熟的牛都会生1头新的牛，所以第N年的牛就是第N-1年的所有牛加上成熟的牛
 *      而成熟的牛实际上就是第N-3年的所有牛
 *      f(n) = f(n-1) + f(n-3)
 *
 * @author: zhangchaoyi
 * @date: 2019/10/21
 */
public class Fibonacci {

    public static void main(String[] args){
        System.out.println(f3(4));
    }

    public static int f3(int n){
        if (n<1) {
            return 0;
        }
        if (n==1 || n==2) {
            return 1;
        }
        int[][] base = {{1,1}, {1,0}};
        int[][] res = matrixPower(base, n-2);
        return res[0][0] + res[1][0];
    }

    /**
     * 求矩阵m的p次方
     * 将幂次转成2进制，比如 10101010111， 如果该位是1，才进行乘积相乘
     * @param m
     * @param p
     * @return
     */
    public static int[][] matrixPower(int[][] m, int p){
        int[][] res = new int[m.length][m[0].length];

        //先把res设为单位矩阵，相当于整数中的1
        for(int i=0;i<res.length;i++){
            res[i][i] = 1;
        }
        int[][] tmp = m;

        for(;p!=0;p>>=1){
            if((p&1)!=0){
                //如果该位是1，才进行次幂的乘积相乘
                res = muliMatrix(res, tmp);
            }
            //得到对应的次幂
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    /**
     * 两个矩阵相乘
     * @param m1
     * @param m2
     * @return
     */
    public static int[][] muliMatrix(int[][] m1, int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];

        for(int i=0;i<m1.length;i++){
            for(int j=0;j<m2[0].length;j++){
                for(int k=0;k<m2.length;k++){
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return res;
    }
}
