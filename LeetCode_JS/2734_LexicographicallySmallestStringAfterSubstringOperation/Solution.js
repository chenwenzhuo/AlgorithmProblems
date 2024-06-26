/**
 * @param {string} s
 * @return {string}
 */
var smallestString = function (s) {
    const n = s.length;
    // 选择下标尽可能小，且不包含字符a的子串
    let start = 0;
    while (start < n && s[start] === 'a')
        start++; // 寻找下标最小的非a字符

    if (start >= n) // start>=n，则字符串全是a，将最后一个字符替换为z
        return s.substring(0, n - 1) + 'z';

    let end = start + 1;
    while (end < n && s[end] !== 'a')
        end++; // 寻找字符a作为子串的结束点

    const prefix = s.substring(0, start),
        suffix = s.substring(end);
    let operated = '';
    // 对s[start,end)区间内的子串进行操作
    for (let i = start; i < end; i++) {
        const nextChar = String.fromCharCode(s[i].charCodeAt() - 1);
        operated += nextChar;
    }
    return prefix + operated + suffix;
};