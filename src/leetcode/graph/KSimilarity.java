package leetcode.graph;

import java.util.*;

/**
 * 如果可以通过将 A 中的两个小写字母精确地交换位置 K 次得到与 B 相等的字符串，我们称字符串 A 和 B 的相似度为 K（K 为非负整数）。
 *
 * 给定两个字母异位词 A 和 B ，返回 A 和 B 的相似度 K 的最小值。
 *
 * 示例 1：
 * 输入：A = "ab", B = "ba"
 * 输出：1
 *
 * 示例 2：
 * 输入：A = "abc", B = "bca"
 * 输出：2
 *
 * 示例 3：
 * 输入：A = "abac", B = "baca"
 * 输出：2
 *
 *  示例 4：
 * 输入：A = "aabc", B = "abca"
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= A.length == B.length <= 20
 * A 和 B 只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母。
 *
 *
 *  https://leetcode-cn.com/problems/k-similar-strings/solution/xiang-si-du-wei-k-de-zi-fu-chuan-by-leetcode/
 *
 *  广度优先搜索
 * 当我们把基础图 G 拆分为环并进行截断操作时，我们可以每次截断从左到右第一个 A[i] != B[i] 对应的那条边。即在字符串 A 和 B 中，我们每次找到最左侧满足 A[i] != B[i] 的 i，并搜索满足 j > i 且 A[j] == B[i] 的 j。
 *
 * 通过这种做法，我们可以使用广度优先搜索遍历所有的状态。
 *
 * 例如: A: a b b c
 *       B: b c b a
 * 第一次广度搜索后 A 可能为 b a b c 或者 b b a c
 *
 */
public class KSimilarity {

    public static void main(String[] args){
        String A = "aabc";
        String B = "abca";

        KSimilarity kSimilarity = new KSimilarity();
        System.out.println(kSimilarity.kSimilaritySolution(A, B));
    }

    public int kSimilaritySolution(String A, String B) {
        Queue<String> queue = new ArrayDeque<>();
        //字典map 与 queue永远是 1对1 存在
        Map<String, Integer> stringMap = new HashMap<>();
        stringMap.put(A, 0);
        queue.add(A);

        while(!queue.isEmpty()){
            String curString = queue.poll();
            if(Objects.equals(curString, B)){
                return stringMap.get(curString);
            }
            List<String> ans = neighbor(curString, B);

            for(String s : ans){
                if (!stringMap.containsKey(s)) {
                    stringMap.put(s, stringMap.get(curString)+1);
                    queue.add(s);
                }
            }
        }
        
        return 0;
    }
    
    public void swap(char[] charArray, int k, int p){
        char temp = charArray[k];
        charArray[k] = charArray[p];
        charArray[p] = temp;
    }

    /**
     * 对curString进行一次截断，找出一次截断可能存在的多个交换后字符串
     *
     * @param curString
     * @param B
     * @return
     */
    public List<String> neighbor(String curString, String B){
        List<String> ans = new ArrayList<>();

        int i=0;
        for(;i<curString.length();i++){
            if(!Objects.equals(curString.charAt(i), B.charAt(i))){
                break;
            }
        }
        for(int j=i+1;j<curString.length();j++){
            if (Objects.equals(curString.charAt(j), B.charAt(i))) {
                char[] charArray = curString.toCharArray();
                swap(charArray, i, j);
                ans.add(new String(charArray));
            }
        }

        return ans;
    }
}
