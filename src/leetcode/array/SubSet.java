package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 *  思路：List<List<Integer>>最初为一个空元素[]，
 *   对nums中进行遍历，每个元素都把 List<List<Integer>>中的所有集合取出 加上 nums[i], 新增的集合加回 List<List<Integer>>
 *
 *   同时可以发现子集合数 dp[i] = dp[i-1] * 2 ， 前提是元素互不相同
 *
 *   对于 [1,2,3]
 *
 *       List<List<Integer>>                                       新增
 *         []
 *  i=0    [] [1]                                                   [1]
 *  i=1    [] [1] [2] [1,2]                                        [2][1,2]
 *  i=2    [] [1] [2] [1,2] [3] [1,3] [2,3] [1,2,3]                [3] [1,3] [2,3] [1,2,3]
 *
 *  时间复杂度：O(n * n)  在nums中遍历时根据已有集合得到新集合
 *  空间复杂度：O(n * n)
 *
 * @author: zhangchaoyi
 * @date: 2018/8/16
 */
public class SubSet {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};

        SubSet ss = new SubSet();
        ss.subsets(nums);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for(int i=0;i<nums.length;i++){
            List<List<Integer>> newList = new ArrayList<>();
            for(List<Integer> subList : result){
                List<Integer> copyList = new ArrayList<>(subList);
                copyList.add(nums[i]);
                newList.add(copyList);
            }
            result.addAll(newList);
        }
        System.out.println(result);
        return result;
    }
}
