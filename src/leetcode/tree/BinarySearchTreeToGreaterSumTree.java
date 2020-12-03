package leetcode.tree;

/**
 *
 * 1038. 从二叉搜索树到更大和树
 *
 * 给出二叉 搜索 树的根节点，该二叉树的节点值各不相同，修改二叉树，使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 *  
 *  思路：即反向中序遍历，记录累加和 递归 时间复杂度 O(n) 空间复杂度 O(n)
 */
public class BinarySearchTreeToGreaterSumTree {

    //记录各个节点遍历的累加和
    int sum=0;

    //叶子节点的值为本身
    //非叶子节点的值为 本身+右子树
    public TreeNode bstToGst(TreeNode root) {
        if(root != null){
            bstToGst(root.right);
            sum = sum + root.val;
            root.val = sum;
            bstToGst(root.left);
        }
        return root;
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

        BinarySearchTreeToGreaterSumTree bsttg = new BinarySearchTreeToGreaterSumTree();
        bsttg.bstToGst(root);
    }

}
