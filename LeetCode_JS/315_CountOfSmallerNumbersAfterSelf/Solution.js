/**
 * @param {number[]} nums
 * @return {number[]}
 */
var countSmaller = function (nums) {
    const n = nums.length;
    // 将nums映射为一个包含其值和下标的二维数组
    const pairs = nums.map((num, index) => [num, index]);
    const cache = new Array(n).fill(0); // 辅助归并排序
    const counts = new Array(n).fill(0); // 结果数组

    // 归并排序，在归并时计算counts
    const mergeSort = (low, high) => {
        if (low >= high)
            return;
        const mid = (low + high) >> 1;
        // 排序子区间
        mergeSort(low, mid);
        mergeSort(mid + 1, high);

        // mid左右的子区间都已排序，执行归并
        for (let k = low; k <= high; k++)
            cache[k] = pairs[k];
        let i = low, j = mid + 1; // cache数组中两子区间的下标
        let p = low; // pairs数组的下标
        while (i <= mid && j <= high) {
            // 在cache中取较小者放入pairs
            if (cache[i][0] <= cache[j][0]) {
                pairs[p] = cache[i];
                // 闭区间[mid+1,high]中的元素在排序前一定是在[low,mid]中元素的右边的
                // 当前比cache[i][0]小的元素是cache[mid+1...j-1][0]，数量为j-mid-1
                counts[pairs[p][1]] += (j - mid - 1);
                i++;
            } else {
                pairs[p] = cache[j];
                j++;
            }
            p++;
        }
        while (i <= mid) {
            pairs[p] = cache[i];
            counts[pairs[p][1]] += (j - mid - 1);
            i++;
            p++;
        }
        while (j <= high) {
            pairs[p] = cache[j];
            j++;
            p++;
        }
    }

    mergeSort(0, n - 1);
    return counts;
};