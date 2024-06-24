/**
 * @param {string} word1
 * @param {string} word2
 * @return {number}
 */
var minDistance = function (word1, word2) {
    const m = word1.length, n = word2.length;
    // dp[i][j]表示使word1的前i个字符子串与word2的前j个字符的子串相同的步数
    const dp = new Array(m + 1).fill(0).
        map(() => new Array(n + 1).fill(0));
    // base case：i/j为0，使字符串与空串相同，需要步数为其长度
    for (let i = 1; i <= m; i++)
        dp[i][0] = i;
    for (let j = 1; j <= n; j++)
        dp[0][j] = j;

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            // 当前字符相同，无需操作，步数与前缀所需步数相同
            // 若不同，则至少删除一个
            if (word1[i - 1] === word2[j - 1])
                dp[i][j] = dp[i - 1][j - 1];
            else
                dp[i][j] = Math.min(
                    dp[i - 1][j - 1] + 2, // 两字符串的当前字符都删除
                    dp[i - 1][j] + 1, // 删除word1[i]
                    dp[i][j - 1] + 1, // 删除word2[j]
                );
        }
    }
    return dp[m][n];
};