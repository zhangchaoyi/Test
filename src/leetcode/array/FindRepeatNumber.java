package leetcode.array;

/**
 *
 找出数组中重复的数字。

 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

 示例 1：
 输入：
 [2, 3, 1, 0, 2, 5, 3]
 输出：2 或 3

 限制：

 2 <= n <= 100000

 解法一：排序 + 遍历一次找到 时间复杂度 O(nlogn) + O(n)， 空间复杂度 O(1)

 解法二: 空间换时间，遍历一次存入map， 时间复杂度 O(n), 空间复杂度 O(n)

 解法三: 鸽巢原理，由于题中有个条件"所有数字都在0~n-1的范围"，因此可以利用数组下标，将nums[i]的值放到nums[nums[i]]
 中完成归位，swap(nums[i], nums[nums[i]])完成nums[i]的归位，同时nums[nums[i]]被交换到当前的位置i，判断是否已归位继续
 往下while循环
 时间复杂度 O(n) ， 空间复杂度O(1)
 */
public class FindRepeatNumber {

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 1, 0, 2, 5, 3};

        FindRepeatNumber fp = new FindRepeatNumber();
        System.out.println(fp.findRepeatNumber(array));
    }

    public int findRepeatNumber(int[] nums) {

        for(int i=0; i<nums.length; i++){
            while(i != nums[i]){
                //判断被换位置的元素是否已经是归位状态的,如果已归位则发现重复
                if(nums[nums[i]] == nums[i]){
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return 0;
    }

    private void swap(int nums[], int j, int k){
        int temp = nums[j];
        nums[j] = nums[k];
        nums[k] = temp;
    }

}
