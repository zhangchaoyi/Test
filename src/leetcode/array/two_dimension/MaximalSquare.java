package leetcode.array.two_dimension;

/**
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 示例：
 *
 * 输入：
 * matrix = [['1','0','1','0','0'],
 *           ['1','0','1','1','1'],
 *           ['1','1','1','1','1'],
 *           ['1','0','0','1','0']]
 *
 * 输出：4
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:27
 *
 * 思路：遍历二维数组，每个节点 拓展判断
 */
public class MaximalSquare {

    private int max = 0;

    /**
     * 时间复杂度：O(mn min(m,n)^2) 其中 mm 和 nn 是矩阵的行数和列数。
     *
     * 需要遍历整个矩阵寻找每个 11，遍历矩阵的时间复杂度是 O(mn)
     * 对于每个可能的正方形，其边长不超过 mm 和 nn 中的最小值，需要遍历该正方形中的每个元素判断是不是只包含 11，遍历正方形时间复杂度是 O(min(m,n)^2)
     * 总时间复杂度是 O(mn min(m,n)^2)
     * 空间复杂度：O(1)
     * 额外使用的空间复杂度为常数。
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix.length==0){
            return 0;
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if (matrix[i][j]=='1') {
                    //对于每一个1进行判读是否>初始值0
                    max = Math.max(max, 1);
                    getTempMax(matrix, i, j);
                }
            }
        }
        return max;
    }

    /**
     * 更新以 r,c 为左顶点的最大矩阵面积
     * @param matrix
     * @param r
     * @param c
     */
    private void getTempMax(char[][] matrix, int r, int c){
        int rolLimit = matrix.length;
        int colLimit = matrix[0].length;
        int add = 1;
        while(r+add<rolLimit && c+add<colLimit){
            if (matrix[r+add][c+add] != '1') {
                return;
            }
            for(int i=r;i<r+add;i++){
                if(matrix[i][c+add] != '1'){
                    return;
                }
            }
            for(int i=c;i<c+add;i++){
                if(matrix[r+add][i] != '1'){
                    return;
                }
            }
            int temp = (int)Math.pow(add+1, 2);
            if(temp > max){
                System.out.println(r+","+c);
                max = temp;
            }
            add++;
        }
    }

    public static void main(String[] args){
//        char[][] matrix = new char[][]{
//                {'1','0','1','0','0'},
//                {'1','0','1','1','1'},
//                {'1','1','1','1','1'},
//                {'1','0','0','1','0'}
//        };

        char[][] matrix = new char[][]{
                {'0','1','1','0','0','1','0','1','0','1'},
                {'0','0','1','0','1','0','1','0','1','0'},
                {'1','0','0','0','0','1','0','1','1','0'},
                {'0','1','1','1','1','1','1','0','1','0'},
                {'0','0','1','1','1','1','1','1','1','0'},
                {'1','1','0','1','0','1','1','1','1','0'},
                {'0','0','0','1','1','0','0','0','1','0'},
                {'1','1','0','1','1','0','0','1','1','1'},
                {'0','1','0','1','1','0','1','0','1','1'}};
        
        
        MaximalSquare ms = new MaximalSquare();
        System.out.println(ms.maximalSquare(matrix));
    }
}
