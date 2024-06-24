/**
 * @param {number[][]} envelopes
 * @return {number}
 */
var maxEnvelopes = function (envelopes) {
    const n = envelopes.length;
    // 先按宽度升序排序，宽度相同时按高度降序排序
    envelopes.sort((a, b) => {
        if (a[0] !== b[0])
            return a[0] - b[0];
        return b[1] - a[1];
    });
    // 对envelopes的第二维（高度）求最长递增子序列
    const top = new Array(n).fill(0);
    let piles = 0;
    for (let i = 0; i < n; i++) {
        const envH = envelopes[i][1]; // 当前信封高度
        // 搜索左边界的二分查找
        let left = 0, right = piles;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (top[mid] < envH)
                left = mid + 1;
            else
                right = mid;
        }
        if (left === piles) // 没找到，新建一堆
            piles++;
        top[left] = envH;
    }
    return piles;
};

console.log(maxEnvelopes([[5, 4], [6, 4], [6, 7], [2, 3]]));