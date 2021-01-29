package leetcode.array.binary_search;

/**
 * 875. 爱吃香蕉的珂珂
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *
 *
 *
 * 示例 1：
 *
 * 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 * 示例 2：
 *
 * 输入: piles = [30,11,23,4,20], H = 5
 * 输出: 30
 * 示例 3：
 *
 * 输入: piles = [30,11,23,4,20], H = 6
 * 输出: 23
 *
 *
 * 提示：
 *
 * 1 <= piles.length <= 10^4
 * piles.length <= H <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 * 思路：不断对于速度k做二分，   left=1, right=piles[max] //因为 H>=piles.length, 因此找出piles的最大值作为初始最大速度
 * 如果mid能满足，记录mid，尝试更小的速度，right-1
 * 如果mid不能满足，left+1，说明需要更大的速度k， left+1
 */
public class MinEatingSpeed {

    public int minEatingSpeed(int[] piles, int H) {
        int ans=0;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<piles.length;i++){
            max = Math.max(piles[i], max);
        }
        int left=1;
        int right=max;
        while(left<=right){
            int mid = (left+right)>>>1;
            if(canSatisfy(mid, piles, H)){
                ans = mid;
                right=mid-1;
            } else {
                left=mid+1;
            }
        }

        return ans;
    }

    /**
     * 计算 以每小时k的速度吃完piles所有香蕉，能否满足小于H
     * @param k
     * @param piles
     * @param H
     * @return
     */
    private boolean canSatisfy(int k, int[] piles, int H){
        int actualCount = 0;
        for(int i=0;i<piles.length;i++){
            int remain = piles[i]%k;
            if(remain>0){
                actualCount += (piles[i]/k+1);
            } else {
                actualCount+=piles[i]/k;
            }
        }
        return H>=actualCount;
    }

    public static void main(String[] args){
        int[] piles = new int[]{30,11,23,4,20};
        MinEatingSpeed mes = new MinEatingSpeed();
        System.out.println(mes.minEatingSpeed(piles, 6));
    }
}
