package leetcode.tree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 翻转一棵二叉树。

 示例：

 输入：

          4
       /   \
     2     7
   / \   / \
 1   3 6   9
 输出：

          4
       /   \
     7     2
   / \   / \
 9   6 3   1
 */
public class InvertTree {

    private static TreeNode root;

    static{
        root = new TreeNode(4);
        TreeNode rootLeft = new TreeNode(2);
        TreeNode rootLeftLeft = new TreeNode(1);
        TreeNode rootLeftRight = new TreeNode(3);
        TreeNode rootRight = new TreeNode(7);
        TreeNode rootRightLeft = new TreeNode(6);
        TreeNode rootRightRight = new TreeNode(9);

        root.left = rootLeft;
        root.right = rootRight;
        rootLeft.left = rootLeftLeft;
        rootLeft.right = rootLeftRight;
        rootRight.left = rootRightLeft;
        rootRight.right = rootRightRight;

    }

    public static void main(String[] args){
        InvertTree it = new InvertTree();
//        TreeNode treeNode = it.invertTreeNoRecursive(root);
        it.invertTree(root);
        RecursivePre.pre(root);
    }

    public void invertTree(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        TreeNode tempLeft = treeNode.left;
        TreeNode tempRight = treeNode.right;

        treeNode.left = tempRight;
        treeNode.right = tempLeft;

        invertTree(treeNode.left);
        invertTree(treeNode.right);

    }

    /**
     * 递归写法
     * @param root
     * @return
     */
//    public TreeNode invertTree(TreeNode root){
//        if (root != null) {
//            TreeNode tempLeft = root.left;
//            root.left = invertTree(root.right);
//            root.right = invertTree(tempLeft);
//        }
//        return root;
//    }

    /**
     * 非递归写法, 广度优先遍历
     * @param root
     * @return
     */
    public TreeNode invertTreeNoRecursive(TreeNode root){
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            TreeNode tempLeft = curNode.left;
            TreeNode tempRight = curNode.right;
            curNode.left = tempRight;
            curNode.right = tempLeft;
            if(curNode.right != null){
                queue.add(curNode.right);
            }
            if(curNode.left != null){
                queue.add(curNode.left);
            }
        }

        return root;
    }
}
