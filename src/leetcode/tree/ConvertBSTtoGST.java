package leetcode.tree;

import java.util.*;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * （即每个节点新值等于原节点的所有右边节点之和）
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
 *
 *
 *
 * 示例 1：
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 *
 * 示例 2：
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 *
 * 示例 3：
 * 输入：root = [1,0,2]
 * 输出：[3,3,2]
 *
 *  示例 4：
 * 输入：root = [3,2,4,1]
 * 输出：[7,9,4,10]
 *
 *
 * 提示：
 *
 * 树中的节点数介于 0 和 104 之间。
 * 每个节点的值介于 -104 和 104 之间。
 * 树中的所有值 互不相同 。
 * 给定的树为二叉搜索树。
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:34
 *
 * 思路：1.中序遍历，得到一个有序数组，对数组每一项进行累加处理  O(n + n + n) 递归时间复杂度O(n)，遍历list O(n) , 赋值递归时间复杂度O(n)
 *                                                       O(n + n + n) 递归空间复杂度 + list + map
 *
 *      2.反向中序遍历，记录累加和 递归 时间复杂度 O(n) 空间复杂度 O(n)
 */
public class ConvertBSTtoGST {

    //超出限制时间
    List<TreeNode> list = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();//<node.val, sum>

    public TreeNode convertBST(TreeNode root) {
        mid(root);
        int tempSum = 0;
        for(int i=list.size()-1;i>=0;i--){
            tempSum += list.get(i).val;
            map.put(list.get(i).val, tempSum);
        }
        System.out.println(map);
        //对原树赋值
        mid2(root);
        return root;
    }

    private void mid(TreeNode node) {
        if(Objects.isNull(node)){
            return;
        }
        mid(node.left);
        list.add(node);
        mid(node.right);
    }

    private void mid2(TreeNode node) {
        if(Objects.isNull(node)){
            return;
        }
        mid2(node.left);
        node.val = map.get(node.val);
        mid2(node.right);
    }

    public static void main(String[] args){
        //4,1,6,0,2,5,7,null,null,null,3,null,null,null,8
        TreeNode root = new TreeNode(4);
        TreeNode Rl = new TreeNode(1);
        TreeNode Rr = new TreeNode(6);
        TreeNode Rll = new TreeNode(0);
        TreeNode Rlr = new TreeNode(2);
        TreeNode Rrl = new TreeNode(5);
        TreeNode Rrr = new TreeNode(7);
        TreeNode Rlrr = new TreeNode(3);
        TreeNode Rrrr = new TreeNode(8);

        root.left = Rl;
        root.right = Rr;
        Rl.left = Rll;
        Rl.right = Rlr;
        Rr.left = Rrl;
        Rr.right = Rrr;
        Rlr.right = Rlrr;
        Rrr.right = Rrrr;

        ConvertBSTtoGST cbg = new ConvertBSTtoGST();
        cbg.convertBST(root);

    }
}
