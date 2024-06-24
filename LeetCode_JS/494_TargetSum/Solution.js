/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var findTargetSumWays = function (nums, target) { // 递归解法
    const n = nums.length;
    const waysMap = new Map(); // 存储(i,t)二元组和构造方式数之间的映射

    // 计算使用nums前i个数，构造得到结果t的方式数
    const getWays = (i, t) => {
        if (i === 1) { // 只使用一个数，只能构成绝对值相等的数
            if (nums[i - 1] === 0 && t === 0)
                return 2;
            return nums[i - 1] === Math.abs(t) ? 1 : 0;
        }

        const tupple = `${i}_${t}`;
        if (waysMap.has(tupple)) // 用前i个数构造数值t已计算过
            return waysMap.get(tupple);

        // 在当前数字前添加正负号，分别递归
        const ways = getWays(i - 1, t + nums[i - 1]) +
            getWays(i - 1, t - nums[i - 1]);
        waysMap.set(tupple, ways);
        return ways;
    }
    return getWays(n, target);
};

// 迭代解法，转化为背包问题
// 如果把nums划分成两个子集A和B，分别代表分配+的数和分配-的数，那么它们和target存在如下关系：
// sum(A) - sum(B) = target
// sum(A) = target + sum(B)
// sum(A) + sum(A) = target + sum(B) + sum(A)
// 2 * sum(A) = target + sum(nums)
// sum(A) = (target + sum(nums)) / 2
// 即，原问题转化成：nums中存在几个子集A，使得A中元素的和为(target+sum(nums))/2
var findTargetSumWays = function (nums, target) {
    const n = nums.length;
    let sum = 0;
    nums.forEach(item => sum += item);

    // 这两种情况，不可能存在合法的子集划分
    if (sum < Math.abs(target) || (sum + target) % 2 == 1)
        return 0;
    sum = (sum + target) / 2;

    // dp[i][j]表示使用nums前i个数，刚好填满容量j的方式数
    const dp = new Array(n + 1).fill(0).
        map(() => new Array(sum + 1).fill(0));
    dp[0][0] = 1;
    for (let i = 1; i <= n; i++) {
        for (let j = 0; j <= sum; j++) {
            if (j >= nums[i - 1])
                dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
            else
                dp[i][j] = dp[i - 1][j];
        }
    }
    return dp[n][sum];
}