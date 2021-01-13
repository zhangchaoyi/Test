package leetcode.slideWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * 思路：1.暴力法，O(n^2) i, j=i+1 遍历所有的子串区间
 *      2.双指针，如果未达到子串条件右指针不断右移，如果达到子串条件左指针开始向右遍历直到不满足子串条件；过程中维护记录的节点
 *      时间复杂度 O(n) , 空间复杂度O(n)
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:33
 */
public class MinWindow {

    //====================维护中间状态需要遍历map， 时间复杂度仍需提高==================================================================================================
    public String minWindow(String s, String t) {
        if(t.length()>s.length()) {
            return "";
        }
        int leftAns = -1;
        int rightAns = -1;
        int left=0;
        int right=0;
        final Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<t.length();i++){
            int c = map.getOrDefault(String.valueOf(t.charAt(i)), 0);
            map.put(String.valueOf(t.charAt(i)), c+1);
        }
        Map<String, Integer> countMap = new HashMap<>();
        while(right<s.length()){
            if(map.containsKey(String.valueOf(s.charAt(right)))){
                int curRightCount = countMap.getOrDefault(String.valueOf(s.charAt(right)), 0);
                if(curRightCount==0){
                    countMap.put(String.valueOf(s.charAt(right)), 1);
                } else {
                    countMap.put(String.valueOf(s.charAt(right)), curRightCount+1);
                }
                //满足了t的所有元素，开始遍历左指针，尽量找最小的区间
                while(left<=right && satify(countMap, map)) {
                    if(map.containsKey(String.valueOf(s.charAt(left)))) {
                        if (leftAns == -1) {
                            leftAns=left;
                            rightAns=right;
                        } else if ((right-left) <= (rightAns-leftAns)) {
                            leftAns=left;
                            rightAns=right;
                        }

                        int curLeftCount = countMap.get(String.valueOf(s.charAt(left)));
                        if(curLeftCount==1)  {
                            countMap.remove(String.valueOf(s.charAt(left)));
                            left++;
                            break;
                        } else {
                            countMap.put(String.valueOf(s.charAt(left)), curLeftCount-1);
                        }

                    }
                    left++;
                }
            }
            right++;
        }

        System.out.println("leftAns:"+leftAns+" rightAns:"+rightAns);
        if (leftAns==-1 && rightAns==-1) {
            return "";
        }

        return s.substring(leftAns, rightAns+1);
    }

    private boolean satify(Map<String, Integer> m1, Map<String, Integer> m2){
        for(Map.Entry<String, Integer> entry : m2.entrySet()){
            int v = m1.getOrDefault(entry.getKey(), 0);
            if(entry.getValue() > v){
                return false;
            }
        }
        return true;
    }
    //======================================================================================================================
    public static void main(String[] args){
        MinWindow mw = new MinWindow();
//        System.out.println(mw.minWindow("aa", "aa"));
//        System.out.println(mw.minWindow("cabwefgewcwaefgcf", "cae"));
        System.out.println(mw.minWindow("acbbaca", "aba"));//需要定义两个map的countValue比较
//        Map<String, Integer> m1 = new HashMap<>();
//        m1.put("a", 2);
//        m1.put("b", 3);
//
//        Map<String, Integer> m2 = new HashMap<>();
//        m2.put("a", 2);
//        m2.put("b", 3);
//
//        System.out.println(m1.hashCode());
//        System.out.println(m2.hashCode());
    }
}
