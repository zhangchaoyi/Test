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
 *
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

    public static void main(String[] args){
        String s = "abc";
        CountPalindrome countPalindrome = new CountPalindrome();
        System.out.println(countPalindrome.countSubstrings(s));
    }
}
