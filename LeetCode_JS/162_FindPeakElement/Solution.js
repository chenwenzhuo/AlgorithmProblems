/**
 * @param {number[]} nums
 * @return {number}
 */
const findPeakElement = function (nums) {
    const n = nums.length;
    let low = 0, high = n - 1;
    while (low < high) {
        const mid = (low + high) >> 1;
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
            return mid; // 峰值，直接返回
        if (nums[mid] < nums[mid + 1]) // 爬坡
            low = mid + 1;
        else if (nums[mid] > nums[mid + 1]) // 下坡
            high = mid - 1;
    }
    return low;
};
