/**
 * @param {number[]} nums
 * @return {number}
 */
const findMaxConsecutiveOnes = function (nums) {
    let ans = 0, left = 0, right = 0;
    while (left < nums.length) {
        if (nums[left] !== 1) { // 寻找一个1作为起点
            left++;
            continue;
        }
        // 增大窗口，直到nums[right]不为1
        right = left;
        while (right < nums.length && nums[right] === 1)
            right++;
        ans = Math.max(ans, right - left);
        left = right;
    }
    return ans;
};