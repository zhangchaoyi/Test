package leetcode.array.binary_search;

/**
 * 154. 寻找旋转排序数组中的最小值 II
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 注意数组中可能存在重复的元素。
 *
 * 示例 1：
 *
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 *
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * 说明：
 *
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 *
 * 思路：出现重复的解决是将mid与left与right比较是否相等，如果相等，left++ or right-- , 进入下一次while
 * 如果全部都是重复数字，那么时间复杂度从 O(logn) -> O(n)
 */
public class SearchMinValueInRotateArray2 {

    public int findMin(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid = (left+right)>>>1;
            if(left==mid){
                return Math.min(nums[left], nums[right]);
            }
            if(nums[mid]==nums[left]){
                left++;
                continue;
            }
            if(nums[mid]==nums[right]){
                right--;
                continue;
            }
            if(nums[left] < nums[mid] && nums[mid] < nums[right]){//完全单调递增，最小值即首位
                return nums[left];
            } else if(nums[mid]>nums[left]) {//最小值在右
                left=mid;//有可能当前的mid即最小值
            } else {//最小值在左
                right=mid;//有可能当前的mid即最小值
            }
        }
        return nums[left];
    }

    public static void main(String[] args){
        int[] array = new int[]{10,1,10,10,10};
        SearchMinValueInRotateArray2 s = new SearchMinValueInRotateArray2();
        System.out.println(s.findMin(array));
    }
}
