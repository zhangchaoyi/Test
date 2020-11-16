package leetcode.DynamicPlanning.recursion;

/**
 * n皇后
 * 在 N * N 的棋盘要摆 N 个皇后，要求任何两个皇后不同行，不同列，也不在同一条斜线
 * 给定一个整数n，返回 n 皇后的摆法有多少种
 */
public class NQueue {

    public static void main(String[] args){
        NQueue nq = new NQueue();
        System.out.println(nq.num(8));
    }

    public int num(int n){
        if(n < 1){
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    public int process(int i, int[] record, int n){
        if (i==n) {
            return 1;
        }
        int res = 0;
        for(int j=0;j<n;j++){
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(i+1, record, n);
            }
        }
        return res;
    }

    public boolean isValid(int[] record, int i, int j){
        for(int k=0;k<i;k++){
            if(j==record[k] || Math.abs(record[k]-j)==Math.abs(i-k)){
                return false;
            }
        }
        return true;
    }
}
