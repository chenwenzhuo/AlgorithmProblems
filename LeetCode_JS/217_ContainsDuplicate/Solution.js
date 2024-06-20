/**
 * @param {number[]} nums
 * @param {number} k
 * @return {boolean}
 */
const containsDuplicate = function (nums, k) {
    const set = new Set();
    for (let n of nums) {
        if (set.has(n))
            return true;
        set.add(n);
    }
    return false;
};