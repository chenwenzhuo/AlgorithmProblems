package com.hey_there.BestTimeToBuyAndSellStock_3;

public class Solution {
    public int maxProfit(int[] prices) {
        int len_prices = prices.length;
        //dp[i][j][0]表示当前是第i天，最多允许进行j次交易，且当前未持有股票，能获得的最大利润
        //dp[i][j][1]表示当前是第i天，最多允许进行j次交易，且当前持有股票，能获得的最大利润
        int[][][] dp = new int[len_prices + 1][3][2];
        //初始化base case
        for (int i = 0; i <= len_prices; i++) {
            //j为0，表示不允许交易，在任意一天的利润都为0
            dp[i][0][0] = 0;
            //j为0，不允许交易，也就不可能持有股票，令其为“负无穷”表示不可能
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        for (int j = 0; j < 3; j++) {
            //i为0，表示交易未开始，无论允许交易多少次，都没有利润
            dp[0][j][0] = 0;
            //i为0，表示交易未开始，也就不可能持有股票，令其为“负无穷”表示不可能
            dp[0][j][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i <= len_prices; i++) {
            for (int j = 1; j < 3; j++) {
                //今天未持有股票，可能有两种情况
                dp[i][j][0] = Math.max(dp[i - 1][j][0],//昨天也未持有，今天选择不操作
                        dp[i - 1][j][1] + prices[i - 1]);//昨天持有，今天选择卖掉
                //今天持有股票，可能有两种情况
                dp[i][j][1] = Math.max(dp[i - 1][j][1], //昨天也持有，今天选择不操作
                        dp[i - 1][j - 1][0] - prices[i - 1]);//昨天未持有，今天选择买入
            }
        }
        return dp[len_prices][2][0];
    }
}
