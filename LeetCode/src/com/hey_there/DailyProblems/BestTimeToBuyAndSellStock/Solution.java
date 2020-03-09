package com.hey_there.DailyProblems.BestTimeToBuyAndSellStock;

public class Solution {
    public static int maxProfit(int[] prices) {
        int numPrices = prices.length;
        if (numPrices <= 1) {
            return 0;
        }

        int low = prices[0], high = 0;//low存储买入价格，high存储卖出价格
        int low_index = 0, high_index = -1;//买入和卖出价格在数组中的下标
        int profit = 0;
        for (int i = 1; i < numPrices; i++) {
            if (prices[i] < low) {
                //找到一个比low更低的价格
                low = prices[i];
                low_index = i;

                //找到更低的low后，需要重新找一个high
                high = 0;
                high_index = -1;
            } else if (prices[i] >= high) {
                //找到一个比high更高的价格
                high = prices[i];
                high_index = i;
            }

            if (high_index > low_index && high > low) {
                profit = Math.max(high - low, profit);
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit(prices));
    }
}
