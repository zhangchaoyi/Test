package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 437. 路径总和 III
 *
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 * 思路：
 *
 *  @Author: chaoyi.zhang
 * @Date: 2020/10/13 18:00
 */
public class PathSum {

    private int result;

    public int pathSum(TreeNode root, int sum) {
        dfs(root, sum, new ArrayList<>(), 0);
        return result;
    }

    /**
     * 计算以当前节点为结尾的 满足条件的路径数
     * 使用list记录每一层的数值，对于当前节点，前面每一层只有一个元素
     * 因为 当前节点 到前面所有祖先节点都是单个路径，比如当前节点是第四层，则当前节点node分别到前面3个节点都是 node+list[2]/ node+list[2]+list[1] / node+list[2]+list[1]+list[0]
     *
     * @param root 当前节点
     * @param sum 目标和
     * @param list 用于收集当前节点 前面几层的经过的节点，每一层一定是唯一的
     * @param p 代表当前节点的层数
     * @return 返回表示 当前节点结尾到前面几层满足sum的路径总数
     */
//    public int pathSum(TreeNode root, int sum, List<Integer> list/*保存路径*/, int p/*指向路径终点*/) {
//        if (root == null) {
//            return 0;
//        }
//        int tmp = root.val;
//        int n = root.val == sum ? 1 : 0;
//        /**
//         * 以当前节点为结尾，逐层加上前面几层的节点值
//         */
//        for (int i = p - 1; i >= 0; i--) {
//            tmp += list.get(i);
//            if (tmp == sum) {
//                n++;
//            }
//        }
//        list.add(p, root.val);
//        /**
//         * 以左节点结尾的满足条件路径数
//         */
//        int n1 = pathSum(root.left, sum, list, p + 1);
//        /**
//         * 以右节点结尾的满足条件路径数
//         */
//        int n2 = pathSum(root.right, sum, list, p + 1);
//        return n + n1 + n2;
//    }

    /**
     * 深度遍历
     * @param root
     * @param sum
     * @param list 自顶开始记录每一层的节点
     * @param p 当前层数
     *
     *   实际上对于同一层的多个节点，list的值会发生改变，只用于当前节点的遍历
     *   比如对于对于最后一层的元素 3,       -2，        1
     *          list分别是[10, 5, 3]  [10, 5, 3]  [10, 5, 2]
     *
     *          10
     *         /  \
     *        5   -3
     *       / \    \
     *      3   2   11
     *     / \   \
     *    3  -2   1
     */
    private void dfs(TreeNode root, int sum, List<Integer> list, int p){
        if (root == null) return;
        if (root.val == sum) {//当前节点直接满足
            result++;
        }
        //记录当前节点这一层的值
        list.add(p, root.val);
        //当前节点逐层加上前面几层判断是否满足
        int temp = root.val;
        for(int i=p-1;i>=0;i--){
            temp += list.get(i);
            if (temp == sum) {
                result++;
            }
        }

        dfs(root.left, sum, list, p+1);
        dfs(root.right, sum, list, p+1);
    }



    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);

        PathSum ps = new PathSum();
        System.out.println(ps.pathSum(root, 8));
    }
}
