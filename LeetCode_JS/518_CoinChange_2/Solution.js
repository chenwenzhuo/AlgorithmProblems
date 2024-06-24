/**
 * @param {number} amount
 * @param {number[]} coins
 * @return {number}
 */
var change = function (amount, coins) {
    const coinTypes = coins.length;
    // dp[i][j]表示使用前i种硬币，凑成金额j的方式数
    //base case：dp[0][...]为0和dp[...][0]为1，即可选硬币为0则无法凑出任何金额，目标金额为0则只有1种凑法
    const dp = new Array(2).fill(0).
        map(() => new Array(amount + 1).fill(0));
    dp[0][0] = 1;
    dp[1][0] = 1;

    for (let i = 1; i <= coinTypes; i++) {
        const curRow = i % 2, prevRow = (i - 1) % 2;
        for (let j = 1; j <= amount; j++) {
            if (j >= coins[i - 1]) // 目标金额大于等于硬币面额，可以选择是否使用当前硬币
                // 硬币可重复选择，所以此处是dp[curRow][j-coins[i-1]]，不是dp[prevRow][j-coins[i-1]]
                dp[curRow][j] = dp[curRow][j - coins[i - 1]] + dp[prevRow][j];
            else
                dp[curRow][j] = dp[prevRow][j]; // 目标金额小于硬币面额，无法选择当前硬币
        }
    }
    return dp[coinTypes % 2][amount];
};