/**
 * @param {string[]} words
 * @return {number}
 */
let maxProduct = function (words) {
    const numWords = words.length;
    // 用数字的最低0～25位表示某字符是否在字符串中出现
    const masks = [];
    for (const word of words) {
        let msk = 0;
        for (let i = 0; i < word.length; i++) {
            msk |= 1 << (word[i].charCodeAt() - 'a'.charCodeAt());
        }
        masks.push(msk);
    }
    // 对于每一对字符串组合，检查是否有公共字符，没有时更新答案
    let ans = 0;
    for (let i = 0; i < numWords; i++) {
        for (let j = i + 1; j < numWords; j++) {
            // 按位与的结果为0时，没有公共字符
            if ((masks[i] & masks[j]) === 0) {
                ans = Math.max(ans, words[i].length * words[j].length);
            }
        }
    }
    return ans;
};