/**
 * @param {number[]} arr
 * @return {number}
 */
var peakIndexInMountainArray = function(arr) {
    const n = arr.length;
    let low = 0, high = n - 1;
    while (low < high) {
        const mid = (low + high) >> 1;
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1])
            return mid; // 峰值，直接返回
        if (arr[mid] < arr[mid + 1]) // 爬坡
            low = mid + 1;
        else if (arr[mid] > arr[mid + 1]) // 下坡
            high = mid - 1;
    }
    return low;
};