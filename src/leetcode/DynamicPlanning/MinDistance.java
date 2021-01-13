package leetcode.DynamicPlanning;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 提示：
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 * 思路：动态规划， 累加的方式，dp[i][j]表示word1的(0,i)到word2的(0,j)的最小编辑距离
 * 编辑距离总共有三种情况
 *  1.word1增加一个字符 等价于 word2减少一个字符
 *  2.word1减少一个字符 等价于 word2增加一个字符
 *  3.word1替换word2字符
 *
 * 以 word1 = "horse", word2 = "ros" 为例
 * dp[i-1][j]  表示 hors 和 ros 的编辑距离， 即hors经过了dp[i-1][j]后会变成ros，
 * 则 horse 和 ros 的编辑距离只需在dp[i-1][j]基础上+字符"e"， dp[i][j]=dp[i-1][j]+1 ;
 *
 * dp[i][j-1]  表示 horse 和 ro 的编辑距离， 即horse经过了dp[i][j-1]后会变成ro，则 horse 和 ro 的编辑距离只需在dp[i][j-1]基础上+字符"s"， dp[i][j]=dp[i][j-1]+1 ;
 *
 * dp[i-1][j-1] 表示 hors 和 ro 的编辑距离, 即hors经过了dp[i-1][j-1]后会变成ro, 此时只需将 horse最后的"e"变成"s" 即可满足要求，即 dp[i][j]=dp[i-1][j-1]+1
 * 此外如果最后一位相同，则 dp[i][j]=dp[i-1][j-1]
 *
 * 因此有 dp[i][j]=min{
 *     dp[i-1][j]+1,
 *     dp[i][j-1]+1,
 *     dp[i-1][j-1] (word1[i]==word2[j])
 *     dp[i-1][j-1]+1 (word1[i]!=word2[j])
 * }
 * 初始化状态 多申请一位，dp[0][0] 表示word1="", word2=""
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:32
 */
public class MinDistance {

    public int minDistance(String word1, String word2) {
        if(word1==""||word1==null||word1.length()==0){
            return word2.length();
        }
        if(word2==""||word2==null||word2.length()==0){
            return word1.length();
        }
        int[][] dp = new int[word1.length()+1][word2.length()+1];

        //初始化dp[0][n], 表示第一行，word1第一个位与word2的编辑距离
        for(int i=0;i<word2.length()+1;i++){
            dp[0][i]=i;
        }
        //初始化dp[n][0], 表示第一列，word1与word2第一位的编辑距离
        for(int i=0;i<word1.length()+1;i++){
            dp[i][0]=i;
        }

        for(int i=1;i<word1.length()+1;i++){
            for(int j=1;j<word2.length()+1;j++){
                dp[i][j]=Math.min(dp[i-1][j], dp[i][j-1]) + 1;

                if(word1.charAt(i-1)==word2.charAt(j-1)){// 对齐word1/word2的index
                    dp[i][j]=Math.min(dp[i][j], dp[i-1][j-1]);
                } else {
                    dp[i][j]=Math.min(dp[i][j], dp[i-1][j-1]+1);
                }
            }
        }
        LongestCommonSubstring.printArray(dp);

        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args){
        MinDistance md = new MinDistance();
        System.out.println(md.minDistance("horse", "ros"));
    }
}
