/**
 * @param {number[]} nums
 * @return {number}
 */
var pivotIndex = function (nums) {
    const n = nums.length;
    const prefixSum = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++)
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
    for (let i = 0; i < n; i++) {
        const leftSum = prefixSum[i],
            rightSum = prefixSum[n] - prefixSum[i + 1];
        if (leftSum === rightSum)
            return i;
    }
    return -1;
};