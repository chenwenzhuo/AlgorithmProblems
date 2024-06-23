/**
 * @param {number[]} nums
 * @return {number}
 */
var lengthOfLIS = function (nums) {
    const n = nums.length;
    // dp[i]表示以nums[i]结尾的最长递增子序列的长度
    // 初始化为1，以每个值结尾的子序列长度至少为1
    const dp = new Array(n).fill(1);
    let res = 1;
    for (let i = 1; i < n; i++) {
        // 在[0...i-1]区间寻找比nums[i]小的元素，尝试更新dp[i]
        for (let j = 0; j < i; j++) {
            if (nums[j] < nums[i])
                dp[i] = Math.max(dp[i], dp[j] + 1)
        }
        res = Math.max(res, dp[i]);
    }
    return res;
};

var lengthOfLIS = function (nums) {
    const n = nums.length;
    const top = new Array(n).fill(0);
    let len = 0;
    for (let i = 0; i < n; i++) {
        // 二分搜索，在top[0...len]范围内寻找比nums[i]大、最靠左的元素
        let left = 0, right = len;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (top[mid] < nums[i])
                left = mid + 1;
            else
                right = mid;
        }

        if (left === len)
            len++; // 没找到所需元素
        top[left] = nums[i];
    }
    return len;
};