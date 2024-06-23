/**
 * @param {string} s
 * @param {string[]} wordDict
 * @return {boolean}
 */
var wordBreak = function (s, wordDict) {
    const validSet = new Set(wordDict); // 存储能组成的所有字符串
    const invalidSet = new Set(); // 存储不能组成的所有字符串
    const isValid = str => {
        if (validSet.has(str))
            return true;
        if (invalidSet.has(str))
            return false;
        if (str.length <= 1)
            return validSet.has(str);

        // 将str分为两段，分别判断
        // 遍历wordDict，若s有效，则必然存在一个前缀在wordDict中
        for (let word of wordDict) {
            if (!str.startsWith(word))
                continue;
            // 前缀有效时，检查后缀
            const suffix = str.substring(word.length);
            if (invalidSet.has(suffix))
                continue; // 当前后缀已经检查过
            const flag = isValid(suffix); // 递归检查后缀
            if (flag) {
                validSet.add(str);
                return true;
            } else
                invalidSet.add(suffix);
        }
        invalidSet.add(str);
        return false;
    }
    return isValid(s);
};

var wordBreak = function (s, wordDict) {
    const wordSet = new Set(wordDict);
    // dp[i]表示前缀s[0...i)能否被拼接出来（左闭右开区间）
    const dp = new Array(s.length + 1).fill(false);
    dp[0] = true; // base case，定义空串可被拼接得到

    // 枚举s的每一个前缀
    for (let i = 1; i <= s.length; i++) {
        for (let j = 0; j < i; j++) {
            if (dp[j] && wordSet.has(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[s.length];
}