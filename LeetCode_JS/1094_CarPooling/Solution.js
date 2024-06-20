/**
 * @param {number[][]} trips
 * @param {number} capacity
 * @return {boolean}
 */
const carPooling = function (trips, capacity) {
    // 统计所有旅程中，from的最小值和to的最大值
    let minFrom = 1001, maxTo = -1;
    trips.forEach(t => {
        const [_, curFrom, curTo] = t;
        minFrom = Math.min(minFrom, curFrom);
        maxTo = Math.max(maxTo, curTo);
    });

    const diff = new Array(maxTo - minFrom + 1).fill(0); // 差分数组
    trips.forEach(t => {
        const [num, curFrom, curTo] = t;
        diff[curFrom - minFrom] += num;
        if (curTo - minFrom < diff.length)
            diff[curTo - minFrom] -= num;
    });
    // 从差分数组构造answer数组
    if (diff[0] > capacity)
        return false;
    for (let i = 1; i < diff.length; i++) {
        diff[i] += diff[i - 1];
        if (diff[i] > capacity)
            return false;
    }
    return true;
};

console.log(carPooling([[9, 0, 1], [3, 3, 7]], 4));