/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function (nums, target) {
    const n = nums.length;
    let low = 0, high = n - 1;
    while (low <= high) {
        // 为了不出现nums[mid] === nums[low] === nums[high]的情况
        // 收缩low和high，忽略多余重复元素
        while (low < high && nums[low] === nums[low + 1])
            low++;
        while (low < high && nums[high] === nums[high - 1])
            high--;
        const mid = (low + high) >> 1;
        if (nums[mid] === target)
            return true;

        // mid落在断崖左侧，或[low,high]范围内不包含断崖
        if (nums[mid] >= nums[low]) {
            // [low,high]范围内包含断崖，且target在断崖右侧时，nums[low]>target成立
            if (nums[mid] < target || nums[low] > target)
                low = mid + 1;
            else
                high = mid - 1;
        } else { // mid落在断崖右侧
            if (nums[mid] > target || nums[high] < target)
                high = mid - 1;
            else
                low = mid + 1;
        }
    }
    return false;
}
