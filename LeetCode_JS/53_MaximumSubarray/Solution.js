/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    const n = nums.length;
    // dp[i]表示以nums[i]结尾的子数组的最大和
    const dp = new Array(n).fill(0);
    dp[0] = nums[0]; // base case
    let ans = nums[0];
    for (let i = 1; i < n; i++) {
        // 对于dp[i]可选择将nums[i]作为独立子数组，或将其加入前一个子数组
        dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        ans = Math.max(ans, dp[i]);
    }
    return ans;
};