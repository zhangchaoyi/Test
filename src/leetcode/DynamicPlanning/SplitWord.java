package leetcode.DynamicPlanning;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 示例 4：
 * "bb"
 * ["a","b","bbb","bbbb"]
 * 输出true
 *
 * dfs, 将s使用wordDict中的每个单词进行分割，如果可以分割完，最终为0，则true
 * 否则wordDict全部尝试一次都失败的话为false
 *
 *
 *  动态规划： 参考完全背包问题  F[v] = max{F[v], F[j-Ci]+Wi}
 *
 *          dp[i]表示s前i个字符能否拆分
 *          转移方程：dp[j] = dp[i] && check(s[i+1, j]);  这等价于s[i+1, j]是否是wordDict中的元素
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/26 14:17
 */
public class SplitWord {

//============ dfs 超出时间限制 =====================================================================================================================================================================
    private boolean split = false;

    /**
     * 思路：逐个wordDict进行取出从s中减去
     * bfs
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        dfs(s, wordDict);
        return split;
    }

    /**
     * 超出时间限制
     * @param s
     * @param wordDict
     */
    private void dfs(String s, List<String> wordDict){
        if (isWhiteSpace(s)) {
            split = true;
            return;
        }
        for(int i=0;i<wordDict.size();i++){
            if(s.contains(wordDict.get(i))){
                dfs(s.replaceFirst(wordDict.get(i), " "), wordDict);
            } else {
                continue;
            }
        }
    }

    public boolean isWhiteSpace(String s){
        if (Objects.equals(s.trim(), "")) {
            return true;
        }
        return false;
    }
//=================================================================================================================================================================================

    public static void main(String[] args){
        SplitWord sw = new SplitWord();
//        String s = "leetcode";
//        List<String> wordDict = new ArrayList<String>(){
//            {
//                add("leet");
//                add("code");
//            }
//        };
//        String s = "catsandog";
//        List<String> wordDict = new ArrayList<String>(){
//            {
//                add("cats");
//                add("dog");
//                add("sand");
//                add("and");
//                add("cat");
//            }
//        };
        String s = "ccbb";
        List<String> wordDict = new ArrayList<String>(){
            {
                add("bc");
                add("cb");
            }
        };

        System.out.println(sw.wordBreak(s, wordDict));
//        System.out.println(sw.matchWord("leet", "leet"));

//        System.out.println(sw.isWhiteSpace("   "));
        System.out.println("|"+"    ".trim() +"|");
    }

    /*
    动态规划算法，dp[i]表示s前i个字符能否拆分
    转移方程：dp[j] = dp[i] && check(s[i+1, j]);
    check(s[i+1, j])就是判断i+1到j这一段字符是否能够拆分
    其实，调整遍历顺序，这等价于s[i+1, j]是否是wordDict中的元素
    这个举个例子就很容易理解。
    假如wordDict=["apple", "pen", "code"],s = "applepencode";
    dp[8] = dp[5] + check("code")
    翻译一下：前八位能否拆分取决于前五位能否拆分，加上五到八位是否属于字典
*/
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) { // 为啥不能dp[i]= dp[j] && wordDictSet.contains(s.substring(j, i))
                    dp[i] = true;
                }
            }
        }
        System.out.println(Arrays.toString(dp));//[true, false, false, false, true, false, false, false, true]
        return dp[s.length()];
    }

}