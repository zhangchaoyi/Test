package leetcode.array.binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例:
 *
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 * 思路：使用暴力法时间复杂度是O(n*n)
 *
 *  可以空间换时间，借助一个O(n)链表
 *  将原始数组倒叙遍历， 以升序插入到一个新的list，插入时可以二分搜索插入，原数组元素在插入后的新数组
 *  的下标即 等价于 元素在原数组中右侧比它更小的元素
 *
 *  [1,3,6,1,2,3]
 *
 * input 3,  output: [3] -> 3 左边有 0 个数
 * input 2,  output: [2,3] -> 2 左边有 0 个数
 * input 1,  output: [1,2,3] -> 1 左边有 0 个数
 * input 6,  output: [1,2,3,6] -> 6 左边有 3 个数
 * input 3', output: [1,2,3',3,6] -> 3' 左边有 2 个数
 * input 1', output: [1',1,2,3',3,6] -> 1' 左边有 0 个数
 *
 * 时间复杂度 O(n * logn), 由于实现采用的是ArrayList，虽然查找时间是O(logn)，但是插入时移动元素时间是n
 *  代码中注意临界点 二分时 right = mid,
 *                          left = mid+1
 *
 *  优化算法：在插入时可以使用二叉搜索树，查找 O(logn), 插入O(1)
 */
public class CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args){
        int[] nums = new int[]{5,2,6,1};
//        int[] nums = new int[]{-1, -1};

        CountOfSmallerNumbersAfterSelf c = new CountOfSmallerNumbersAfterSelf();

        System.out.println(c.countSmaller(nums));
//        System.out.println(c.countSmallerWithBst(nums));
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> temp = new ArrayList<>(nums.length);
        Integer[] result = new Integer[nums.length];

        for(int i=nums.length-1;i>=0;i--){
            //每次二分搜索找到待插入list的位置下标
            int left = 0;
            int right = temp.size();
            while (left < right) {
                int mid = left + (right - left)/2;
                if (temp.get(mid) >= nums[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            temp.add(left, nums[i]);
            result[i] = left;
        }

        return Arrays.asList(result);
    }

    public List<Integer> countSmallerWithBst(int[] nums) {
        Integer[] result = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            result[i] = 0;
        }
        TreeNode root = null;
        for(int i=nums.length-1;i>=0;i--){
            root = insertBst(root, new TreeNode(nums[i]), result, i);
        }

        return Arrays.asList(result);
    }

    /**
     * Question...未理解
     * 利用二叉搜索树的特性：左边节点的值小于等于当前节点值，右边节点的值大于等于当前节点值。
     *
     * 那么实现算法首先要构建一颗二叉搜索树：
     *
     * 定义树的节点结构 TreeNode
     * 实现树的节点插入方法 insertNode
     * 其中， insertNode 方法需要实现几个功能：
     *
     * 构建二叉树
     * 维护每个节点中其左子树节点数量值 count：如果新加入的节点需要加入当前节点的左子树，则当前节点的 count += 1
     * 计算出新加入节点 nums[i] 的 "右侧小于当前元素的个数"，即题目所求值 res[i]
     *
     * @param root
     * @param node
     * @param ret
     * @param i
     * @return
     */
    public TreeNode insertBst(TreeNode root, TreeNode node, Integer[] ret, int i) {
        if (root == null) {
            root = node;
            return root;
        }
        if (root.val >= node.val) { // 注意小于等于插入到左子树，防止多加1
            root.count++;
            root.left = insertBst(root.left, node, ret, i);
        } else {
            ret[i] += root.count + 1;
            root.right = insertBst(root.right, node, ret, i);
        }
        return root;
    }

    static class TreeNode{
        private TreeNode left;
        private TreeNode right;
        private int val;
        //记录当前节点的左子树的节点数
        private int count = 0;

        public TreeNode(int val){this.val = val;}

        public int getVal(){return this.val;}
    }
}
