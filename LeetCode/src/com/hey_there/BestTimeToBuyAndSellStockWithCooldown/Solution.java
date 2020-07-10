package com.hey_there.BestTimeToBuyAndSellStockWithCooldown;

public class Solution {
    //超时
    public int maxProfit_my(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int len_prices = prices.length;
        //dp[i]表示在第i天完成最后一笔交易，能获得的最大利润
        int[] dp = new int[len_prices];
        dp[0] = 0;//第一天无法完成交易
        dp[1] = prices[1] > prices[0] ? prices[1] - prices[0] : 0;

        int mostProfit = Math.max(dp[1], 0);
        for (int i = 2; i < len_prices; i++) {//第i天是卖出日期
            for (int j = i - 1; j >= 0; j--) {//枚举所有可能的买入日期
                if (prices[j] < prices[i]) {
                    int tmpProfit = prices[i] - prices[j];
                    int k = j - 2;
                    int prevProfit = 0;
                    //从第k天开始向前查找利润最大的一天
                    while (k >= 0) {
                        prevProfit = Math.max(prevProfit, dp[k]);
                        k--;
                    }
                    tmpProfit += prevProfit;
                    dp[i] = Math.max(dp[i], tmpProfit);
                }
            }
            mostProfit = Math.max(mostProfit, dp[i]);
        }
        return mostProfit;
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for (int i = 1; i < n; ++i) {
            int newf0 = Math.max(f0, f2 - prices[i]);
            int newf1 = f0 + prices[i];
            int newf2 = Math.max(f1, f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }
        return Math.max(f1, f2);
    }

    public static void main(String[] args) {
        //int[] prices = {1, 2, 3, 0, 2};
        int[] prices = {6, 1, 6, 4, 3, 0, 2};
        Solution solution = new Solution();
        int mostProfit = solution.maxProfit_my(prices);
        System.out.println(mostProfit);
    }
}
