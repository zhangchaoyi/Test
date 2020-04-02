package leetcode.tree;

/**
 * Created by zcy on 18-5-17.
 * 前序递归遍历
 */
public class RecursivePre {

    public static void pre(TreeNode node){
        if (node == null) {
            return;
        }
        visit(node);
        pre(node.left);
        pre(node.right);
    }

    public static void mid(TreeNode node){
        if (node == null) {
            return;
        }

        mid(node.left);
        visit(node);
        mid(node.right);
    }

    public static void post(TreeNode node){
        if (node == null) {
            return;
        }

        post(node.left);
        post(node.right);
        visit(node);
    }

    public static void visit(TreeNode treeNode){
        System.out.println(treeNode.val);
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
//        mid(root);
        post(root);
    }
}
