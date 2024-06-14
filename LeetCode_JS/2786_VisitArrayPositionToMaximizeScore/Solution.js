/**
 * @param {number[]} nums
 * @param {number} x
 * @return {number}
 */
const maxScore = function (nums, x) {
    const n = nums.length;
    // 对于所有j<i，将所有nums[j]为奇数的最高得分记为oddMax,为偶数的最高得分记为evenMax
    let oddMax = -Infinity, evenMax = -Infinity;
    if (nums[0] % 2 === 1)
        oddMax = nums[0];
    else evenMax = nums[0];

    let highScore = nums[0];
    for (let i = 1; i < n; i++) {
        let curScore;
        if (nums[i] % 2 === 1) {
            curScore = Math.max(oddMax, evenMax - x) + nums[i];
            oddMax = Math.max(oddMax, curScore);
        } else {
            curScore = Math.max(oddMax - x, evenMax) + nums[i];
            evenMax = Math.max(evenMax, curScore);
        }
        highScore = Math.max(highScore, curScore);
    }
    return highScore;
};
