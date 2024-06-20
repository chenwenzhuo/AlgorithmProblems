/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
const longestSubstring = function (s, k) {
    // 寻找s中含有n种字符，且每种字符都出现至少k次的子串
    const fn = n => {
        let ans = 0, left = 0, right = 0;
        // 记录窗口中各字符的出现次数
        const winCharCount = new Array(26).fill(0);
        let winUniqueCount = 0; // 记录窗口中有几种不同的字符
        let winValidCount = 0; // 记录窗口中有几种字符的数量达到k
        // 滑动窗口
        while (right < s.length) {
            const cr = s[right];
            if (winCharCount[cr.charCodeAt() - 97] === 0) // 97是a的ASCII码
                winUniqueCount++; // 窗口中新增一种字符
            winCharCount[cr.charCodeAt() - 97]++;
            if (winCharCount[cr.charCodeAt() - 97] === k)
                winValidCount++; // 窗口中新增一种达标的字符
            right++;

            // 窗口中字符种类大于n时，缩小窗口
            while (winUniqueCount > n) {
                const cl = s[left];
                if (winCharCount[cl.charCodeAt() - 97] === k)
                    winValidCount--; // 减少一种达标的字符
                winCharCount[cl.charCodeAt() - 97]--;
                if (winCharCount[cl.charCodeAt() - 97] === 0)
                    winUniqueCount--; // 窗口中减少一种字符
                left++;
            }

            // 当窗口中字符种类为n，且每个字符出现次数都不低于k，更新ans
            if (winValidCount === n)
                ans = Math.max(ans, right - left);
        }
        return ans;
    }

    // 题目规定s由小写字母构成，遍历所有可能
    let ans = 0;
    for (let i = 1; i <= 26; i++)
        ans = Math.max(ans, fn(i)); // 限制窗口中只能有i种不同字符
    return ans;
};