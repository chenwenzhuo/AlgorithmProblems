/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maximumBeauty = function (nums, k) {
    nums.sort((a, b) => a - b); // 美丽值依赖子序列，可以排序
    // 设最大美丽值对应的数组值为x，则原数组中位于区间[x-k,x+k]内的值最多
    // 即滑动窗口中，最大最小值相差最多2k
    let low = 0, high = 0, ans = 0;
    while (high < nums.length) {
        // 增大窗口，寻找high和low的最大差值
        while (high < nums.length && nums[high] - nums[low] <= k * 2)
            high++;
        ans = Math.max(ans, high - low);
        // 缩小窗口，直到差值不超过2k
        while (low < high && nums[high] - nums[low] > k * 2)
            low++;
    }
    return ans;
};
