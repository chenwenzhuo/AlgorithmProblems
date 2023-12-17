/**
 * @param {number[]} cost
 * @return {number}
 */
const minCostClimbingStairs = function (cost) {
    const length = cost.length;
    // a、b分别表示到达前两级和前一级台阶的花费
    let a = 0, b = 0, ans;
    for (let i = 2; i <= length; i++) {
        ans = Math.min(a + cost[i - 2], b + cost[i - 1]);
        a = b;
        b = ans;
    }
    return ans;
};