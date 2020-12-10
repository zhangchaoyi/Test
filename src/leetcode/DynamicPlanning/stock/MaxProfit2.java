package leetcode.DynamicPlanning.stock;

/**
 * 122. 买卖股票的最佳时机 II
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/7 14:35
 * <p>
 * 思路：左指针代表买入，maxProfit代表全局最大利润；tempProfit代表当前段的最大利润
 * 一次遍历如果当前 num[i] 大于左指针，进行更新 maxProfit ，如果num[i]比上一个数小，进行更新左指针
 * 即记录每一段增量的区间
 * <p>
 * 动态规划： cash表示第i天不持股   hold表示第i天持股
 * cash[i] = max{cash[i-1], hold[i-1]+price[i]}
 * hold[i] = max{hold[i-1], cash[i-1]-price[i]}
 */
public class MaxProfit2 {

    public int maxProfitDp(int[] prices) {
        int[] cash = new int[prices.length];
        int[] hold = new int[prices.length];
        cash[0] = 0;
        hold[0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i]);
            hold[i] = Math.max(hold[i - 1], cash[i - 1] - prices[i]);
        }
        return cash[prices.length-1];
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int tempProfit = 0;
        int buy = 0;

        for (int i = 1; i < prices.length; i++) {
            //与上一个数比较是否需要更新左指针
            if (prices[i] < prices[i - 1]) {
                //更新左指针买入， 记录上一段的profit
                buy = i;
                maxProfit += tempProfit;
                tempProfit = 0;
            } else if (prices[i] >= prices[buy]) {
                tempProfit = Math.max(tempProfit, prices[i] - prices[buy]);
            }
        }
        maxProfit += tempProfit;
        return maxProfit;
    }

    /**
     * from leetcode , 记录每一段增量
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int accumulated = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                accumulated += prices[i + 1] - prices[i];
            }
        }
        return accumulated;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8};

        MaxProfit2 maxProfit1 = new MaxProfit2();
        System.out.println(maxProfit1.maxProfitDp(prices));
    }
}
