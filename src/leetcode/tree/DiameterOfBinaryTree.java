package leetcode.tree;

import java.util.Objects;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 等价最大路径/求最大深度等思路
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *      / \   \
 *     6  9    8
 *    /
 *   7
 *
 *
 * 返回 5, 它的长度是路径 [7,6,4,2,5,8]
 *
 * 定义贡献指当前节点对父节点的最大路径, 如节点4对父节点的贡献是2；  节点5对父节点贡献是1
 * 每个节点都可以计算他左和右的贡献和
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:19
 */
public class DiameterOfBinaryTree {

    private int max=Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        calContribution(root);

        return max<0?0:max;
    }

    private int calContribution(TreeNode root){
        if (Objects.isNull(root)) {
            return 0;
        }
        int left = calContribution(root.left);
        int right = calContribution(root.right);

        max = Math.max(max, left+right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        TreeNode Rl = new TreeNode(2);
        TreeNode Rr = new TreeNode(3);
        TreeNode Rll = new TreeNode(4);
        TreeNode Rlr = new TreeNode(5);
        TreeNode Rlll = new TreeNode(6);
        TreeNode Rllr = new TreeNode(9);
        TreeNode Rlrr = new TreeNode(8);
        TreeNode Rllll = new TreeNode(7);

        root.left = Rl;
        root.right = Rr;
        Rl.left = Rll;
        Rl.right = Rlr;
        Rll.left = Rlll;
        Rll.right = Rllr;
        Rlll.left = Rllll;
        Rlr.right = Rlrr;

        DiameterOfBinaryTree dob = new DiameterOfBinaryTree();
        System.out.println(dob.diameterOfBinaryTree(root));
    }
}
