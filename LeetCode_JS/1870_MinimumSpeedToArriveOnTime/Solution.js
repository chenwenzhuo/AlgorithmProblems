/**
 * @param {number[]} dist
 * @param {number} hour
 * @return {number}
 */
let minSpeedOnTime = function (dist, hour) {
    //dist数组最大值
    let maxDist = dist.reduce((maxVal, cur) => Math.max(maxVal, cur), 0);
    /* 只有最后一段路程需要计算小数时间，其余的都向上取整。
     * 二分查找上限设为maxDist*100，
     * 因为hour为两位小数，通过maxDist的时间也最多保留两位小数。*/
    let low = 1,
        high = maxDist * 100;
    while (low <= high) {
        let mid = Math.floor((low + high) / 2);
        //计算当速度为mid时，走完全程需要的时间
        let total = 0;
        dist.forEach((d, index) => {
            //最后一段路程计算小数，其余的向上取整
            if (index === dist.length - 1) {
                total += d / mid;
            } else {
                total += Math.ceil(d / mid);
            }
        });
        if (total > hour) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    //所需时速超过上限，则无法按时到达，返回-1
    return low > maxDist * 100 ? -1 : low;
};