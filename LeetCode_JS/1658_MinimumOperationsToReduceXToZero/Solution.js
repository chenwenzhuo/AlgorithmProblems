/**
 * @param {number[]} nums
 * @param {number} x
 * @return {number}
 */
const minOperations = function (nums, x) {
    const n = nums.length;
    const prefixSum = new Array(n + 1).fill(0); // prefixSum[i]表示前i个元素的和
    for (let i = 1; i <= n; i++)
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
    // 从nums首尾移除元素，使其和为x，等价于剩余元素和为sum-x
    // 剩余元素一定为连续子数组，则需要两个前缀和之差为sum-x
    const diff = prefixSum[n] - x;
    const map = new Map([[0, 0]]); // 存储前缀和与其下标的映射
    let ans = n + 1;
    for (let i = 0; i <= n; i++) {
        const need = prefixSum[i] - diff;
        if (map.has(need))
            ans = Math.min(ans, n - (i - map.get(need)));
        // 求最少操作数，则剩余子数组应该最长，故只需存储每个前缀和第一次出现的下标
        if (!map.has(prefixSum[i]))
            map.set(prefixSum[i], i);
    }
    return ans === n + 1 ? -1 : ans;
};