package leetcode.tree;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 非递归遍历
 * Created by zcy on 18-5-17.
 */
public class NoRecursiveTraversal {

    /**
     * 借助一个stack 先push右节点 再push左节点
     * @param root
     */
    private static void pre(TreeNode root){
        Stack<TreeNode> s = new Stack<>();
        s.push(root);

        while(!s.empty()){
            TreeNode treeNode = s.pop();
            visit(treeNode);

            if(treeNode.right != null){
                s.push(treeNode.right);
            }
            if(treeNode.left != null){
                s.push(treeNode.left);
            }
        }
    }

    private static void pre2(TreeNode root){
        Stack<TreeNode> s = new Stack<>();

        while(root != null || !s.empty()){
            while(root != null){
                visit(root);
                s.push(root);
                root = root.left;
            }
            root = s.pop();
            root = root.right;
        }
    }

    private static void mid(TreeNode root){
        Stack<TreeNode> s = new Stack<>();

        while(root != null || !s.empty()){
            //不断对左子树节点压栈
            while(root != null){
                s.push(root);
                root = root.left;
            }
            //弹栈后不断赋值右子树
            root = s.pop();
            visit(root);
            root = root.right;
        }
    }

    public static void visit(TreeNode treeNode){
        System.out.println(treeNode.val);
    }

    /**
     * 借助一个队列，层次进行遍历
     * @param root
     */
    public static void bfs(TreeNode root){
        Queue<TreeNode> queue = new LinkedBlockingQueue();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            visit(curNode);
            if(curNode.left != null){
                queue.add(curNode.left);
            }
            if(curNode.right != null){
                queue.add(curNode.right);
            }
        }
    }

    /**
     * 102. 二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     *
     *
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(Objects.isNull(root)){
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //当前层的size
        int curSize = 1;
        List<List<Integer>> list = new ArrayList<>();

        while(!queue.isEmpty()){
            List<Integer> perLevel = new ArrayList<>();
            for(int i=0;i<curSize;i++){
                TreeNode treeNode = queue.poll();
                perLevel.add(treeNode.val);
                if(Objects.nonNull(treeNode.left)){
                    queue.add(treeNode.left);
                }
                if(Objects.nonNull(treeNode.right)){
                    queue.add(treeNode.right);
                }
            }
            list.add(perLevel);
            curSize=queue.size();
        }

        return list;
    }

    /**
     * 即前、中、后序遍历
     * @param root
     */
    public static void dfs(TreeNode root){

    }

    public static void main(String[] args){
        //3,9,20,null,null,15,7
        TreeNode root = new TreeNode(3);
        TreeNode Rl = new TreeNode(9);
        TreeNode Rr = new TreeNode(20);
        TreeNode Rrl = new TreeNode(15);
        TreeNode Rrr = new TreeNode(7);

        root.left = Rl;
        root.right = Rr;
        Rr.left = Rrl;
        Rr.right = Rrr;

//        pre(root);
//        pre2(root);
//        bfs(root);

        NoRecursiveTraversal nrt = new NoRecursiveTraversal();
        System.out.println(nrt.levelOrder(root));
    }
}
