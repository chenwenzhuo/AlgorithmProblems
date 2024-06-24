/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canPartition = function (nums) {
    // 数组求和，将和的一半作为容量，检查nums能否正好填满
    let sum = 0;
    nums.forEach(n => sum += n);
    if (sum % 2 === 1) // 和为奇数，无法分割
        return false;

    const capacity = sum / 2;
    // dp[i][j]表示使用前i个数，能否填满容量j
    // base case：i/j为0，没有数字则无法填满，容量为0则一定是满的
    // dp[i]只与dp[i-1]有关，则只需要使用一个2行的二维数组
    const dp = new Array(2).fill(0).
        map(() => new Array(capacity + 1).fill(false));
    dp[0][0] = true;
    dp[1][0] = true;

    for (let i = 1; i <= nums.length; i++) {
        const curRow = i % 2, prevRow = (i - 1) % 2;
        for (let j = 1; j <= capacity; j++) {
            // j>=nums[i - 1]，容量够，可选择是否放入nums[i-1]
            if (j >= nums[i - 1])
                dp[curRow][j] = dp[prevRow][j - nums[i - 1]] || dp[prevRow][j];
            else // 容量不够，一定不能放入
                dp[curRow][j] = dp[prevRow][j];
        }
    }
    return dp[(nums.length + 1) % 2][capacity];
};