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
 * toSubmit
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
        //使用队列
        Queue<TreeNode> queue = new LinkedList<>();

        boolean setLeft=false;
        boolean setRight=false;
        TreeNode node = root;

        for(int i=1;i<list.size();i++){
            TreeNode curNode = Objects.nonNull(list.get(i))?new TreeNode(list.get(i)):null;

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
            if (Objects.nonNull(curNode)) {
                queue.add(curNode);
            }
        }
        return root;

    }

    public static void main(String[] args){
        //1,2,3,null,null,4,5
        TreeNode root = new TreeNode(1);
        TreeNode Rl = new TreeNode(2);
        TreeNode Rr = new TreeNode(3);
        TreeNode Rrl = new TreeNode(4);
        TreeNode Rrr = new TreeNode(5);

        root.left = Rl;
        root.right = Rr;
        Rr.left = Rrl;
        Rr.right = Rrr;

        Serialize s = new Serialize();
        System.out.println(s.serialize(root));
    }

    private List<Integer> convertStringToList(String data){
        String[] splits = data.substring(1,data.length()-1).split(",");
        if(splits.length==1 && splits[0].equals("")){
            return new ArrayList<>();
        }
        List<Integer> list = Arrays.stream(splits).map(str->{
            if(str==null||Objects.equals(str,"null")){
                return null;
            }
            return Integer.valueOf(str);
        }).collect(Collectors.toList());
        return list;
    }
}
