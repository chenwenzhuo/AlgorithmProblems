/**
 * @param {number} n
 * @param {number[]} quantities
 * @return {number}
 */
let minimizedMaximum = function (n, quantities) {
    //二分查找上限为quantities数组中的最大值
    let low = 1,
        high = quantities.reduce(
            (maxVal, cur) => Math.max(maxVal, cur), 0);
    while (low <= high) {
        let mid = Math.floor((low + high) / 2);
        //计算每个店最多分mid件商品时，总共需要的店的数量
        let cnt = 0;
        quantities.forEach(quantity => cnt += Math.ceil(quantity / mid));
        if (cnt > n) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return low;
};