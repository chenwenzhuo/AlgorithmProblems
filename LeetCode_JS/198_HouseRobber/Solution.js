/**
 * @param {number[]} nums
 * @return {number}
 */
var rob = function (nums) {
    const n = nums.length;
    // dp[i]表示在前i个房子中能获得的最大金额
    const dp = new Array(n + 1).fill(0);
    dp[1] = nums[0];
    for (let i = 2; i <= n; i++) {
        // 在偷/不偷当前房子之间选择
        dp[i] = Math.max(
            dp[i - 2] + nums[i - 1], // 偷，则前一间不能偷
            dp[i - 1], // 不偷，则所得金额与dp[i - 1]相同
        );
    }
    return dp[n];
};