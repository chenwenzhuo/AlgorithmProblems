/**
 * @param {number[]} coins
 * @param {number} amount
 * @return {number}
 */
var coinChange = function(coins, amount) {
    const types = coins.length;
    // dp[i]表示凑成金额i的最少数量
    // base case：金额为0，则所需数量为0
    const dp = new Array(amount + 1).fill(10001);
    dp[0] = 0;
    for (let i = 1; i <= amount; i++) {
        for (const c of coins) {
            if (c <= i) // 当目标金额大于等于硬币面额时，进行计算
                dp[i] = Math.min(dp[i], dp[i - c] + 1);
        }
    }
    return dp[amount] === 10001 ? -1 : dp[amount];
};