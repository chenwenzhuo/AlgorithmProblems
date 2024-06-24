/**
 * @param {string} s
 * @return {number}
 */
var minInsertions = function (s) {
    const len = s.length;
    // dp[i][j]表示使s[i...j]闭区间内子串回文需要的最少插入次数
    // base case：i与j相同，长度为1的子串一定回文，次数为0
    const dp = new Array(len).fill(0).
        map(() => new Array(len).fill(0));

    // 沿对角线遍历并计算dp[i][j]
    // 对角线上j>=i，inc表示j相对于i的增量
    for (let inc = 1; inc <= len - 1; inc++) {
        for (let i = 0; i < len - 1; i++) {
            const j = i + inc;
            // inc为1，则子串长度为2，首尾字符相同则子串回文
            // inc大于1，则还额外需要中间子串回文
            if (s[i] === s[j])
                dp[i][j] = inc === 1 ? 0 : dp[i + 1][j - 1];
            else
                // s[i]与s[j]不同，可以选择：
                dp[i][j] = Math.min(
                    dp[i + 1][j - 1] + 2, // 首尾分别插入s[j]和s[i]
                    dp[i][j - 1] + 1, // 在子串首插入s[j]，s[i]继续和s[j-1]比较
                    dp[i + 1][j] + 1, // 在子串尾插入s[i]，s[j]继续和s[i+1]比较
                );
        }
    }
    return dp[0][len - 1];
};