package leetcode.string;

/**
 * 647. 回文子串
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 * 如果先找出所有的子串再判断是否回文，时间复杂度O(n^3)
 * 1.从中心拓扑，因为回文的特性是两边字符一样，需要区分奇偶
 * 时间复杂度O(n^2)
 *
 * 2.dp
 */
public class CountPalindrome {

    private int num;

    public int countSubstrings(String s) {
        char[] array = s.toCharArray();
        for(int i=0;i<array.length;i++){
            num++;
            countOdd(s, i);
            countEven(s, i);
        }
        return num;
    }

    /**
     * 统计以 i 为中心的回文数
     * @param s
     * @param i
     */
    private void countOdd(String s, int i){
        char[] array = s.toCharArray();
        int left = i-1;
        int right = i+1;
        while(left>=0 && right<array.length && array[left] == array[right]){
            num++;
            left--;
            right++;
        }
    }

    private void countEven(String s, int i){
        char[] array = s.toCharArray();
        //判断char[i+1]==char[i], 确定是否偶数的回文
        if (i>=array.length-1) {
            return;
        }
        if(array[i] != array[i+1]){
            return;
        }
        num++;
        int left = i-1;
        int right = i+2;
        while(left>=0 && right<array.length && array[left] == array[right]){
            num++;
            left--;
            right++;
        }
    }

    /**
     * 把 n*2 统一处理奇偶的回文中心
     * 编号 ii	回文中心左起始位置 l_il
     * i
     * ​
     *  	回文中心右起始位置 r_ir
     * i
     * ​
     * i  left right
     * 0	0	0
     * 1	0	1
     * 2	1	1
     * 3	1	2
     * 4	2	2
     * 5	2	3
     * 6	3	3
     * @param s
     * @return
     */
    public int countSubstringsUnite(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args){
        String s = "abc";
        CountPalindrome countPalindrome = new CountPalindrome();
        System.out.println(countPalindrome.countSubstringsUnite(s));
    }
}
