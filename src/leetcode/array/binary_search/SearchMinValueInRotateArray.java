package leetcode.array.binary_search;

/**
 * 153. 寻找旋转排序数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
 *
 * 请找出其中最小的元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 *
 *  示例 2：
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数都是 唯一 的
 * nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转
 *
 * toSubmit
 */
public class SearchMinValueInRotateArray {

    public int findMin(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid = (left+right)>>>1;
            if(nums[mid]==nums[left]){
                return nums[mid];
            }
            if(nums[left] < nums[mid] && nums[mid] < nums[right]){//最小值即首位
                return nums[left];
            } else if(nums[mid]>nums[left]) {//最小值在右
                left=mid+1;
            } else {//最小值在左
                right=mid-1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args){
        int[] nums = new int[]{1};
        SearchMinValueInRotateArray s = new SearchMinValueInRotateArray();
        System.out.println(s.findMin(nums));
    }
}
