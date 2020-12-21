package leetcode.array.binary_search;

/**
 * 33. 搜索旋转排序数组
 * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
 *
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * nums 肯定会在某个点上旋转
 * -10^4 <= target <= 10^4
 *
 * 思路: 可以画一个坐标轴，如果发生旋转，有以下特征 nums[left] > nums[right]; 如果未发生旋转则 nums[left] < nums[right]
 * 因此先确定单调性，可能有3种  1.left->right完全单调递增    nums[left] < nums[right]
 *                          2.left->mid完全单调递增, mid->right分两段递增， 此时在[left,mid]判断与target的关系
 *                          3.left->mid分两段递增， mid->right完全单调递增，此时在[mid,right]判断与target的关系
 *
 *                  |               /
 *                  |            /
 *                  |         /
 *                  |      /
 *                  |   /
 *                  |/
 *                  |                        /
 *                  |                     /
 *                  |                  /
 *                  |               /
 * ------------------------------------------------------------------------------------------------
 *
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:28
 */
public class SearchRotateArray {

    public int search(int[] nums, int target) {
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
            //两种情况单调性
            //判断target array[mid] 和 array[left] array[right]之间的关系
            if(nums[mid] > nums[left] && nums[mid] > nums[right]){//说明临界值在mid右区间, 在左区间一定单调增
                if(nums[left] < target && target < nums[mid]){ //target在左区间
                    right=mid-1;
                } else {//target在右区间
                    left=mid+1;
                }
                //说明临界值在mid左区间, 在右区间一定单调增
            } else if(nums[mid] < nums[left] && nums[mid] < nums[right]){
                if(target > nums[mid] && target < nums[right]) {//target在右区间
                    left=mid+1;
                } else {
                    right=mid-1;
                }
            } else { //此时 nums[left] < nums[mid] < nums[right]
                if(target > nums[mid]) {
                    left=mid+1;
                } else {
                    right=mid-1;
                }
            }
        }

        return -1;

    }

    public static void main(String[] args){
        int[] nums = new int[]{3,1};
        SearchRotateArray sra = new SearchRotateArray();
        System.out.println(sra.search(nums, 1));
    }
}
