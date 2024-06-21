/**
 * @param {string} s
 * @return {string}
 */
const removeDuplicateLetters = function (s) {
    const n = s.length;
    const sChars = s.split("");
    // 遍历s，统计每种字符的出现次数
    const map = {};
    sChars.forEach(ch => {
        if (map[ch])
            map[ch]++;
        else
            map[ch] = 1;
    });

    const stack = []; // 栈用于辅助去重
    const inStackFlag = new Array(26).fill(false); // 标记栈中是否存在各个字符
    let ind = 0;
    while (ind < n) {
        const curChar = sChars[ind];
        const flagInd = curChar.charCodeAt() - "a".charCodeAt();
        if (inStackFlag[flagInd]) { // 栈中已有相同字符，跳过
            map[curChar]--;
            ind++;
            continue;
        }

        // 当栈顶元素比 curChar 的字典序靠后，且栈顶元素在未遍历部分还有相同的，将其出栈
        while (stack.length > 0 && map[stack[stack.length - 1]] > 0 &&
            stack[stack.length - 1].charCodeAt() > curChar.charCodeAt()
        ) {
            const top = stack.pop();
            inStackFlag[top.charCodeAt() - "a".charCodeAt()] = false;
        }

        // 将当前元素入栈
        stack.push(curChar);
        inStackFlag[flagInd] = true;
        map[curChar]--;
        ind++;
    }
    return stack.join("");
};

console.log(removeDuplicateLetters("bbcaac"));