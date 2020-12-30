package leetcode.todo;

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
 * todo
 * 思路：回溯，left、right分别记录当前str所使用的左括号数和右括号数，每一次递归都在str上在最右添加 "(" 或者 ")"
 * 当left>n || right>n时剪枝
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:26
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        return null;
    }

}
