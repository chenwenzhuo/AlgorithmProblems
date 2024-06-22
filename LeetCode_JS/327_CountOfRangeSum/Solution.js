/**
 * @param {number[]} nums
 * @param {number} lower
 * @param {number} upper
 * @return {number}
 */
var countRangeSum = function (nums, lower, upper) {
    const n = nums.length;
    // 计算nums的前缀和数组
    const preSum = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++)
        preSum[i] = preSum[i - 1] + nums[i - 1];

    const cache = new Array(n + 1).fill(0); // 辅助归并排序
    let count = 0;

    // 对前缀和数组归并排序
    const mergeSort = (lo, hi) => {
        if (lo >= hi)
            return;
        // 递归排序子区间
        const mid = (lo + hi) >> 1;
        mergeSort(lo, mid);
        mergeSort(mid + 1, hi);

        // 维护左闭右开区间[start, end)，其中的元素与nums[i]的差在[lower, upper]范围内
        let start = mid + 1, end = mid + 1;
        for (let i = lo; i <= mid; i++) {
            // 如果 preSum[i] 对应的区间是 [start, end)，
            // 那么 preSum[i+1] 对应的区间一定会整体右移，类似滑动窗口
            while (start <= hi && preSum[start] - preSum[i] < lower)
                start++;
            while (end <= hi && preSum[end] - preSum[i] <= upper)
                end++;
            count += end - start;
        }

        // 归并子区间排序的结果
        for (let k = lo; k <= hi; k++)
            cache[k] = preSum[k];
        let i = lo, j = mid + 1;
        let p = lo;
        while (i <= mid && j <= hi) {
            if (cache[i] <= cache[j])
                preSum[p] = cache[i++];
            else
                preSum[p] = cache[j++];
            p++;
        }
        while (i <= mid) {
            preSum[p] = cache[i++];
            p++;
        }
        while (j <= hi) {
            preSum[p] = cache[j++];
            p++;
        }
    }

    mergeSort(0, n);
    return count;
};