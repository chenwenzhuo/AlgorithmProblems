/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
const numSubarrayProductLessThanK = function (nums, k) {
    const n = nums.length;
    let ans = 0;
    let left = 0, product = 1;
    // 枚举右边界
    for (let right = 0; right < n; right++) {
        product *= nums[right];
        //对于当前右边界，右移左边界，直到乘积小于k
        while (left <= right && product >= k) {
            product /= nums[left];
            left++;
        }
        //退出内层循环时，闭区间[left,right]内元素的乘积小于k
        //此时左边界为闭区间[left,right]中的任意值，均满足product<k
        ans += (right - left + 1);
    }
    return ans;
};