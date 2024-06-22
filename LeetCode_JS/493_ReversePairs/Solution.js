/**
 * @param {number[]} nums
 * @return {number}
 */
var reversePairs = function (nums) {
    const n = nums.length;
    let cache = new Array(n).fill(0); // 辅助归并排序
    let cnt = 0;

    // 归并排序，归并过程中计算cnt
    const mergeSort = (low, high) => {
        if (low >= high)
            return;
        const mid = (low + high) >> 1;
        mergeSort(low, mid);
        mergeSort(mid + 1, high);

        // 对于左半边的每个nums[i]，都去右半边寻找符合条件的元素
        let end = mid + 1;
        for (let i = low; i <= mid; i++) {
            // 增大end，直到不满足nums[i]>2*nums[end]
            // 退出循环后，左闭右开区间[mid+1,end)内所有元素都满足要求
            while (end <= high && nums[i] > 2 * nums[end])
                end++;
            cnt += (end - (mid + 1));
        }

        // 复制当前区间到cache中
        for (let k = low; k <= high; k++)
            cache[k] = nums[k];

        // 执行归并
        let i = low, j = mid + 1;
        let p = low;
        while (i <= mid && j <= high) {
            if (cache[i] <= cache[j])
                nums[p] = cache[i++];
            else
                nums[p] = cache[j++];
            p++;
        }
        while (i <= mid) {
            nums[p] = cache[i++];
            p++;
        }
        while (j <= high) {
            nums[p] = cache[j++];
            p++;
        }
    }

    mergeSort(0, n - 1);
    return cnt;
};