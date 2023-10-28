/**
 * @param {number[]} candies
 * @param {number} k
 * @return {number}
 */
let maximumCandies = function (candies, k) {
    //二分搜索上界设为糖果总数平均分为k份时的每堆大小
    let low = 0,
        high = Math.floor(
            candies.reduce((sum, cur) => sum + cur, 0) / k);
    while (low <= high) {
        let mid = Math.floor((low + high) / 2);
        //计算每人拿mid个时，candies数组中所有元素能分出多少个大小为mid的堆
        let cnt = 0;
        candies.forEach(pile => cnt += Math.floor(pile / mid));
        if (cnt < k) { //分出的数量少于k堆
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return high;
};