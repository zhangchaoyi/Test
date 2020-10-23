package leetcode.array;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 思路1：二分查找到 target, 从当前的target向左和右分别线性递归边界 O(logn+n)
 * 思路2：双指针，左指针从左开始找target值位置 ；右指针从右开始找target值位置 找到即停止，找不到当两个指针相遇推出循环 O(N)
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/22 16:11
 */
public class SearchRange {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2};
        SearchRange sr = new SearchRange();
        System.out.println(Arrays.toString(sr.searchRange2(nums, 2)));
    }

    /**
     * 思路：二分查找，找到后在当前位置上 分别前后寻找左边界和右边界
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            return (nums[0]==target)?new int[]{0,0}:new int[]{-1,-1};
        }
        int targetIndex = 0;
        int left = 0;
        int right = nums.length - 1;
        boolean find = false;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                find = true;
                targetIndex = mid;
                break;
            } else if (target > nums[mid]) {
                //此处一定要进行 +1 / -1 ，否则会出现死循环
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        if (find) {
            //向左向右进行扩展搜索
            left = targetIndex;
            right = targetIndex;
            while (left >= 0 && nums[left] == target) {
                left--;
            }
            while (right < nums.length && nums[right] == target) {
                right++;
            }
            return new int[]{left+1, right-1};
        }
        return new int[]{-1, -1};
    }

    /**
     * O(n) 当n太大会超出时间限制
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            return (nums[0]==target)?new int[]{0,0}:new int[]{-1,-1};
        }
        int left = 0;
        int right = nums.length - 1;
        while(left < right || nums[left] != target || nums[right] != target){
            if(nums[left] != target){
                left++;
            }
            if(nums[right] != target) {
                right--;
            }
        }
        if(nums[left]==target && nums[right]==target){
            return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }
}
