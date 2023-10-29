/**
 * @param {number[]} weights
 * @param {number} days
 * @return {number}
 */
let shipWithinDays = function (weights, days) {
    //计算最大单个重量和总重量
    let maxWeight = 0,
        totalWeight = 0;
    weights.forEach(w => {
        maxWeight = Math.max(maxWeight, w);
        totalWeight += w;
    });
    //二分查找下限为单个货物的最大重量，
    //上限为weights数组所有元素之和，此时可以一次运完
    let low = maxWeight,
        high = totalWeight;
    while (low <= high) {
        let mid = Math.floor((low + high) / 2);
        //计算单次运力为mid时，运完所有货物所需天数
        let cnt = 0,
            capacity = mid;
        weights.forEach(w => {
            if (capacity >= w) { //剩余运力足够时，将当前货物装船
                capacity -= w;
            } else { //运力不够，结算一次天数并重新开始装载
                cnt++;
                capacity = mid - w;
            }
        });
        cnt++;//将最后一批运走
        if (cnt > days) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return low;
};