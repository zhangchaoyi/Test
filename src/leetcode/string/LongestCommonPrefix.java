package leetcode.string;

/**
 * Created by zcy on 18-5-30.
 * 编写一个函数来查找字符串数组中的最长公共前缀。 O(N*N)

 如果不存在公共前缀，返回空字符串 ""。
 输入: ["flower","flow","flight"]
 输出: "fl"

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for(String str : strs){
            if(minLen > str.length()){
                minLen = str.length();
            }
        }

        //以第一个字符串为参照物，与字符数组中所有的字符串逐个字符比对
        int curIndex = 0;
        boolean stop = false;
        String firstStr = strs[0];
        while(curIndex < minLen && stop == false){
            char c = firstStr.charAt(curIndex);
            for(int j = 1; j<strs.length; j++){
                if(strs[j].charAt(curIndex) != c){
                    stop = true;
                    break;
                }
            }
            curIndex++;
        }
        if(stop == false && curIndex == minLen){
            return strs[0].substring(0, curIndex);
        }
        if(stop == false){
            return "";
        }
        System.out.println(curIndex);
        return strs[0].substring(0, curIndex-1);
    }

    public static void main(String[] args){
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String[] strs = new String[]{"flower","flow","flight"};
//        String[] strs = new String[]{"racecar","racecar","racecar"};
        System.out.println(longestCommonPrefix.longestCommonPrefix(strs));
    }
}
