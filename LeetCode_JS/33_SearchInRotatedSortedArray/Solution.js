/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function (nums, target) {
    const n = nums.length;
    if (n === 1)
        return nums[0] === target ? 0 : -1;

    // 搜索旋转点（nums中最小元素的下标）
    let low = 0, high = n - 1;
    while (low < high) {
        const mid = (low + high) >> 1;
        // 当前mid指向最小元素，直接退出
        if (mid > 0 && nums[mid] < nums[mid - 1]) {
            low = mid;
            break;
        }
        if (mid === low) {
            low = nums[low] < nums[high] ? low : high;
            break;
        }
        if (nums[low] < nums[mid] && nums[high] < nums[mid])
            low = mid + 1;
        else if (nums[low] > nums[mid] && nums[high] > nums[mid])
            high = mid;
        else if (nums[low] < nums[mid] && nums[mid] < nums[high])
            high = mid - 1;
    }

    const rotateIndex = low;
    // 以rotateIndex为分界点，分别搜索两端
    const binarySearch = (left, right) => {
        while (left <= right) {
            const mid = (left + right) >> 1;
            if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
            else
                return mid;
        }
        return -1;
    }
    let ind = binarySearch(0, rotateIndex - 1);
    if (ind >= 0)
        return ind;
    return binarySearch(rotateIndex, n - 1);
};

var search = function (nums, target) {
    const n = nums.length;
    let low = 0, high = n - 1;
    while (low <= high) {
        const mid = (low + high) >> 1;
        if (nums[mid] === target)
            return mid;

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
    return -1;
}

console.log(search([4, 5, 6, 7, 0, 1, 2,], 6));
console.log(search([7, 0, 1, 2, 4, 5, 6,], 6));
console.log(search([1, 2, 4, 5, 6, 7, 0,], 1));
console.log(search([1, 2, 4, 5, 6, 7, 0,], 10));
console.log(search([1, 2, 4, 5, 6, 7, 0,], -10));
console.log(search([1, 2, 4, 5, 6, 7, 0,], 3));
console.log(search([3, 1], 0));
console.log(search([3, 1], 3));
console.log(search([1, 3], 0));
console.log(search([1, 3], 3));