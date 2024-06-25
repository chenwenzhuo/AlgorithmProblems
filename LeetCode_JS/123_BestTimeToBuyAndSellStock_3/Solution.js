/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    const n = prices.length;
    // dp[i][j][k]表示在第i天结束，已经完成j次交易，持股状态为k时的最大利润
    // j为0、1、2，表示交易次数，k为1或0，表示持股/不持股
    const dp = new Array(n).fill(0).
        map(() => new Array(3).fill(0).
            map(() => new Array(2).fill(0)));

    for (let i = 0; i < n; i++) {
        for (let j = 1; j < 3; j++) {
            // 第0天结束，状态为持股，无论交易多少次，利润都为-prices[0]
            if (i === 0) {
                dp[0][j][1] = -prices[0];
                continue;
            }
            dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
            dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
        }
    }
};
