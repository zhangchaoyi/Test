package leetcode.DynamicPlanning;

/**
 *
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 *
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 *
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 *
 * 请你返回「表现良好时间段」的最大长度。
 *
 * 示例 1：
 *
 * 输入：hours = [9,9,6,0,6,6,9]
 * 输出：3
 * 解释：最长的表现良好时间段是 [9,9,6]。
 *  
 * 提示：
 *
 * 1 <= hours.length <= 10000
 * 0 <= hours[i] <= 16
 *
 *
 *
 *
 * 图解思路：  https://leetcode-cn.com/problems/longest-well-performing-interval/solution/can-kao-liao-ji-ge-da-shen-de-ti-jie-zhi-hou-zong-/
 *  观察到题目中给出数组中的元素有且只有两种,分别是大于8和小于等于8,而具体的数值没有意义.
 *  所以简单起见,我们用1代表大于8的元素,用-1代表小于等于8的元素,得到一个数组[1,1,-1,-1,-1,-1,1]记为arr.
 *
 * 现在题目变成了,我们需要找到一个子列,子列中1的元素数量严格大于-1的元素数量.
 *
 * 继续简化: 也就是需要找到一个子列,子列中所有元素的和大于0.
 *
 * 这时自然可以想到直接暴力遍历所有子列,找到和大于0且长度最大的子列即可.
 *
 *
 * 我们需要的仅仅是子列和,所以这里有一个技巧:
 *
 * i是数组的任意下标,计算arr[0]到arr[ i ]的和
 * 对数组中的每一个下标都计算一次,得到一个新的数组,它被称为前缀和数组
 * arr = [1, 1, -1, -1, -1, -1, 1]
 * prefixSum = []  # 前缀和数组
 *
 * cur_sum = 0
 * for val in arr:
 *     prefixSum.append(cur_sum)
 *     cur_sum += val
 * prefixSum.append(cur_sum)
 *
 * print(prefixSum)  # [1, 2, 1, 0, -1, -2, -1]
 * 这时就能很容易找到每一个子列和了,比如我想要(2,5) = arr[2]+arr[3]+...+arr[5]的和直接用prefixSum[5] - prefixSum[1]即可,得到结果是-4.
 *
 *
 */
public class LongestWellPerformingInterval {

    public static void main(String[] args){
        int[] hours = new int[]{9,9,6,0,6,6,9};
        LongestWellPerformingInterval longestWellPerformingInterval = new LongestWellPerformingInterval();
        longestWellPerformingInterval.longestWPI(hours);
    }

    public int longestWPI(int[] hours) {
        //转换数组
        int[] arr = new int[hours.length];
        for(int i=0; i<hours.length; i++){
            arr[i] = hours[i] > 8 ? 1 : -1;
        }
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        //求前缀和
        for(int i=1; i<arr.length; i++){
            prefixSum[i] = prefixSum[i-1] + arr[i];
        }
        //记录(i,j)的 和
        //暴力解法超出内存限制
        int lIndex = 0;
        int rIndex = 0;
        int max = Integer.MIN_VALUE;
        //dp用于保存(i,j) 区间内的和
        int[][] dp = new int[hours.length][hours.length];
        boolean existResult = false;
        //此处的两次for循环可以进行优化，内层遍历优化，
        // 区间【i ， j， k】只要 prefixSum[k] > prefixSum[i] 那么 不需要考虑 （i，j），最长的为(i, k) todo
        for(int i=0; i<arr.length; i++){
            for(int j=i; j<arr.length ;j++){
                //i==j 即数组该数本身
                if (i==j) {
                    dp[i][j] = arr[i];
                    continue;
                }
                int sumLeft = 0;
                if (i != 0) {
                    sumLeft = prefixSum[i-1];
                }

                dp[i][j] = prefixSum[j] - sumLeft;
            }
        }

        //遍历dp，dp[i][j] > 0 则说明满足题目的区间，需要找出最大的区间即可
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if (dp[i][j] > 0) {
                    existResult = true;
                    int diff = j - i;
                    if (diff > max) {
                        max = diff;
                        lIndex = i;
                        rIndex = j;
                    }
                }
            }
        }
        System.out.println("("+lIndex+","+rIndex+")");
        System.out.println("max:"+(max+1));

        return existResult?max+1:0;
    }

}
