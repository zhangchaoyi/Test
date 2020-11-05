package leetcode.array;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/5 15:54
 */
public class MaxSlidingWindow {

    /**
     * 思路：线性遍历找出k窗口中的最大值，窗口移动每次加一位进来比较得到下一个最大值，直到尽头
     * 需要一个额外的空间用于维护 k个数 的最大堆，每进来一个数插入最大堆
     *  O(N * log(k))  遍历-N ， 在堆中插入-log(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int max=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return null;
    }

    /**
     * 暴力解法 O(Nk)
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for(int j = i; j < i + k; j++)
                max = Math.max(max, nums[j]);
            output[i] = max;
        }
        return output;
    }

    public static void main(String[] args){

    }
}
