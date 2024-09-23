/**
 * @param {number[]} values
 * @return {number}
 */
var maxScoreSightseeingPair = function (values) {
  const n = values.length;
  // dp[i]表示在values[0...i]区间内的最高分
  const dp = new Array(n).fill(0);
  dp[1] = values[0] + values[1] - 1;

  // 记录区间[0...i-1]区间内，values[j]+j的最大值
  let recordMax = Math.max(values[0], values[1] + 1);
  for (let i = 2; i < n; i++) {
    const cur = values[i] - i;
    // 对于当前values[i]，可以选或不选
    // 若选，则最优解为cur+recordMax，若不选则继承dp[i-1]的值
    dp[i] = Math.max(cur + recordMax, dp[i - 1]);
    recordMax = Math.max(recordMax, values[i] + i);
  }
  return dp[n - 1];
};

var maxScoreSightseeingPair = function (values) {
  const n = values.length;
  // 记录区间[0...i-1]区间内，values[j]+j的最大值
  let recordMax = Math.max(values[0], values[1] + 1);
  let maxScore = values[0] + values[1] - 1;
  for (let i = 2; i < n; i++) {
    const cur = values[i] - i;
    // 对于当前values[i]，可以选或不选
    // 若选，则最优解为cur+recordMax，若不选则继承前一次循环的值
    maxScore = Math.max(cur + recordMax, maxScore);
    recordMax = Math.max(recordMax, values[i] + i);
  }
  return maxScore;
}