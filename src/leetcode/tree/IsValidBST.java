package leetcode.tree;

import java.util.*;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 *
 *      思路：因为在搜索二叉树中中序遍历即取出一个有序数组，可以使用中序遍历取出数组判断单调递增数组
 *      使用非递归的中序遍历，判断出栈后的节点值要大于前一个节点的值，
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:24
 */
public class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        if (!helper(node.right, val, upper)) {
            return false;
        }
        if (!helper(node.left, lower, val)) {
            return false;
        }
        return true;
    }

    /**
     * O(logn)
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    /**
     * 根据中序遍历取出到数组，判断是否从小到大 O(logn) + O(n)
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root){
        List<Integer> list = new ArrayList<>();
        middle(root, list);
        System.out.println(list);
        for(int i=0;i<list.size()-1;i++){
            if(list.get(i) >= list.get(i+1)){
                return false;
            }
        }
        return true;
    }

    private void middle(TreeNode root, List<Integer> list){
        if(Objects.isNull(root)){
            return;
        }
        middle(root.left, list);
        list.add(root.val);
        middle(root.right, list);
    }

    public static void main(String[] args){
        // [10,5,15,4,6,13,20,3]
        TreeNode root = new TreeNode(10); //5 10 11 15 20
        TreeNode Rl = new TreeNode(5);

        TreeNode Rr = new TreeNode(15);
        TreeNode Rrl = new TreeNode(11);
        TreeNode Rrr = new TreeNode(20);

        root.left = Rl;
        root.right = Rr;
        Rr.left = Rrl;
        Rr.right = Rrr;

        IsValidBST isValidBST = new IsValidBST();
        System.out.println(isValidBST.isValidBST3(root));
    }
}
