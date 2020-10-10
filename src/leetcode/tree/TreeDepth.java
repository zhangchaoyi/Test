package leetcode.tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 求二叉树的深度
 * @Author: chaoyi.zhang
 * @Date: 2020/10/9 13:59
 */
public class TreeDepth {

    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        TreeNode Rl = new TreeNode(5);
        TreeNode Rll = new TreeNode(21);
        TreeNode Rlr = new TreeNode(22);

        TreeNode Rr = new TreeNode(2);
        TreeNode Rrl = new TreeNode(23);
        TreeNode Rrr = new TreeNode(25);

        root.left = Rl;
        root.right = Rr;
        Rl.left = Rll;
        Rl.right = Rlr;
        Rr.left = Rrl;
        Rr.right = Rrr;

        TreeDepth treeDepth = new TreeDepth();
        System.out.println(treeDepth.depth2(root));
    }

    /**
     * dfs递归, max{左子树的最大深度， 右子树的最大深度} + 1
     * @param root
     * @return
     */
    public int depth(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        return Math.max(leftDepth, rightDepth)+1;
    }

    /**
     * 非递归， 广度遍历, 要求记录每一层的个数，才能知道每层遍历
     * @param root
     * @return
     */
    public int depth2(TreeNode root){
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedBlockingQueue();
        queue.add(root);
        int curLevelSize = 1;
        int depth = 0;

        while(!queue.isEmpty()){
            //代表每一层的开始
            depth++;
            //for中表示该层的元素进行遍历
            for(int i=0;i<curLevelSize;i++){
                TreeNode curNode = queue.poll();
                if(curNode.left != null){
                    queue.add(curNode.left);
                }
                if(curNode.right != null){
                    queue.add(curNode.right);
                }
            }
            curLevelSize=queue.size();
        }

        return depth;
    }
}
