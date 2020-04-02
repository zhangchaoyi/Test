package leetcode.array.two_dimension;

/**
 * @description: 顺时针遍历二维数组
 *
 *      1 0 0 0 0 0 0
 *      0 0 0 0 0 0 0
 *      0 1 0 0 0 0 0
 *      0 0 2 0 0 0 0
 *      0 0 0 3 0 0 0
 *      0 0 0 0 4 0 0
 *      0 0 0 0 0 0 0
 *      0 0 0 0 0 0 0
 *
 * 解析：从[0][0]开始以对角线为每次起点进行遍历,注意对角线不需要遍历，在一次遍历中定义当前的位置索引 curRow, curCol
 * 再定义当前的循环loop=0， 关键在于找到四次遍历的边界值
 *  按顺序 先右横向到尾，再下纵向到底，再左横向到头，再上纵向到顶， 每一次 右下左上为一次循环周期，loop++;
 *
 *
 * @author: zhangchaoyi
 * @date: 2019/10/18
 */
public class PrintClockwiseOrderly {


    public static void main(String[] args){
        int[][] z = new int[][]{
//                {1, 2, 3, 4},
//                {12,13,14,5},
//                {11,16,15,6},
//                {10,9, 8, 7}

//                {1, 2, 3, 4, 5},
//                {14,15,16,17,6},
//                {13,20,19,18,7},
//                {12,11,10,9, 8}

//                {1,2,3},
//                {8,9,4},
//                {7,6,5},

//                {1,2},
//                {12,3},
//                {11,4},
//                {10,5},
//                {9,6},
//                {8,7}

//                {1, 2, 3, 4, 5, 6, 7},
//                {26,27,28,29,30,31,8},
//                {25,44,45,46,47,32,9},
//                {24,43,54,55,48,33,10},
//                {23,42,53,56,49,34,11},
//                {22,41,52,51,50,35,12},
//                {21,40,39,38,37,36,13},
//                {20,19,18,17,16,15,14},
        };

        print(z);
    }

    public static void print(int[][] z){
        int row = 0;
        int col = 0;
        int rowLen = z.length;
        int colLen = z[0].length;

        int loop = 0;
        while(row < rowLen - loop && col < colLen - loop){
            int curRow = row;
            int curCol = col;

            //打印从左向右横向
            while(curCol < colLen - loop){
                System.out.print(z[curRow][curCol] + " ");
                curCol++;
            }
            curCol--;//下标越界处理
            curRow++;//向下跨一行
            //打印从上到下纵向
            while(curRow < rowLen - loop){
                System.out.print(z[curRow][curCol] + " ");
                curRow++;
            }
            curRow--;//防止下标越界
            curCol--;//向左跨一列
            //打印从右到左横向
            while(curCol >= 0 + loop){
                System.out.print(z[curRow][curCol] + " ");
                curCol--;
            }
            curCol++;//防止下标越界
            curRow--;//向上跨一行
            //打印从下到上纵向
            while(curRow >= 0 + loop + 1) {
                System.out.print(z[curRow][curCol] + " ");
                curRow--;
            }

            row++;
            col++;
            loop++;
        }
    }
}
