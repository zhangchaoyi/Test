package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 *560. 和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 * 思路：
 * 1.暴力法 O(n^2)
 * 2.前缀和
 * pre[i]表示(0..i)的前缀和， 则找出 pre[i]-pre[j]==k的所有情况
 *
 * 不能使用滑动窗口，因为有负数存在
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/17 14:53
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int[] pre = new int[nums.length+1];
        pre[0]=0;
        for(int i=0;i<nums.length;i++){
            pre[i+1]=pre[i]+nums[i];
        }
        int ans = 0;
        for(int i=0;i<nums.length+1;i++){
            for(int j=i+1;j<nums.length+1;j++){
                if (pre[j]-pre[i]==k) {
                    ans++;
                }
            }
        }

        return ans;
    }


    /**
     * 前缀和优化， 未理解
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }

            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,1,1};
//        int[] nums = new int[]{1,-1,0};
        SubarraySumEqualsK sse = new SubarraySumEqualsK();
        System.out.println(sse.subarraySum2(nums, 2));
    }
}
