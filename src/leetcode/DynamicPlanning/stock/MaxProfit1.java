package leetcode.DynamicPlanning.stock;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 思路：左指针代表买入价格，左指针遇到比当前小的数进行更新，
 * 从左向右遍历，如果比左指针大则记录差值
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/7 14:34
 */
public class MaxProfit1 {

    public int maxProfit(int[] prices) {
        if (prices==null || prices.length==0) {
            return 0;
        }
        int maxProfit = 0;
        int buy = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[buy]){
                maxProfit = Math.max(prices[i]-prices[buy], maxProfit);
            } else {
                buy=i;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args){
        int[] prices = new int[]{7,1,5,3,100};

        MaxProfit1 maxProfit1 = new MaxProfit1();
        System.out.println(maxProfit1.maxProfit(prices));
    }
}
