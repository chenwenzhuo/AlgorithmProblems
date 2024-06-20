/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
const characterReplacement = function (s, k) {
    const len = s.length;
    let winCharCount = new Array(26).fill(0); // 记录窗口中每个字符的出现次数
    let winCharMost = 0; // 窗口中最多的字符的出现次数
    // 将窗口中其他字符替换为出现最多的字符，最节省替换次数，能获得更多的重复字符

    let res = 0, left = 0, right = 0;
    while (right < len) {
        // 扩大窗口
        let ind = s[right].charCodeAt() - 65; // 65是A的ASCII码
        winCharCount[ind]++;
        winCharMost = Math.max(winCharMost, winCharCount[ind]);
        right++;

        // 窗口长度为right-left，再减去winCharMost，即为将窗口中全替换为相同字符所需的操作次数
        // 所需次数大于k时，缩小窗口
        while (right - left - winCharMost > k) {
            // 缩小窗口
            ind = s[left].charCodeAt() - 65;
            winCharCount[ind]--;
            left++;
            // 这里不用更新 winCharMost
            // 因为只有 winCharMost 变得更大的时候才可能获得更长的重复子串，才会更新res
        }
        // 此时一定是一个合法的窗口
        res = Math.max(res, right - left);
    }
    return res;
};