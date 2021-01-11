package leetcode.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例:
 *
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * 难点在于二叉树不是完全二叉树，中间是存在null的
 *
 * 序列化：层序遍历，将每个节点的left、right子节点都存入queue，(当left、right==null也存入队列)，最后要去除尾部的null值
 *
 * 反序列化： 解析string为List<Integer>, 包括null值，遍历list， 同时使用queue作为记录分配下一个list节点作为 左、右子节点的父节点
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:45
 */
public class Serialize {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(Objects.isNull(root)){
            return "[]";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<Integer> res = new ArrayList<>();
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (Objects.nonNull(node)) {
                res.add(node.val);
            } else {
                res.add(null);
            }

            if(Objects.isNull(node)) {

            } else {
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        //去除尾部的null
        for(int i=res.size()-1;i>=0;i--){
            if(res.get(i)==null){
                res.remove(i);
            } else {
                break;
            }
        }

        return String.valueOf(res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<Integer> list = convertStringToList(data);
        if (list.size()==0) {
            return null;
        }
        TreeNode root = new TreeNode(list.get(0));
        //使用队列,存放待作为父节点的元素
        Queue<TreeNode> queue = new LinkedList<>();

        boolean setLeft=false;
        boolean setRight=false;
        TreeNode node = root;

        for(int i=1;i<list.size();i++){
            TreeNode curNode = Objects.nonNull(list.get(i))?new TreeNode(list.get(i)):null;
            //如果左右子节点都设置了则从队列取下一个节点
            if(setLeft&&setRight){
                node = queue.poll();
                setLeft=false;
                setRight=false;
            }
            if(!setLeft) {
                node.left = curNode;
                setLeft=true;
            } else if(!setRight){
                node.right = curNode;
                setRight=true;
            }
            //当前节点不为空则加入队列作为 待设置子节点 的父节点
            if (Objects.nonNull(curNode)) {
                queue.add(curNode);
            }
        }
        return root;

    }

    public static void main(String[] args){
        //1,2,3,null,null,4,5
//        TreeNode root = new TreeNode(1);
//        TreeNode Rl = new TreeNode(2);
//        TreeNode Rr = new TreeNode(3);
//        TreeNode Rrl = new TreeNode(4);
//        TreeNode Rrr = new TreeNode(5);
//
//        root.left = Rl;
//        root.right = Rr;
//        Rr.left = Rrl;
//        Rr.right = Rrr;

        String data = "[1,2,3,null,null,4,5]";

        Serialize s = new Serialize();
//        System.out.println(s.serialize(root));
        System.out.println(s.deserialize(data));
    }

    private List<Integer> convertStringToList(String data){
        String[] splits = data.substring(1,data.length()-1).split(",");
        if(splits.length==1 && splits[0].equals("")){
            return new ArrayList<>();
        }
        System.out.println(Arrays.toString(splits));
        List<Integer> list = Arrays.stream(splits).map(str->{
            if(str==null||Objects.equals(str.trim(),"null")){
                return null;
            }
            return Integer.valueOf(str.trim());
        }).collect(Collectors.toList());
        return list;
    }
}
