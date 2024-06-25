/**
 * @param {number[]} nums
 * @return {number}
 */
var rob = function (nums) {
    // 面对第一间房子，有偷/不偷两个选择
    const range1 = nums.slice(2, nums.length - 1); // 偷，则后续操作的范围变为range1
    const range2 = nums.slice(1); // 不偷，则后续操作的范围变为range2

    // 分别计算两种情况获得的的最大金额
    const getAmount = (range) => {
        const n = range.length;
        // dp[i]表示在前i个房子中能获得的最大金额
        const dp = new Array(n + 1).fill(0);
        dp[1] = range[0];
        for (let i = 2; i <= n; i++) {
            // 在偷/不偷当前房子之间选择
            dp[i] = Math.max(
                dp[i - 2] + range[i - 1], // 偷，则前一间不能偷
                dp[i - 1], // 不偷，则所得金额与dp[i - 1]相同
            );
        }
        return dp[n];
    };
    return Math.max(getAmount(range1) + nums[0], getAmount(range2));
};

var rob = function (nums) {
    const n = nums.length;
    if (n == 1) return nums[0];
    if (n == 2) return Math.max(nums[0], nums[1]);

    // 两遍dp，第一遍忽略最后一个，第二遍忽略第一个
    // 记录从区间[0,i-2]和[1,i-1]中可获得的最大金额
    let prev1 = nums[0], prev2 = nums[0]; // 第一遍一定偷第一个，则第二个不能偷
    let amount1 = nums[0];
    for (let i = 2; i < n - 1; i++) {
        amount1 = Math.max(prev1 + nums[i], prev2); // 在偷/不偷中选择较大者
        prev1 = prev2;
        prev2 = amount1;
    }
    // 第二遍一定不偷第一个，可抢第二个
    prev1 = 0;
    prev2 = nums[1];
    let amount2 = nums[1];
    for (let i = 2; i < n; i++) {
        amount2 = Math.max(prev1 + nums[i], prev2);
        prev1 = prev2;
        prev2 = amount2;
    }
    return Math.max(amount1, amount2);
}