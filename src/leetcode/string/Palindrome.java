package leetcode.string;

/**
 * Created by zcy on 18-5-23.
 * 判断是否回文
 * 输入: "A man, a plan, a canal: Panama"
 输出: true
 输入: "race a car"
 输出: false
 */
public class Palindrome {

    public boolean isPalindrome(String s) {
        if(s == null){
            return false;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        //过滤非字母和数字
        for(int i=0;i<s.length();i++){
            if(isCharacter(s.charAt(i)) || isNumberic(s.charAt(i))){
                sb.append(s.charAt(i));
            }
        }
        s = sb.toString();

        System.out.println(s);
        System.out.println(sb.toString());

        char[] array = s.toCharArray();

        int left = 0;
        int right = array.length - 1;
        while(left < right){
            if(array[left] == array[right]){
                left++;
                right--;
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        String s = "0P";
        Palindrome p = new Palindrome();
        System.out.println(p.isPalindrome(s));
    }

    private boolean isNumberic(char c){
        if(c >= '0' && c <= '9'){
            return true;
        }
        return false;
    }

    private boolean isCharacter(char c){
        if(c >= 'a' && c<= 'z'){
            return true;
        }
        return false;
    }
}
