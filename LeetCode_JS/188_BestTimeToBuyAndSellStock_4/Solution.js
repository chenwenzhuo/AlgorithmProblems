/**
 * @param {number} k
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (k, prices) {
    const n = prices.length;
    // dp[x][y][z]表示在第x天结束，已经完成y次交易，持股状态为z时的最大利润
    // y为[0...k]，表示交易次数，z为1或0，表示持股/不持股
    const dp = new Array(n).fill(0).
        map(() => new Array(k + 1).fill(0).
            map(() => new Array(2).fill(0)));

    for (let x = 0; x < n; x++) {
        for (let y = 1; y <= k; y++) {
            // 第0天结束，状态为持股，无论交易多少次，利润都为-prices[0]
            if (x === 0) {
                dp[0][y][1] = -prices[0];
                continue;
            }
            dp[x][y][0] = Math.max(dp[x - 1][y][0], dp[x - 1][y][1] + prices[x]);
            dp[x][y][1] = Math.max(dp[x - 1][y][1], dp[x - 1][y - 1][0] - prices[x]);
        }
    }
    return dp[n - 1][k][0];
};
