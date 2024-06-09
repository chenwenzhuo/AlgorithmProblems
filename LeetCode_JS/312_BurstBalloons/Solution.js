/**
 * @param {number[]} nums
 * @return {number}
 */
const maxCoins = function (nums) {
    // 为nums数组首尾各添加一个1
    nums = [1, ...nums, 1];
    const length = nums.length;
    // dp[i][j]表示开戳破区间(i,j)内所有气球的最高得分
    const dp = new Array(length).fill(0)
        .map(() => new Array(length).fill(0));

    // 沿左上到右下的对角线遍历dp右上半部分，计算dp[i][j]
    // 这一区域内j一定不小于i，inc表示j相对于i的增量
    // inc从2开始，因为当inc为0和1时，开区间(i,i)和(i,i+1)为空，dp[i][i]和dp[i][i+1]一定为0
    for (let inc = 2; inc < length; inc++) {
        for (let i = 0; i < length - 2 && i + inc < length; i++) {
            let j = i + inc;
            for (let k = i + 1; k < j; k++) {
                dp[i][j] = Math.max(
                    dp[i][j],
                    dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j]
                );
            }
        }
    }
    return dp[0][length - 1];
};