package leetcode.graph;

/**
 * 200. 岛屿数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 * 示例 1:
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 *
 *
 * 示例 2:
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 *  思路： 遍历二维数组，每发现一个 1 进行dfs将相邻的所有 1 都变成 0 ， 只需统计 遍历到 1 的次数
 */
public class NumsIsland {

    public int numsIsland(char[][] grid){
        if (grid == null||grid.length==0) {
            return 0;
        }
        int result=0;
        //边界
        int ROWS = grid.length;
        int COLS = grid[0].length;
        for(int r=0;r<ROWS;r++){
            for(int c=0;c<COLS;c++){
                if(grid[r][c]=='1'){
                    result++;
                    dfs(grid, r, c);
                }
            }
        }
        return result;
    }

    /**
     * 将 (r,c) 相邻的节点进行判断dfs, 将1变为0
     * @param grid
     * @param r
     * @param c
     */
    private void dfs(char[][] grid, int r, int c){
        //边界
        int ROWS = grid.length;
        int COLS = grid[0].length;
        if(r >= ROWS || c >= COLS || r < 0 || c < 0 || grid[r][c]=='0'){
            return;
        }

        grid[r][c]='0';

        dfs(grid, r-1, c);
        dfs(grid, r+1, c);
        dfs(grid, r, c+1);
        dfs(grid, r, c-1);
    }

    public static void main(String[] args){
//        char[][] array = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char[][] array = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        NumsIsland nil = new NumsIsland();
        System.out.println(nil.numsIsland(array));
    }
}
