/**
 * @param {number[]} nums
 * @param {number} k
 * @return {boolean}
 */
const checkSubarraySum = function (nums, k) {
    const n = nums.length;
    const prefixSum = new Array(n + 1).fill(0); // prefixSum[i]表示前i个元素的和
    for (let i = 1; i <= n; i++)
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
    // 子数组之和为k的倍数，即前缀和的差为k的倍数，即两个前缀和对k取余相同
    // 用哈希表存储各前缀和对k的余数，有相同时即存在“好的子数组”
    const map = new Map();
    for (let i = 0; i <= n; i++) {
        const remainder = prefixSum[i] % k;
        if (map.has(remainder) && i - map.get(remainder) >= 2)
            return true;
        // 仅存储每个余数第一次出现的下标，使子数组尽可能长
        if (!map.has(remainder))
            map.set(remainder, i);
    }
    return false;
};