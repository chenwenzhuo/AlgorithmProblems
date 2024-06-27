/**
 * @param {number[]} cost
 * @param {number[]} time
 * @return {number}
 */
var paintWalls = function (cost, time) {
    const n = cost.length;
    const memo = new Array(n).fill(0).
        map(() => new Array(n * 2 + 1).fill(-1));

    // 计算刷[0...range]范围内的墙，净剩时间为remainTime情况下，最低的开销
    // 选择付费刷下标i的墙，会累积time[i]单位时间，在免费刷下标j的墙时，扣除1单位时间
    // remainTime表示扣除后剩余的时间
    const calcMinCost = (range, remainTime) => {
        if (remainTime > range) // 剩余时间不少于剩余墙的数量，则所有墙都可以免费刷
            return 0;
        if (range < 0) // 以上条件不成立，则remainTime<=range
            return Infinity; // 若range<0成立，则remainTime也为负，说明选择免费刷的墙太多，付费时间不够

        const ind = remainTime + n; // 加上偏移量，防止下标出现负数
        if (memo[range][ind] !== -1)
            return memo[range][ind];

        // 下标range的墙选择付费刷，所需的开销
        const cost1 = calcMinCost(range - 1, remainTime + time[range]) + cost[range];
        // 下标range的墙选择免费刷，所需的开销
        const cost2 = calcMinCost(range - 1, remainTime - 1);
        memo[range][ind] = Math.min(cost1, cost2);
        return memo[range][ind];
    }
    return calcMinCost(n - 1, 0);
};