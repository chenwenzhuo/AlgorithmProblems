/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var subarraySum = function (nums, k) {
    const n = nums.length;
    const prefixSum = new Array(n + 1).fill(0); // prefixSum[i]表示前i个元素的和
    for (let i = 1; i <= n; i++)
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1];

    const map = new Map([[0, 1]]); // 存储前缀和及其出现次数的映射
    let cnt = 0;
    for (let i = 1; i <= n; i++) {
        // 如果之前存在值为need的前缀和，则存在以nums[i-1]结尾的子数组的和为k
        let need = prefixSum[i] - k;
        if (map.has(need))
            cnt += map.get(need);
        // 将当前前缀和存入哈希表
        if (!map.has(prefixSum[i]))
            map.set(prefixSum[i], 1);
        else map.set(prefixSum[i], map.get(prefixSum[i]) + 1);
    }
    return cnt;
};
