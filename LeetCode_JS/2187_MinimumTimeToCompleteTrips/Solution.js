/**
 * @param {number[]} time
 * @param {number} totalTrips
 * @return {number}
 */
let minimumTime = function (time, totalTrips) {
    //用速度最慢的车完成所有旅途，花费的时间最多
    let mostTime = time.reduce((maxVal, cur) => Math.max(maxVal, cur), 0);
    let low = 1,
        high = mostTime * totalTrips;
    while (low <= high) {
        let mid = Math.floor((low + high) / 2);
        //计算在mid时间内能完成的旅途次数
        let total = 0;
        time.forEach(cur => total += Math.floor(mid / cur));
        if (total < totalTrips) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return low;
};