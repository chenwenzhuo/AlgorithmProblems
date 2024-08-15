/**
 * @param {number[][]} grid
 * @return {number}
 */
var maxScore = function (grid) {
  const m = grid.length, n = grid[0].length;
  // dp[i][j]表示坐标(i,j)的左上方的最小矩阵值
  const dp = Array.from(new Array(m), () => new Array(n).fill(0));
  dp[0][0] = 0;
  dp[0][1] = grid[0][0];
  dp[1][0] = grid[0][0];

  let res = Math.max(grid[0][1] - dp[0][1], grid[1][0] - dp[1][0]);
  // 单独计算第一行/列
  for (let j = 2; j < n; j++) {
    dp[0][j] = Math.min(grid[0][j - 1], dp[0][j - 1]);
    res = Math.max(res, grid[0][j] - dp[0][j]);
  }
  for (let i = 2; i < m; i++) {
    dp[i][0] = Math.min(grid[i - 1][0], dp[i - 1][0]);
    res = Math.max(res, grid[i][0] - dp[i][0]);
  }

  for (let i = 1; i < m; i++) {
    for (let j = 1; j < n; j++) {
      dp[i][j] = Math.min(grid[i - 1][j], dp[i - 1][j], grid[i][j - 1], dp[i][j - 1]);
      res = Math.max(res, grid[i][j] - dp[i][j]);
    }
  }
  return res;
}