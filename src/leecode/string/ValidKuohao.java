package leecode.string;

import java.util.Stack;

/**
 * Created by chris on 6/24/18.
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：

 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。

 输入: "()"
 输出: true

 输入: "()[]{}"
 输出: true

 输入: "(]"
 输出: false

 输入: "([)]"
 输出: false

 输入: "{[]}"
 输出: true
 */
public class ValidKuohao {

    public boolean isValid(String s) {
        if(s==null){
            return false;
        }
        if("".equals(s)){
            return true;
        }
        Stack<String> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for(char c : chars){
            String curString = String.valueOf(c);
            if(!stack.empty()){
                String findPreMatchString = findPreMatchString(curString);
                if(stack.peek().equals(findPreMatchString)){
                    stack.pop();
                    continue;
                }
            }
            stack.add(curString);
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }

    private String findPreMatchString(String s){
        switch(s){
            case ")":
                return "(";
            case "}":
                return "{";
            case "]":
                return "[";

            default:
                return "";
        }
    }

    public static void main(String[] args){
        String s = "{[]1}";
        ValidKuohao validKuohao = new ValidKuohao();
        System.out.println(validKuohao.isValid(s));
    }
}
