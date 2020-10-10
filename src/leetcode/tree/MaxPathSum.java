package leetcode.tree;

/**
 * 124.二叉树中的最大路径和
 *
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1：
 *
 * 输入：[1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出：6
 *
 *
 * 示例 2：
 *
 * 输入：[-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出：42
 *
 *
 * 思路：最大路径是从树的某个节点A到另外一个节点B， 因此这个结构一定构成一颗子树，子树的根节点可能是A/B或者A和B之间的C
 * 我们可以递归每个节点计算当前节点的 路径最大值 = 左子树的最大路径和 + 右子树的最大路径和 + 当前节点值
 * 注意：每一次递归对上层节点的路径贡献是 max{左节点， 右节点} + 当前值 ， 因为路径是单条的
 *
 *   A         B               C
 *  /         /               / \
 * B         A               A  B
 *
 */
public class MaxPathSum {

    /**
     * 类变量用于记录更新
     */
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    /**
     *
     * 在方法中需要计算当前节点的最大路径值并更新max
     * @param node
     * @return  返回每个节点对上一层节点的最大路径贡献值
     */
    public int dfs(TreeNode node){
        if (node == null) {
            return 0;
        }
        //左右子树的贡献为负数时不处理
        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);

        //计算当前节点的最大路径和, 计算时对于负数的子路径不处理
        int curMax = left + right + node.val;
        if(curMax > max){
            max = curMax;
        }

        //本次递归对上一层的贡献是 左或右 中较大的
        return Math.max(left, right) + node.val;
    }


    public static void main(String[] args){
        //[9,6,-3,null,null,-6,2,null,null,2,null,-6,-6,-6]

        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(6);
        root.right = new TreeNode(-3);
        root.right.left = new TreeNode(-6);
        root.right.right = new TreeNode(2);
        root.right.right.left = new TreeNode(2);

        MaxPathSum mps = new MaxPathSum();
        System.out.println(mps.maxPathSum(root));
    }
}
