package leetcode.tree;

/**
 * 二叉搜索树的后序遍历序列
 *
   输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
    假设输入的数组的任意两个数字都互不相同。

 示例 1：

 输入: [1,6,3,2,5]
 输出: false
 示例 2：

 输入: [1,3,2,6,5]
 输出: true

 参考以下这颗二叉搜索树：

              5
            / \
          2   6
        / \
      1   3


 思路：后序遍历满足 左 右 根
       二叉搜索树满足  左 < 根 < 右子树

 分治
     后序遍历中，对于数组区间(i, j)， j为根节点，因此可以先找到一个大于 j 的节点 m， 则(i, m-1)为左子树； (m,j-1)为右子树
     因此验证 (m, j-1) 中的所有数都大于 j 节点的数

     递归进行验证 (i, m-1) 和 (m, j-1) 左右子树
 */
public class VerifyPostorder {

    public boolean verifyPostorder(int[] postorder) {
        boolean result = vertify(postorder, 0, postorder.length-1);
        System.out.println(result);
        return result;
    }

    public boolean vertify(int[] postorder, int start, int end){
        if(start >= end){
            return true;
        }
        int root = postorder[end];

        int m = start;
        for(;m<end;m++){
            if (postorder[m] > root) {
                break;
            }
        }
        //进行验证右子树
        for(int j=m;j<end;j++){
            if (postorder[j] < root) {
                return false;
            }
        }

        boolean left = vertify(postorder, start, m-1);
        boolean right = vertify(postorder, m, end-1);
        return left && right;
    }

    public static void main(String[] args){
        int[] array = new int[]{1,2,5,10,6,9,4,3};

        VerifyPostorder verifyPostorder = new VerifyPostorder();
        verifyPostorder.verifyPostorder(array);
    }
}
