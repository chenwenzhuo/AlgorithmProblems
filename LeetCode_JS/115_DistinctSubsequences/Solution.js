/**
 * @param {string} s
 * @param {string} t
 * @return {number}
 */
var numDistinct = function (s, t) {
    const ls = s.length, lt = t.length;
    // dp[i][j]表示从s[0...i]闭区间中获得t[0...j]子串的方式数
    const dp = new Array(ls).fill(0).
        map(() => new Array(lt).fill(0));
    dp[0][0] = s[0] === t[0] ? 1 : 0;
    for (let i = 1; i < ls; i++) {
        // 计算从s[0...i]中获得t[0]的方式数
        dp[i][0] = dp[i - 1][0] +
            (s[i] === t[0] ? 1 : 0);

        for (let j = 1; j < lt; j++) {
            if (j > i) // 不能从一个较短的字符串中获得更长的
                break;
            // s[i]与t[j]相同，可选择是否让t[j]匹配s[i]
            // 若二者不同，则只能在s[0...i-1]中寻找t[0...j]
            if (s[i] === t[j])
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            else
                dp[i][j] = dp[i - 1][j];
        }
    }
    return dp[ls - 1][lt - 1];
};