package leetcode.tree;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * @Author: chaoyi.zhang
 * @Date: 2020/11/9 10:47
 *
 * todo
 *
 */
public class NumsOfBinarySearchTree {

    public int numTrees(int n) {
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2) {
            return 2;
        }
        if(n==3) {
            return 5;
        }
        //dp[i] = dp[i-1] * 2 + dp[i-2]
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        dp[3]=5;

        for(int i=4;i<n+1;i++){
            dp[i] = dp[i-1]*2 + dp[i-2];
        }

        return dp[n];
    }

    public static void main(String[] args){
        NumsOfBinarySearchTree nbst = new NumsOfBinarySearchTree();
        System.out.println(nbst.numTrees(1));
    }
}
