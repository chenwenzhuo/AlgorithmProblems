package com.hey_there.BestTimeToBuyAndSellStockWithTransactionFee;

public class Solution {
    public int maxProfit(int[] prices, int fee) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, temp - price - fee);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        Solution solution = new Solution();
        int mostProfit = solution.maxProfit(prices, fee);
        System.out.println(mostProfit);
    }
}
