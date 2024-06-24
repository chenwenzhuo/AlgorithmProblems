/**
 * @param {string} word1
 * @param {string} word2
 * @return {number}
 */
var minDistance = function (word1, word2) { // 自底向上，迭代解法
    const m = word1.length, n = word2.length;
    // dp[i][j]表示word1的前i个字符到word2的前j个字符的编辑距离
    const dp = new Array(m + 1).fill(0).
        map(() => Array(n + 1).fill(0));
    // base case：i/j为0，空字符串到另一字符串的编辑距离为另一字符串的长度
    for (let i = 0; i <= m; i++)
        dp[i][0] = i;
    for (let j = 0; j <= n; j++)
        dp[0][j] = j;
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            // 当前的两字符相同，则无需操作，编辑距离等于两前缀间的编辑距离
            // 不同时，可以插入、删除、替换，在三者之间取最小值
            if (word1[i - 1] === word2[j - 1])
                dp[i][j] = dp[i - 1][j - 1];
            else
                dp[i][j] = Math.min(
                    dp[i - 1][j - 1], // 替换word1[i]
                    dp[i][j - 1], // 向word1插入一个字符
                    dp[i - 1][j], // 删除word1[i]
                ) + 1;
        }
    }
    return dp[m][n];
};

// 自顶向下，递归解法
var minDistance = function (word1, word2) {
    const m = word1.length, n = word2.length;
    // dp[i][j]表示word1的前i个字符到word2的前j个字符的编辑距离
    // 初始化为-1，表示还未计算
    const dp = new Array(m + 1).fill(0).
        map(() => Array(n + 1).fill(-1));

    // 计算dp[i][j]
    const calcEditDist = (i, j) => {
        // 其中一个为0，表示空串，空字符串到另一字符串的编辑距离为另一字符串的长度
        if (i === 0) return j;
        if (j === 0) return i;

        // dp[i][j]不为-1，直接返回其值
        if (dp[i][j] !== -1)
            return dp[i][j];
        // 当前的两字符相同，则无需操作，编辑距离等于两前缀间的编辑距离
        if (word1[i - 1] === word2[j - 1])
            dp[i][j] = calcEditDist(i - 1, j - 1);
        else
            dp[i][j] = Math.min(
                calcEditDist(i - 1, j - 1), // 替换word1[i]
                calcEditDist(i, j - 1), // 向word1插入一个字符
                calcEditDist(i - 1, j), // 删除word1[i]
            ) + 1;
        return dp[i][j];
    }
    return calcEditDist(m, n);
}