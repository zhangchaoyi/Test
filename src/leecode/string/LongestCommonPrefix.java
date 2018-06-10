package leecode.string;

/**
 * Created by zcy on 18-5-30.
 * 编写一个函数来查找字符串数组中的最长公共前缀。

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

        int i = 0;
        boolean stop = false;
        while(i < minLen && stop == false){
            char c = strs[0].charAt(i);
            for(int j = 1; j<strs.length; j++){
                if(strs[j].charAt(i) != c){
                    stop = true;
                    break;
                }
            }
            i++;
        }
        if(stop == false && i == minLen){
            return strs[0].substring(0, i);
        }
        if(stop == false){
            return "";
        }
        System.out.println(i);
        return strs[0].substring(0, i-1);
    }

    public static void main(String[] args){
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String[] strs = new String[]{"flower","flow","flight"};
//        String[] strs = new String[]{"racecar","racecar","racecar"};
        System.out.println(longestCommonPrefix.longestCommonPrefix(strs));
    }
}
