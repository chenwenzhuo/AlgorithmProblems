/**
 * @param {number[]} nums
 * @param {number} threshold
 * @return {number}
 */
let smallestDivisor = function (nums, threshold) {
    // 题目要求除法求商后向上取整，故最后的求和结果最小为nums.length
    // 二分查找的上限也可设为nums中的最大值
    let low = 1,
        high = nums.reduce((maxVal, cur) => Math.max(maxVal, cur), 0);
    while (low <= high) {
        let mid = Math.ceil((low + high) / 2);
        // 用mid做除数去除nums中所有数，并对结果求和
        let sum = 0;
        nums.forEach(n => sum += Math.ceil(n / mid));
        if (sum > threshold) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return low;
};

let nums = [44, 22, 33, 11, 1];
let threshold = 5;
let res = smallestDivisor(nums, threshold);
console.log(res);