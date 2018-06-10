package leecode.string;

import java.util.Arrays;

/**
 * Created by zcy on 18-5-22.
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
 *
 * 输入: s = "anagram", t = "nagaram"
    输出: true

 输入: s = "rat", t = "car"
 输出: false

 说明:
 你可以假设字符串只包含小写字母
 */
public class Anagram {

    public boolean isAnagram(String s, String t) {
        if(s == null || t == null){
            return false;
        }
        s = s.toLowerCase();
        t = t.toLowerCase();
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        System.out.println(Arrays.toString(alphabet));
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }

    public static void main(String[] args){
        Anagram anagram = new Anagram();
        System.out.println(anagram.isAnagram("anagrama", "nagaram"));
    }
}
