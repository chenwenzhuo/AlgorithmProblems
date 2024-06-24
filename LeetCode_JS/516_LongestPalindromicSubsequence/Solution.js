/**
 * @param {string} s
 * @return {number}
 */
var longestPalindromeSubseq = function (s) {
    const len = s.length;
    // dp[i][j]表示s[i...j]闭区间内子串的最长回文子序列长度
    // base case：i与j相同，长度为1的子串一定回文，长度为1
    const dp = new Array(len).fill(0).
        map(() => new Array(len).fill(0));
    for (let i = 0; i < len; i++)
        dp[i][i] = 1;

    // 沿对角线遍历并计算dp[i][j]
    // 对角线上j>=i，inc表示j相对于i的增量
    for (let inc = 1; inc <= len - 1; inc++) {
        for (let i = 0; i < len - 1; i++) {
            const j = i + inc;
            // inc为1，则子串长度为2，首尾字符相同则子串回文，长度为2
            // inc大于1，则为中间子串的最长回文子序列长度加2
            if (s[i] === s[j])
                dp[i][j] = dp[i + 1][j - 1] + 2;
            else
                // 首尾字符不同，可以选择：
                dp[i][j] = Math.max(
                    dp[i + 1][j], // 舍弃头部字符
                    dp[i][j - 1], // 舍弃尾部字符
                );
        }
    }
    return dp[0][len - 1];
};
