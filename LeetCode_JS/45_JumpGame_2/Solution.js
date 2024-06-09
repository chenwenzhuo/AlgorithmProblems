/**
 * @param {number[]} nums
 * @return {number}
 */
var jump = function (nums) {
    const length = nums.length;
    let border = 0; // 记录在下标i，走一步能到达的最远位置，即边界下标
    let maxPos = 0; // 记录从一个不超过border的下标出发，走一步能到达的最远位置
    let steps = 0; // 步数
    for (let i = 0; i < length - 1; i++) {
        maxPos = Math.max(maxPos, i + nums[i]);
        if (i === border) {
            border = maxPos;
            steps++;
        }
    }
    return steps;
}
