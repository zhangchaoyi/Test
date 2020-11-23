package leetcode.tree;

import java.util.*;

/**
 * 236 寻找两个节点的最近的公共祖先, 所有元素都不相同
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 *                          3
 *                        /   \
 *                      5      1
 *                     / \    / \
 *                   6   2   0  8
 *                      / \
 *                     7   4
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *  
 * 思路： dfs遍历使用hashmap记录所有节点的上一级父节点
 * 使用hashmap对p找出p的所有父节点列表list，然后使用hashmap对q进行找父节点时进行判断是否在list中
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/5 14:29
 */
public class FindNearestSameFather {

    public static void main(String[] args){
//        TreeNode root = new TreeNode(3);
//        TreeNode Rl = new TreeNode(5);
//        TreeNode Rll = new TreeNode(6);
//        TreeNode Rlr = new TreeNode(2);
//        TreeNode Rlrl = new TreeNode(7);
//        TreeNode Rlrr = new TreeNode(4);
//
//        TreeNode Rr = new TreeNode(1);
//        TreeNode Rrl = new TreeNode(0);
//        TreeNode Rrr = new TreeNode(8);
//
//        root.left = Rl;
//        root.right = Rr;
//        Rl.left = Rll;
//        Rl.right = Rlr;
//        Rlr.left = Rlrl;
//        Rlr.right = Rlrr;
//
//        Rr.left = Rrl;
//        Rr.right = Rrr;
        //[-1,0,3,-2,4,null,null,8]
        //8
        //0
        TreeNode root = new TreeNode(-1);
        TreeNode Rl = new TreeNode(0);
        TreeNode Rr = new TreeNode(3);
        TreeNode Rll = new TreeNode(-2);
        TreeNode Rlr = new TreeNode(4);
        TreeNode Rlll = new TreeNode(8);
        root.left = Rl;
        root.right = Rr;
        Rl.left = Rll;
        Rl.right = Rlr;
        Rll.left = Rlll;

        FindNearestSameFather fnsf = new FindNearestSameFather();
        TreeNode tn = fnsf.lowestCommonAncestor(root, Rlll, Rl);

        System.out.println();
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(Objects.equals(root, p) || Objects.equals(root, q)){
            return root;
        }
        HashMap<TreeNode, TreeNode> map = getAllNodesFather(root);
        //查出p的所有父节点列表
        List<TreeNode> pFathers = new ArrayList<>();
        pFathers.add(p);

        TreeNode father = map.get(p);
        while (true) {
            if (Objects.nonNull(father)) {
                pFathers.add(father);
                father = map.get(father);
            } else {
                break;
            }
        }

        //从q开始找与p相同的父节点
        if(pFathers.contains(q)){
            return q;
        }

        TreeNode qFather = map.get(q);
        while(true){
            if(Objects.nonNull(qFather)){
                if(pFathers.contains(qFather)){
                    return qFather;
                }
                qFather = map.get(qFather);
            } else {
                System.out.println("can not find");
                return null;
            }
        }

    }

    /**
     * 获取所有节点的父节点
     * @return
     */
    private HashMap<TreeNode, TreeNode> getAllNodesFather(TreeNode root){
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        dfs(root, map);
        return map;
    }

    private void dfs(TreeNode root, HashMap<TreeNode, TreeNode> map){
        if(Objects.isNull(root)){
            return;
        }
        if(Objects.nonNull(root.left)){
            map.put(root.left, root);
        }
        if(Objects.nonNull(root.right)){
            map.put(root.right, root);
        }
        dfs(root.left, map);
        dfs(root.right, map);
    }
}
