/**
 * @param {number[]} nums
 * @return {number}
 */
var countWays = function (nums) {
  const n = nums.length;
  nums.sort((a, b) => a - b);
  let total = 0;
  // 一个都不选，则需要nums[0]大于0
  // 若全选，需要nums[n-1]<n
  if (nums[0] > 0)
    total++;
  if (nums[n - 1] < n)
    total++;
  // 若选择其他数量，则必然选择从下标0开始的连续若干个
  for (let chosenCnt = 1; chosenCnt < n; chosenCnt++) {
    if (nums[chosenCnt - 1] < chosenCnt && chosenCnt < nums[chosenCnt])
      total++;
  }
  return total;
};