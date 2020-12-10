package leetcode.DynamicPlanning.stock;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:32
 *
 *
 * hold代表第i天持股
 * cashCold代表第i天不持股在冷冻期
 * cashNormal代表第i天不持股不在冷冻期
 *
 * hold[i]=max{hold[i-1], cashCold[i-1]-price[i]} //继续持股 或者 前一天是冷冻期今天买入
 * cashCold[i]= cashNormal[i-1] //说明前一天是卖出（不持股不在冷冻期）
 * cashNormal[i]=max{cashNormal[i-1], hold[i-1]+price[i]} //继续不持股不在冷冻期 或 前一天是持股今天卖出
 *
 */
public class MaxProfitCodePeriod {

    public int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        int[] cashCold = new int[prices.length];
        int[] cashNormal = new int[prices.length];
        int[] hold = new int[prices.length];

        hold[0] = -prices[0];
        cashCold[0] = 0;
        cashNormal[0] = 0;

        for(int i=1;i<prices.length;i++){
            hold[i]=Math.max(hold[i-1], cashCold[i-1]-prices[i]); //继续持股或者前一天是冷冻期
            cashCold[i]= cashNormal[i-1]; //说明前一天是卖出
            cashNormal[i]=Math.max(cashNormal[i-1], hold[i-1]+prices[i]);
        }
        return Math.max(cashCold[prices.length-1], cashNormal[prices.length-1]);
    }

    public static void main(String[] args){
        int[] prices = new int[]{1,2};
        MaxProfitCodePeriod maxProfitCodePeriod = new MaxProfitCodePeriod();
        System.out.println(maxProfitCodePeriod.maxProfit(prices));
    }
}
