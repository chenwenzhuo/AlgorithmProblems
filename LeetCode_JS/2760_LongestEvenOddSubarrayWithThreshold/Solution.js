/**
 * @param {number[]} nums
 * @param {number} threshold
 * @return {number}
 */
let longestAlternatingSubarray = function (nums, threshold) {
    let ans = 0;
    let low = 0,
        high = 0;
    while (low < nums.length) {
        // 寻找一个不大于阈值的偶数作为子数组起点
        while (low < nums.length &&
            (nums[low] > threshold ||
                nums[low] % 2 === 1)) {
            low++;
        }
        high = low + 1;
        // nums[high]不大于阈值，且与前一个元素不同奇偶，则继续扩大范围
        while (high < nums.length && nums[high] <= threshold &&
            nums[high] % 2 !== nums[high - 1] % 2) {
            high++;
        }
        // 更新答案
        if (low < nums.length) {
            ans = Math.max(ans, high - low);
        }
        low = high;
    }
    return ans;
};