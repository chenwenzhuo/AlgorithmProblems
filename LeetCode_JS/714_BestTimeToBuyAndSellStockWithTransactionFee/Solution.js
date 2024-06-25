/**
 * @param {number[]} prices
 * @param {number} fee
 * @return {number}
 */
var maxProfit = function (prices, fee) {
    const n = prices.length;
    // dp[i][j]表示第i天结束，持股状态为j时，拥有的最大金额
    const dp = new Array(n).fill(0).
        map(() => new Array(2).fill(0));
    // base case
    dp[0][1] = -prices[0];

    for (let i = 1; i < n; i++) {
        // 卖出时扣除手续费
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
        dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
    }
    return dp[n - 1][0];
};