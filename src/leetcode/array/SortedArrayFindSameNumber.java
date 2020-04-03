package leetcode.array;

/**
 * 找出指定元素在有序数组中的次数
 *
 * [1, 2, 3, 3, 3, 3, 3, 3, 5, 6, 8]
 */
public class SortedArrayFindSameNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 3, 3, 3, 3, 3, 5, 6, 8};
        SortedArrayFindSameNumber sa = new SortedArrayFindSameNumber();
        System.out.println(sa.countTimes(nums, 3));
    }

    public int countTimes(int[] nums, int k){
        int leftIndex = getLeftIndex(nums, k);
        int rightIndex = getRightIndex(nums, k);
        return rightIndex - leftIndex + 1;
    }

    public int getLeftIndex(int[] nums, int k){
        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            int mid = left + (right - left)/2;
            //相同的元素往左区间
            if(k <= nums[mid]){
                if(k == nums[mid]){
                    //取前一位进一步判断是否还有相同元素
                    if (mid > 0 && nums[mid-1] != k) {
                        return mid;
                    }
                }
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }

    public int getRightIndex(int[] nums, int k){
        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            int mid = left + (right - left)/2;
            if(k < nums[mid]){
                right = mid;
            } else {
                if(k == nums[mid]){
                    if (mid < nums.length - 1 && nums[mid+1] != k) {
                        return mid;
                    }
                }
                left = mid;
            }
        }
        return left;
    }
}
