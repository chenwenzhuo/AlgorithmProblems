/**
 * @param {number[]} nums
 * @param {number} k
 * @return {boolean}
 */
const containsNearbyDuplicate = function (nums, k) {
    const n = nums.length;
    const map = new Map(); // 记录数组中的值及其最近一次出现的下标
    // 遍历数组，检查每个值是否重复出现，及最近两次出现的距离
    let ind = 0;
    while (ind < n) {
        if (map.has(nums[ind]) && ind - map.get(nums[ind]) <= k)
            return true;
        map.set(nums[ind], ind);
        ind++;
    }
    return false;
};