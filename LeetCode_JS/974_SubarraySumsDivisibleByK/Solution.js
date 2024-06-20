/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
const subarraysDivByK = function (nums, k) {
    const n = nums.length;
    const prefixSum = new Array(n + 1).fill(0); // prefixSum[i]表示前i个元素的和
    for (let i = 1; i <= n; i++)
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
    // 子数组之和可被k整除，即存在两前缀和之差可被k整除，即两前缀和对k取余相同
    const map = new Map(); // 存储每个余数出现的次数
    let ans = 0;
    for (let i = 0; i <= n; i++) {
        let remainder = prefixSum[i] % k;
        if (remainder < 0) // 余数可能为负，此时加上需要k
            remainder += k;
        if (map.has(remainder)) {
            ans += map.get(remainder);
            map.set(remainder, map.get(remainder) + 1);
        } else
            map.set(remainder, 1);
    }
    return ans;
};
