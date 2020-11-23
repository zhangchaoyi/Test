package leetcode.tree;

import java.util.*;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 思路：判断每一层的节点是否镜像对称
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:41
 */
public class IsSymmetric {

    /**
     * 使用两个节点指针，
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(Objects.isNull(root)){
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode left, TreeNode right){
        if(Objects.nonNull(left) && Objects.nonNull(right)){
            if (left.val != right.val) {
                return false;
            }
            return isMirror(left.left, right.right) && isMirror(left.right, right.left);
        } else if(Objects.isNull(left) && Objects.isNull(right)){
            return true;
        }
        return false;
    }

    /**
     * 迭代，Queue每次弹出两个元素，分别是镜像比较的元素
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if(Objects.isNull(root)){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        if(Objects.isNull(root.left) && Objects.isNull(root.right)){
            return true;
        } else if(Objects.isNull(root.left) || Objects.isNull(root.right)){
            return false;
        }
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if(left.val != right.val){
                return false;
            }
            if (Objects.nonNull(left.left) && Objects.nonNull(right.right)) {
                queue.add(left.left);
                queue.add(right.right);
            } else if(Objects.isNull(left.left) && Objects.isNull(right.right)){

            } else {
                return false;
            }
            if(Objects.nonNull(left.right) && Objects.nonNull(right.left)){
                queue.add(left.right);
                queue.add(right.left);
            } else if(Objects.isNull(left.right) && Objects.isNull(right.left)){

            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 超出时间限制
     * @param root
     * @return
     */
    public boolean isSymmetric1(TreeNode root) {
        if(Objects.isNull(root)){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curLevelSize = 1;
        while(!queue.isEmpty()){
            List<TreeNode> curLevelNodes = new ArrayList<>();
            for(int i=0;i<curLevelSize;i++){
                TreeNode node = queue.poll();
                curLevelNodes.add(node);
                if(Objects.nonNull(node.left)){
                    queue.add(node.left);
                } else {
                    queue.add(new TreeNode(-1));
                }
                if(Objects.nonNull(node.right)) {
                    queue.add(node.right);
                } else {
                    queue.add(new TreeNode(-1));
                }
            }
            boolean isAllMinus = true;
            //判断当前层是否镜像对称
            int left = 0;
            int right = curLevelNodes.size()-1;
            while(left<right){
                if(curLevelNodes.get(left).val != -1 || curLevelNodes.get(right).val != -1){
                    isAllMinus = false;
                }

                if(curLevelNodes.get(left).val != curLevelNodes.get(right).val){
                    return false;
                }
                left++;
                right--;
            }

            if(curLevelNodes.size()!=1 && isAllMinus){
                return true;
            }

            curLevelSize = queue.size();
        }

        return true;
    }

    public static void main(String[] args){
        //1,2,2,3,4,4,3
        TreeNode root = new TreeNode(1);
        TreeNode Rl = new TreeNode(2);
        TreeNode Rr = new TreeNode(2);
        TreeNode Rll = new TreeNode(3);
        TreeNode Rlr = new TreeNode(4);
        TreeNode Rrl = new TreeNode(4);
        TreeNode Rrr = new TreeNode(3);

        root.left = Rl;
        root.right = Rr;
        Rl.left = Rll;
        Rl.right = Rlr;
        Rr.left = Rrl;
        Rr.right = Rrr;

//        TreeNode root = new TreeNode(1);
//        TreeNode Rl = new TreeNode(2);
//        TreeNode Rr = new TreeNode(2);
//        TreeNode Rlr = new TreeNode(3);
//        TreeNode Rrr = new TreeNode(3);
//        root.left = Rl;
//        root.right = Rr;
//        Rl.right = Rlr;
//        Rr.right = Rrr;

        IsSymmetric is = new IsSymmetric();
        System.out.println(is.isSymmetric2(root));
    }
}
