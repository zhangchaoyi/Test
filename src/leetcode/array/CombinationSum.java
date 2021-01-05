package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 * 思路：回溯， remind不断进行对四个元素减值，直到刚好为0，记录结果
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:29
 */
public class CombinationSum {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, null, true);
        return ans;
    }



    public void dfs(int[] candidates, int target, List<Integer> res, boolean init){
        if(init){
            res = new ArrayList<>();
        }

        for(int i=0;i<candidates.length;i++){
            if(target == candidates[i]){
                if(!res.isEmpty() && res.get(res.size()-1) > candidates[i]){
                    continue;
                }
                res.add(candidates[i]);
                ans.add(new ArrayList<>(res));
                res.remove(res.size()-1);
                return;
            } else if(target < candidates[i]){
                continue;
            } else {
                //保证res中的单调性
                if(!res.isEmpty() && res.get(res.size()-1) > candidates[i]){
                    continue;
                } else {
                    res.add(candidates[i]);
                    dfs(candidates, target-candidates[i], res, false);
                    res.remove(res.size()-1);
                }
            }
        }
    }

    public static void main(String[] args){
        int[] nums = new int[]{2,3,5};
        CombinationSum cs = new CombinationSum();
        System.out.println(cs.combinationSum(nums, 8));
    }







}
