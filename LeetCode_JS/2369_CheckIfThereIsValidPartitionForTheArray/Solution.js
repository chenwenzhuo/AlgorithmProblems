/**
 * @param {number[]} nums
 * @return {boolean}
 */
const validPartition = function (nums) {
    const len = nums.length;
    if (len === 2)
        return nums[0] === nums[1];
    // dp[i]表示下标[0,i]区间内的子数组是否存在有效划分
    const dp = new Array(len).fill(false);
    if (nums[0] === nums[1]) // 初始化base case
        dp[1] = true;
    if (
        (nums[0] === nums[1] && nums[1] === nums[2]) ||
        (nums[0] + 1 === nums[1] && nums[1] + 1 === nums[2])
    )
        dp[2] = true;

    for (let i = 3; i < len; i++) {
        const case1 = dp[i - 2] && nums[i] === nums[i - 1];
        if (case1) {
            dp[i] = true;
            continue;
        }
        dp[i] = dp[i - 3] && (
            (nums[i] === nums[i - 1] && nums[i] === nums[i - 2]) ||
            (nums[i] - 1 === nums[i - 1] && nums[i] - 2 === nums[i - 2])
        );
    }
    return dp[len - 1];
};