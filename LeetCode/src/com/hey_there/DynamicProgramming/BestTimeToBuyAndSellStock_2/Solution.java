package com.hey_there.DynamicProgramming.BestTimeToBuyAndSellStock_2;

public class Solution {
    public int maxProfit(int[] prices) {
        int numPrices = prices.length;
        int totalProfit = 0;
        int buyingPrice = -1;
        boolean holdingStock = false;
        for (int i = 0; i < numPrices; i++) {
            //仅当下一个价格高于当前价格且未持有股票时可买进
            if (i < numPrices - 1 && prices[i] < prices[i + 1] && !holdingStock) {
                holdingStock = true;//买进
                buyingPrice = prices[i];//记录买入价格
            }
            //当价格即将下降且当前持有股票时选择卖出
            else if (i < numPrices - 1 && prices[i] > prices[i + 1] && holdingStock) {
                holdingStock = false;//卖出
                totalProfit += (prices[i] - buyingPrice);
                buyingPrice = -1;
            }
            //若最后一天还持有股票，则选择卖出
            if (i == numPrices - 1 && holdingStock) {
                totalProfit += (prices[i] - buyingPrice);
            }
        }
        return totalProfit;
    }
}
