package leecode.DynamicPlanning;

/**
 * @description:动态规划解决斐波那契数列问题
 * 1.返回斐波那契数列第N项
 * 2.给定整数N，代表台阶数，一次可以跨2个或者1个台阶，返回有多少种走法
 * 3.母牛每年只生育一头小母牛，并且永远不会死，第一年农场有1只成熟的母牛；从第二年开始，母牛开始生小母牛，每只小母牛三年后成熟又可以生小母牛
 *  给定整数N，求N年后牛的数量
 *
 * @author: zhangchaoyi
 * @date: 2019/10/21
 */
public class Fibonacci {

    public static void main(String[] args){

    }

    /**
     * 求矩阵m的p次方
     * @param m
     * @param p
     * @return
     */
    public int[][] matrixPower(int[][] m, int p){
        int[][] res = new int[m.length][m[0].length];

        //先把res设为单位矩阵，相当于整数中的1
        for(int i=0;i<res.length;i++){
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for(;p!=0;p>>=1){
            if((p&1)!=0){
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return tmp;
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
