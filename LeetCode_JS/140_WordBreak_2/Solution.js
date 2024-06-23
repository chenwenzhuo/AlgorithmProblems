/**
 * @param {string} s
 * @param {string[]} wordDict
 * @return {string[]}
 */
var wordBreak = function (s, wordDict) {
    const wordSet = new Set(wordDict); // 存储能组成的所有字符串
    const sentences = [];

    const makeSentences = (str, words) => {
        if (wordSet.has(str)) {
            const sentence = [...words, str].join(" ");
            sentences.push(sentence);
        }

        // 遍历wordDict，若s有效，则必然存在一个前缀在wordDict中
        for (let w of wordDict) {
            if (!str.startsWith(w))
                continue;

            words.push(w);
            // 前缀有效时，检查后缀
            const suffix = str.substring(w.length);
            makeSentences(suffix, words); // 递归检查后缀
            words.pop();
        }
    }
    makeSentences(s, []);
    return sentences;
};
