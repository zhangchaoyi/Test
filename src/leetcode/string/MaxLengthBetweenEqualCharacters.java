package leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 1624. 两个相同字符之间的最长子字符串
 * 给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。如果不存在这样的子字符串，返回 -1 。
 *
 * 子字符串 是字符串中的一个连续字符序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aa"
 * 输出：0
 * 解释：最优的子字符串是两个 'a' 之间的空子字符串。
 *
 * 示例 2：
 * 输入：s = "abca"
 * 输出：2
 * 解释：最优的子字符串是 "bc" 。
 *
 * 示例 3：
 * 输入：s = "cbzxy"
 * 输出：-1
 * 解释：s 中不存在出现出现两次的字符，所以返回 -1 。
 *
 *  示例 4：
 * 输入：s = "cabbac"
 * 输出：4
 * 解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 300
 * s 只含小写英文字母
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:16
 *
 *
 *  思路：O(n^2) 左指针从左遍历，右指针每次从 length-1 开始遍历找相同的字符，记录max长度
 *  借助一个map，空间换时间，Map<char, index> , 开始一次遍历，每个元素进来先判断是否已有，1.有则更新max  2.无则putmap
 *
 */
public class MaxLengthBetweenEqualCharacters {

    public int maxLengthBetweenEqualCharacters(String s) {
        int max = Integer.MIN_VALUE;
        Map<String, Integer> map = new HashMap<>();//<string, index>
        for(int i=0;i<s.length();i++){
            String curString = String.valueOf(s.charAt(i));
            Integer prevIndex = map.putIfAbsent(curString, i);
            //进行更新
            if(Objects.nonNull(prevIndex)){
                int period = i-prevIndex-1;
                max = Math.max(max, period);
            }
        }

        return (max<0)?-1:max;
    }

    public static void main(String[] args){
        MaxLengthBetweenEqualCharacters mec = new MaxLengthBetweenEqualCharacters();
        String s = "cabbac";
        System.out.println(mec.maxLengthBetweenEqualCharacters(s));
    }
}
