/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxLength = function (nums) {
    // 题目要找包含相同数量0和1的子数组，将0等价为-1，
    // 改为找包含相同数量-1和1的子数组，即和为0的最长子数组
    const n = nums.length;
    // prefixSum[i]表示前i个元素的和
    const prefixSum = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        prefixSum[i] = prefixSum[i - 1] +
            (nums[i - 1] === 0 ? -1 : 1); // 等价变换
    }

    const map = new Map(); // 记录前缀和的值对应的下标
    let res = 0;
    for (let i = 0; i <= n; i++) {
        // prefixSum[i]没出现过，则记录其下标
        if (!map.has(prefixSum[i]))
            map.set(prefixSum[i], i);
        else
            // prefixSum[i]出现过，则子数组nums[map.get(prefixSum[i])...i-1]之和为0
            res = Math.max(res, i - map.get(prefixSum[i])); // 尝试更新res
    }
    // 求最长子数组，所以只需要在map中记录每个不同的前缀和第一次出现的下标
    // 在后续遍历中，每次遇到重复值，都更新res
    return res;
};
