package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * 思路：递归方法 permute(int[] nums, start, end)，计算【start，end】的全排列, 当start==end时记录
 * 从第一个元素起逐位固定，比如 1 2 3 ， 轮流固定第一位为 1/2/3；
 *
 * 固定第1位为1，permute(nums, 1, 2) -》 第2位固定2，permute(nums, 2, 2) or 第2位固定3，permute(nums, 2, 2)
 *
 * 依次进行第一位固定为 2 or 3
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:30
 */
public class Permutations {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        permute(nums, 0, nums.length-1);
        return res;
    }

    /**
     * 计算 [start,end]的全排列数
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public void permute(int[] nums, int start, int end) {
        if(start==end){
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        } else {
            for(int i=start;i<=end;i++){
                swap(nums, start, i);
                permute(nums, start+1, end);
                //归位
                swap(nums, start, i);
            }
        }
    }

    private void swap(int[] nums, int p, int q){
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3};
        Permutations p = new Permutations();
        System.out.println(p.permute(nums));
    }
}
