/**
 * 若直接使用求全排列的回溯，并增加筛选条件，会超时
 * 例如对于数组[1,2,4,5,6,9]，前三层递归无论是[1,2,4]还是[2,1,4]，
 * 只要使用的数字集合相同，且排列的最后一个数字相同，后续能组成的特别排列数量都是相同的
 * 题目条件说明数组长度不超过14，则可以使用一个数字的二进制位来表示nums[i]是否已经加入排列，
 * 加上通过参数传递的排列中最后一个数字，则可判断在当前 state + lastInPerm 条件下的特别排列数量
 * @param {number[]} nums
 * @return {number}
 */
var specialPerm = function (nums) {
    const n = nums.length, MOD = Math.pow(10, 9) + 7;
    const used = new Array(n).fill(false);
    const state2Perms = new Map(); // 存储状态与特别排列数量之间的映射
    let permLen = 0;

    const backtrack = (state, lastInPerm) => {
        if (permLen === n) // 当前排列长度为n，则只有一种情况
            return 1;

        // 构造全局唯一的键值，已存在则直接返回
        const key = `${state}_${lastInPerm}`;
        if (state2Perms.has(key))
            return state2Perms.get(key);

        // 遍历数组，选择加入排列中的下一个数
        let curAns = 0;
        for (let i = 0; i < n; i++) {
            if (used[i] || (permLen > 0 &&
                lastInPerm % nums[i] !== 0 &&
                nums[i] % lastInPerm !== 0
            )) // 已加入排列的元素、不满足要求的元素都跳过
                continue;
            permLen++;
            used[i] = true;
            const nextState = state | (1 << i);
            curAns = (curAns + backtrack(nextState, nums[i])) % MOD;
            permLen--;
            used[i] = false;
        }
        state2Perms.set(key, curAns);
        return curAns;
    }
    return backtrack(0, -1);
};

var specialPerm = function (nums) {
    const n = nums.length, MOD = Math.pow(10, 9) + 7;
    const used = new Array(n).fill(false);
    // 用数组代替Map，存储已经计算过的状态，n个数的所有状态数量为2^n
    // memo[i][j]表示在排列状态为i，最后一个数为nums[j]的情况下，能组成的特别排列数量
    const memo = new Array(1 << n).fill(0).
        map(() => new Array(n).fill(-1));
    let permLen = 0;

    const backtrack = (state, lastIndex) => {
        if (permLen === n) // 当前排列长度为n，则只有一种情况
            return 1;
        if (memo[state][lastIndex] !== -1)
            return memo[state][lastIndex];

        // 遍历数组，选择加入排列中的下一个数
        let curAns = 0;
        for (let i = 0; i < n; i++) {
            if (used[i] || (permLen > 0 &&
                nums[lastIndex] % nums[i] !== 0 &&
                nums[i] % nums[lastIndex] !== 0
            )) // 已加入排列的元素、不满足要求的元素都跳过
                continue;
            permLen++;
            used[i] = true;
            const nextState = state | (1 << i);
            curAns = (curAns + backtrack(nextState, i)) % MOD;
            permLen--;
            used[i] = false;
        }
        memo[state][lastIndex] = curAns;
        return curAns;
    }

    let ans = 0;
    permLen = 1;
    // 将排列的第一个数放在主函数中，避免传入一个无效的lastIndex
    for (let i = 0; i < n; i++) {
        const state = 1 << i; // 将nums[i]加入排列后的状态
        used[i] = true;
        ans = (ans + backtrack(state, i)) % MOD;
        used[i] = false;
    }
    return ans;
}
