package leetcode.array.binary_search;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 思路：二分查找， 抽屉原理，基于值；如果数字范围[1，9], 则 mid=（1+9）/2 = 5;遍历数组，要求小于等于5的数字个数不能超过5个，有重复在[1,4]中找； 否则在[6,9]找
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:45
 */
public class FindDuplicate {

    public static void main(String[] args){
        int[] nums = new int[]{3,1,3,4,2};
        FindDuplicate fd = new FindDuplicate();
        System.out.println(fd.findDuplicate(nums));
    }

    public int findDuplicate(int[] nums) {
        int left = 1;
        int right = nums.length-1;

        while(left<=right){
            int mid = (left+right)>>>1;
            int count = findCountLowerThanNum(nums, mid);
            if(count>mid){
                //左区间有重复
                right=mid-1;
            } else {
                //左区间无重复
                left=mid+1;
            }
        }
        return left;
    }

    /**
     * 从nums中找小于等于 target的元素
     * @param nums
     * @param target
     * @return
     */
    private int findCountLowerThanNum(int[] nums, int target){
        int count = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<=target){
                count++;
            }
        }
        return count;
    }
}
