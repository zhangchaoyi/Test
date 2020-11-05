package leetcode.array.two_dimension;

import leetcode.DynamicPlanning.LongestCommonSubstring;

/**
 * 顺时针旋转二维矩阵
 *  1   2   3   4
 *  5   6   7   8
 *  9  10  11  12
 * 13  14  15  16
 *
 *  row=3   col=3
 *
 * (0,0) -> (0,3) 、 (0,1) -> (1,3) 、 (0,2) -> (2,3)  、 (0,3) -> (3,3)    (i,j) -> (j,col-i)
 * (1,0) -> (0,2)    (1,1) -> (1,2)   (1,2) -> (2,2)     (1,3) -> (3,2)    (i,j) -> (j,col-i)
 * (2,0) -> (0,1)    (2,1) -> (1,1)   (2,2) -> (2,1)     (2,3) -> (3,1)    (i,j) -> (j,col-i)
 * (3,0) -> (0,0)    (3,1) -> (1,0)   (3,2) -> (2,0)     (3,3) -> (3,0)    (i,j) -> (j,col-i)
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/5 10:34
 */
public class RotateMaxtrix {

    public static void main(String[] args){
        int[][] source = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        RotateMaxtrix rm = new RotateMaxtrix();
        rm.rotate(source);
        LongestCommonSubstring.printArray(source);
    }

    private int[][] rotate(int[][] source){
        boolean[][] mark = new boolean[source.length][source[0].length];

        for(int i=0;i<source.length;i++){
            for(int j=0;j<source[0].length;j++){
                if (!mark[i][j]) {
                    changePosition(source, mark, i, j, source[i][j]);
                }
            }
        }

        return source;
    }

    private void changePosition(int[][] source, boolean[][] mark, int rowIndex, int colIndex, int value){
        int COL = source[0].length-1;
        if (mark[rowIndex][colIndex]) {
            return;
        }
        //保存下一个位置的值，index
        int nextRowIndex = colIndex;
        int nextColIndex = COL-rowIndex;
        int nextValue = source[colIndex][COL-rowIndex];
        source[colIndex][COL-rowIndex] = value;
        /**表示当前已完成换位**/
        mark[rowIndex][colIndex] = true;
        changePosition(source, mark, nextRowIndex, nextColIndex, nextValue);
    }
}
