package leetcode.array.two_dimension;

import java.util.Objects;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 *
 * 提示：
 *
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:28
 *
 * 思路：dfs O(m*n* word.length)
 *
 * 结论：如果在递归不返回boolean会超出时间限制，因此在递归方法要求返回boolean才通过
 */
public class SearchWord {

    public boolean exist1(char[][] board, String word) {
        int[][] trace = new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(findChar(board, word, i, j, 0, trace)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findChar(char[][] board, String word, int i, int j, int wordIndex, int[][] trace){
        int ROW_LIMIT = board.length;
        int COL_LIMIT = board[0].length;
        //超过边界
        if(i>=ROW_LIMIT || j>=COL_LIMIT || i<0 || j<0){
            return false;
        }
        if(trace[i][j]==1){
            return false;
        }
        if (Objects.equals(board[i][j], word.charAt(wordIndex))) {
            if (Objects.equals(wordIndex, word.length()-1)) {
                return true;
            }
            trace[i][j]=1;
            boolean result = findChar(board, word, i-1, j, wordIndex+1, trace)
                    || findChar(board, word, i, j-1, wordIndex+1, trace)
                    || findChar(board, word, i+1, j, wordIndex+1, trace)
                    || findChar(board, word, i, j+1, wordIndex+1, trace);
            trace[i][j]=0;
            return result;
        } else {
            return false;
        }
    }

    public static void main(String[] args){

        char[][] chars = new char[][]{
                {'A','B','C','E'},
                {'S','F','E','S'},
                {'A','D','E','E'}
        };
//        char[][] chars = new char[][]{
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A','D','E','E'}
//        };
        SearchWord sw = new SearchWord();
        System.out.println(sw.exist1(chars, "ABCESEEEFS"));
    }
}
