package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树的最大宽度, 广度遍历，要求区分每层的元素个数
 * @Author: chaoyi.zhang
 * @Date: 2020/10/9 15:19
 */
public class TreeWidth {

    public static void main(String[] args){

    }

    public int width(TreeNode root){
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curLevelSize = 1;
        int maxWidth = 1;

        while(!queue.isEmpty()){
            //while代表每一层的开始
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
            if(curLevelSize > maxWidth){
                maxWidth = curLevelSize;
            }
        }

        return maxWidth;
    }
}
