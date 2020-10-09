package leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 *  广度优先， 取每层的最后一个
 */
public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        if(root == null){
            return Collections.EMPTY_LIST;
        }
        Queue<TreeNode> queue = new LinkedBlockingQueue();
        queue.add(root);
        int curLevelSize = 1;

        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            //代表每一层的开始
            //for中表示该层的元素进行遍历
            for(int i=0;i<curLevelSize;i++){
                TreeNode curNode = queue.poll();
                if(i==curLevelSize-1){
                    list.add(curNode.val);
                }
                if(curNode.left != null){
                    queue.add(curNode.left);
                }
                if(curNode.right != null){
                    queue.add(curNode.right);
                }
            }
            curLevelSize=queue.size();
        }

        return list;
    }

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

        RightSideView rsv = new RightSideView();
        System.out.println(rsv.rightSideView(root));
    }
}
