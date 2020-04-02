package leecode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例:
 *
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 * 思路：使用暴力法时间复杂度是O(n*n)
 *
 *  可以空间换时间，借助一个O(n)链表
 *  将原始数组倒叙遍历， 以升序插入到一个新的list，插入时可以二分搜索插入，原数组元素在插入后的新数组
 *  的下标即 等价于 元素在原数组中右侧比它更小的元素
 *  时间复杂度 O(n * logn), 由于实现采用的是ArrayList，虽然查找时间是O(logn)，但是插入时移动元素时间是n
 *
 *  [1,3,6,1,2,3]
 *
 * input 3,  output: [3] -> 3 左边有 0 个数
 * input 2,  output: [2,3] -> 2 左边有 0 个数
 * input 1,  output: [1,2,3] -> 1 左边有 0 个数
 * input 6,  output: [1,2,3,6] -> 6 左边有 3 个数
 * input 3', output: [1,2,3',3,6] -> 3' 左边有 2 个数
 * input 1', output: [1',1,2,3',3,6] -> 1' 左边有 0 个数
 *
 *  代码中注意临界点 二分时 right = mid,
 *                          left = mid+1
 */
public class CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args){
//        int[] nums = new int[]{5,2,6,1};
        int[] nums = new int[]{-1, -1};

        CountOfSmallerNumbersAfterSelf c = new CountOfSmallerNumbersAfterSelf();

        System.out.println(c.countSmaller(nums));
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> temp = new ArrayList<>(nums.length);
        Integer[] result = new Integer[nums.length];

        for(int i=nums.length-1;i>=0;i--){
            int left = 0;
            int right = temp.size();
            while (left < right) {
                int mid = left + (right - left)/2;
                if (temp.get(mid) >= nums[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            temp.add(left, nums[i]);
            result[i] = left;
        }

        return Arrays.asList(result);
    }



}
