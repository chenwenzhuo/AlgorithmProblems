/**
 * @param {number[]} gas
 * @param {number[]} cost
 * @return {number}
 */
var canCompleteCircuit = function (gas, cost) {
    const n = gas.length;
    // tank表示完成一段路程后油箱剩余的油、tankMin为tank的最小值
    let tank = 0, tankMin = 0;
    let start = 0;
    for (let i = 0; i < n; i++) {
        tank += (gas[i] - cost[i]);
        // tank的值到达新低，则从start开始，到达节点i后，无法继续到达i+1
        // 可得出，从[start,i]区间的任意点开始，到达i后都无法继续到达i+1
        // 所以起点不能是[start,i]区间内的点，只能更新start为i+1
        if (tank < tankMin) {
            start = i + 1;
            tankMin = tank;
        }
    }
    // 总油量小于总消耗，无解
    if (tank < 0)
        return -1;
    // 环形数组特性，start为n时，与0指向的是同一节点
    return start == n ? 0 : start;
};