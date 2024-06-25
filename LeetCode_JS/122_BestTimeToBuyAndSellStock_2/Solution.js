/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    const n = prices.length;
    // dp[i][j]表示，第i天结束，持股状态为j（0-不持股，1-持股）时，能获得的最大利润
    const dp = new Array(n).fill(0).
        map(() => new Array(2).fill(0));
    dp[0][1] = -prices[0]; // 第0天结束，买入股票

    for (let i = 1; i < n; i++) {
        // 第i天结束不持股，可能是前一天也不持股，或前一天持股但当天卖出
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        // 第i天结束持股，可能是前一天也持股，或前一天不持股但当天买入
        dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
    }
    return dp[n - 1][0];
};
