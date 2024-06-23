/**
 * @param {number[][]} matrix
 * @return {number}
 */
var minFallingPathSum = function (matrix) {
    const n = matrix.length;
    // dp[i][j]表示下降到坐标(i,j)的最小值
    const dp = new Array(n).fill(0).
        map(() => new Array(n).fill(0));
    dp[0] = [...matrix[0]]; // base case
    for (let i = 1; i < n; i++) {
        for (let j = 0; j < n; j++) {
            const prev1 = j === 0 ? Infinity : dp[i - 1][j - 1],
                prev2 = dp[i - 1][j],
                prev3 = j === n - 1 ? Infinity : dp[i - 1][j + 1];
            dp[i][j] = matrix[i][j] + Math.min(prev1, prev2, prev3);
        }
    }
    // 检查dp最后一行，记录最小值
    let res = Infinity;
    for (let ind = 0; ind < n; ind++)
        res = Math.min(res, dp[n - 1][ind]);
    return res;
};