package leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 思路：回溯，left、right分别记录当前str所使用的左括号数和右括号数，每一次递归都在str上在最右添加 "(" 或者 ")"
 * 当left>n || right>n时剪枝
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:26
 */
public class GenerateParenthesis {

    private List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        onceTravel(0, 0, n, "");
        System.out.println(res);
        return res;
    }

    /**
     * 在str后添加左括号或者右括号
     * 当left==right记录
     * @param left  当前的左括号数
     * @param right 当前的右括号数
     * @param n limit
     * @param cur 当前的括号字符串
     */
    private void onceTravel(int left, int right, int n, String cur){
        //右括号数不能大于左括号数
        if (left>n || right>n || right > left) {
            return;
        }
        if(left==n && left==right){
            res.add(cur);
            return;
        }
        onceTravel(left+1, right, n, cur+"(");
        onceTravel(left, right+1, n, cur+")");
    }

    public static void main(String[] args){
        GenerateParenthesis gp = new GenerateParenthesis();
        gp.generateParenthesis(1);
    }
}
