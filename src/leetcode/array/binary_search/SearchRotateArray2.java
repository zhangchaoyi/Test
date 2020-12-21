package leetcode.array.binary_search;

/**
 * 81. 搜索旋转排序数组 II
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 *
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 * 思路：与33旋转数组基本一致，唯一的差别是当nums[left]==nums[right] || nums[left]==nums[mid] 需要进行缩小区间， left++; continue;进行下一次循环
 */
public class SearchRotateArray2 {

    public boolean search(int[] nums, int target) {

        return searchElement(nums, target)!=-1;
    }

    public int searchElement(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int mid = (left+right)>>>1;
            if(nums[mid]==target){
                return mid;
            }
            if(nums[left]==target){
                return left;
            }
            if(nums[right]==target){
                return right;
            }
            if(nums[left]==nums[right] || nums[left]==nums[mid]) {
                left++;
                continue;
            }
            if(nums[left] < nums[mid]){
                if(nums[left] < target && target < nums[mid]){ //target在左区间
                    right=mid-1;
                } else {//target在右区间
                    left=mid+1;
                }
            } else {
                if(target > nums[mid] && target < nums[right]) {//target在右区间
                    left=mid+1;
                } else {
                    right=mid-1;
                }
            }
        }

        return -1;

    }

    public static void main(String[] args){
        int[] nums = new int[]{0,0,1,1,2,0};
        SearchRotateArray2 sra = new SearchRotateArray2();
        System.out.println(sra.search(nums, 2));
    }
}
