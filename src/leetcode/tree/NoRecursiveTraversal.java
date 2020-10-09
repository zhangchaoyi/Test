package leetcode.tree;

import java.util.Queue;
import java.util.Stack;
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
     * 即前、中、后序遍历
     * @param root
     */
    public static void dfs(TreeNode root){

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

//        pre(root);
//        pre2(root);
        bfs(root);
    }
}
