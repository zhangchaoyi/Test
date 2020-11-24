package leetcode.DynamicPlanning;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 思路：
 * 1.暴力解法 O(n * n * n)
 * 2.前缀和 O(n*n) , dp[i][j] 表示区间 [i, j] 之间的和， dp[i][j] = sum[j] - sum[i-1]
 * <p>
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class MaxSubArray {

    /**
     * 动态规划，一次遍历 O(n)
     *
     * @param nums
     * @return
     */
    public int maxSubArrayPosition(int[] nums) {
        if (nums == null) {
            return 0;
        }
        //最终全局起始位置
        int start = 0;
        //最终全局结束位置
        int end = 0;
        //前一个子组合起始位置
        int subStart = 0;
        //前一个子组合结束位置
        int subEnd = 0;

        int max = nums[0];    // 全局最大值
        int subMax = nums[0];  // 前一个子组合的最大值

        /*
        正数越加越大，负数越加越小
        当前一个组合出现负数直接舍弃
         */
        for (int i = 1; i < nums.length; i++) {
            if (subMax > 0) {
                // 前一个子组合最大值大于0，正增益，更新最后元素位置
                subMax = subMax + nums[i];
                subEnd++;
            } else {
                // 前一个子组合最大值小于0，抛弃前面的结果，更新当前最大值位置
                subMax = nums[i];
                subStart = i;
                subEnd = i;
            }
            // 计算全局最大值，更新位置，将全局最优解的位置更新
            if (subMax > max) {
                max = subMax;
                start = subStart;
                end = subEnd;
            }
        }

        System.out.println("[" + start + "," + end + "]");
        return max;
    }

    /**
     * dp[i]表示以i结尾的连续子数组最大和，dp[i]=max{dp[i-1]+arr[i], arr[i]} ， 区分负数情况
     * 找出 max{dp[i]}即可
     * @param nums
     * @return
     */
    public int maxSubArrayPosition3(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
        }

        System.out.println(Arrays.toString(dp));

        return 0;
    }

    /**
     * dp[i]与dp[i-1]有关， 状态压缩，滚动数组，降低空间复杂度
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    /**
     * 暴力解法 O(n^3)
     *
     * @param nums
     * @return
     */
    public int maxSubArrayPosition1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                for (int k = i; k < j; k++) {
                    //累加
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        MaxSubArray maxSubArray = new MaxSubArray();
        System.out.println(maxSubArray.maxSubArrayPosition3(array));
    }
}
