/**
 * @param {number[]} nums
 * @param {number[][]} queries
 * @return {boolean[]}
 */
var isArraySpecial = function (nums, queries) {
  const ln = nums.length, lq = queries.length;
  // dp[i]表示以nums[i]结尾的最长特殊数组的长度
  const dp = new Array(ln).fill(0);
  dp[0] = 1;
  for (let i = 1; i < ln; i++) {
    // 相邻两数的奇偶性不同，累加长度，否则长度为1
    if (nums[i - 1] % 2 !== nums[i] % 2)
      dp[i] = dp[i - 1] + 1;
    else
      dp[i] = 1;
  }

  const answer = new Array(lq).fill(false);
  queries.forEach((q, index) => {
    const [from, to] = q;
    answer[index] = dp[to] >= to - from + 1;
  });
  return answer;
}