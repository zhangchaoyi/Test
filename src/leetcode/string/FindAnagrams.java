package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 *
 *  示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 *  示例 2:
 * 输入:
 * s: "abab" p: "ab"
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:36
 *
 *  思路1： 遍历一次，每次判断开始字符是否异位词 O(n^2)
 *
 *  2： 最大滑动窗口 O(n) 动态维护一个int[], 每前进一位删除前一位字符的次数，新增后一个字符的次数，并进行判断是否异位词
 */
public class FindAnagrams {

    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return Collections.EMPTY_LIST;
        }
        for(int i=0;i<s.length();i++){
            if(i+p.length()<=s.length() && isAnagrams(s.substring(i, i+p.length()), p)) {
                res.add(i);
            }
        }

        return res;
    }

    /**
     * 判断 s 与 template是否异位词
     * 1. 一次遍历 s每个字符是否在template中 O(n)调用 string.contains() => O(n^2)
     * 2.借助一个map思想 O(n), int[s.charAt(i)-'a']
     * @param template
     * @param s
     * @return
     */
    private boolean isAnagrams(String template, String s){
        int[] array = new int[26];

        for(int i=0;i<s.length();i++){
            array[s.charAt(i)-'a'] += 1;
        }

        for(int i=0;i<template.length();i++){
            array[template.charAt(i)-'a'] -= 1;
        }

        //check
        for(int i=0;i<array.length;i++){
            if (array[i]!=0) {
                return false;
            }
        }
        return true;
    }

//============最大滑动窗口 O(n)=========================================================================================================
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return Collections.EMPTY_LIST;
        }
        int[] standard = getStandard(p);

        //初始化，将s中前p.length个字符进行放入动态int[]
        List<Integer> res = new ArrayList<>();
        int[] dynamic = new int[26];
        for(int i=0;i<p.length();i++){
            dynamic[s.charAt(i)-'a']+=1;
        }
        if (checkisAnagrams(dynamic, standard)) {
            res.add(0);
        }
        //从第二位开始, 维护dynamic，前一位减1，后一位加1
        for(int i=1;i<s.length()-p.length()+1;i++){
            dynamic[s.charAt(i-1) - 'a'] -= 1;
            dynamic[s.charAt(i+p.length()-1) - 'a'] += 1;
            if(checkisAnagrams(dynamic, standard)){
                res.add(i);
            }
        }
        return res;
    }

    private int[] getStandard(String p){
        int[] array = new int[26];
        for(int i=0;i<p.length();i++){
            array[p.charAt(i)-'a'] += 1;
        }
        return array;
    }

    private boolean checkisAnagrams(int[] now, int[] standard){
        for(int i=0;i<now.length;i++){
            if(now[i]!=standard[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        FindAnagrams fa = new FindAnagrams();
        System.out.println(fa.findAnagrams("cbaebabacd", "abc"));
    }
}
