package leetcode.tree;

import java.util.Objects;
import java.util.Stack;

/**
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/9 10:43
 *
 */
public class Flatten {

    public void flatten(TreeNode root) {
        if(Objects.isNull(root)){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while(!stack.isEmpty()){
            TreeNode treeNode = stack.pop();
            if (Objects.nonNull(treeNode.right)) {
                stack.add(treeNode.right);
            }
            if (Objects.nonNull(treeNode.left)) {
                stack.add(treeNode.left);
            }
            if(!Objects.equals(root, treeNode)){
                root.left = null;
                root.right = treeNode;
                root = root.right;
            }
        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        TreeNode Rl = new TreeNode(2);
        TreeNode Rr = new TreeNode(5);
        TreeNode Rll = new TreeNode(3);
        TreeNode Rlr = new TreeNode(4);
        TreeNode Rrr = new TreeNode(6);
        root.left = Rl;
        root.right = Rr;
        Rl.left = Rll;
        Rl.right = Rlr;
        Rr.right = Rrr;

        Flatten flatten = new Flatten();
        flatten.flatten(root);

        System.out.println();
    }

}
