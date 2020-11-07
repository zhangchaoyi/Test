package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 617.合并二叉树
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 示例 1:
 *
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/10 17:47
 */
public class MergeTree {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1==null && t2==null) {
            return null;
        }
        if (t1==null) {
            return t2;
        }
        if (t2==null) {
            return t1;
        }
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }

    /**
     * 非递归
     * 层序遍历，以左子树为基准，将右子树逐个节点添加到左子树
     * 队列每次弹出两个左右子树对应位置的两个节点，因此入队列时要求左右子树同时存在；
     * 同一位置如果左子节点不为空右子节点为空，此时左子节点即最终的节点, 无需调整
     * 同一位置如果左子节点为空而右子节点不为空，此时将右子节点作为最终的节点
     * 同一位置如果左右两个子节点都不为空则同时入两个队列
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1==null && t2==null) {
            return null;
        }
        if (t1==null) {
            return t2;
        }
        if (t2==null) {
            return t1;
        }
        /**
         * 只有左右子树的节点都同时存在才入队列
         */
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(t1);
        queue.add(t2);

        while (!queue.isEmpty()) {
            TreeNode leftTreeNode = queue.poll();
            TreeNode rightTreeNode = queue.poll();

            leftTreeNode.val += rightTreeNode.val;

            if(Objects.nonNull(leftTreeNode.left) && Objects.nonNull(rightTreeNode.left)){
                queue.add(leftTreeNode.left);
                queue.add(rightTreeNode.left);
            }
            if(Objects.nonNull(leftTreeNode.right) && Objects.nonNull(rightTreeNode.right)){
                queue.add(leftTreeNode.right);
                queue.add(rightTreeNode.right);
            }
            if(Objects.isNull(leftTreeNode.left) && Objects.nonNull(rightTreeNode.left)){
                leftTreeNode.left = rightTreeNode.left;
            }
            if(Objects.isNull(leftTreeNode.right) && Objects.nonNull(rightTreeNode.right)){
                leftTreeNode.right = rightTreeNode.right;
            }
        }
        return t1;
    }

    public static void main(String[] args){
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3);
        t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(5);

        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(4);
        t2.right.right = new TreeNode(7);

        MergeTree mt = new MergeTree();
        TreeNode node = mt.mergeTrees2(t1, t2);
        System.out.println(node);
    }
}
