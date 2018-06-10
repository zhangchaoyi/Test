package leecode.string;

/**
 * Created by zcy on 18-5-28.
 * 实现 strStr() 函数。

 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

 输入: haystack = "hello", needle = "ll"
 输出: 2

 输入: haystack = "aaaaa", needle = "bba"
 输出: -1

 */
public class StrStr {

    public int strStr(String haystack, String needle) {
        if(needle == null || "".equals(needle)){
            return 0;
        }
        if(haystack == null || "".equals(haystack)){
            return -1;
        }
        char[] hArray = haystack.toCharArray();
        char[] nArray = needle.toCharArray();
        int len = nArray.length;
        for(int i=0; i<hArray.length; i++){
            int tmp = i;
            int j = 0;
            while(tmp < hArray.length && j < nArray.length && hArray[tmp] == nArray[j]){
                tmp++;
                j++;
            }
            if(j == len) {
                return i;
            }
        }


        return -1;
    }

    public static void main(String[] args){
        String haystack = "aaaaa";
        String needle = "bba";

        StrStr strStr = new StrStr();
        System.out.println(strStr.strStr(haystack, needle));
    }
}
