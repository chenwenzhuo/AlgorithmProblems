/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    const n = prices.length;
    // dp[i][j]表示第i天结束，持股状态为j，获得的最大利润
    // j取值0、1、2，表示由于当天空闲不持股、由于当天卖出不持股、持股
    const dp = new Array(n).fill(0).
        map(() => new Array(3).fill(0));
    dp[0][2] = -prices[0];

    for (let i = 1; i < n; i++) {
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        dp[i][1] = dp[i - 1][2] + prices[i];
        if (i === 1)
            dp[i][2] = Math.max(dp[0][2], -prices[1]);
        else
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] - prices[i], dp[i - 2][1] - prices[i]);
    }
    return Math.max(dp[n - 1][0], dp[n - 1][1]);
};

var maxProfit = function (prices) {
    const n = prices.length;
    // dp[i][j]表示第i天结束，持股状态为j时，拥有的最大金额
    const dp = new Array(n).fill(0).
        map(() => new Array(2).fill(0));
    // base case
    dp[0][1] = -prices[0];
    if (n >= 2) {
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1], -prices[1]);
    }

    for (let i = 2; i < n; i++) {
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
    }
    return dp[n - 1][0];
}