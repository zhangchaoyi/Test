package leecode.string;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zcy on 18-5-18.
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

 案例:
 s = "leetcode"
 返回 0.
 s = "loveleetcode",
 返回 2.
 */
public class FirstUniqChar {

    public static int firstUniqChar(String s) {
        if(s == null || "".equals(s)) {
            return -1;
        }
        char[] array = s.toCharArray();
        Map<String, Integer> m = new LinkedHashMap<>();

        for(char c : array){
            String k = String.valueOf(c);
            int times = m.getOrDefault(k, 0);
            m.put(k, times+1);
        }

        for(int i=0;i<array.length;i++){
            String k = String.valueOf(array[i]);
            if(m.get(k) == 1){
                return i;
            }
        }
        return -1;
    }

    //较快范例
    public static int firstUniqCharQ(String s){
        int start;
        int end;
        int result = s.length();
        for(char ch='a';ch<='z';ch++) {
            start = s.indexOf(ch);
            end = s.lastIndexOf(ch);
            if(start == end && start != -1) {//start！=-1 要求是存在于给定字符串中，
                result = Math.min(result,start);//不断保存位置的最小值
            }
        }
        if(result == s.length()) {//找不到
            return -1;
        }
        return result;
    }

    public static void main(String[] args){
        String s = "lleettccooddee";
        System.out.println(firstUniqCharQ(s));
    }
}