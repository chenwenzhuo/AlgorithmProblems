/**
 * @param {string} answerKey
 * @param {number} k
 * @return {number}
 */
var maxConsecutiveAnswers = function (answerKey, k) {
  const n = answerKey.length;
  let longest = 1;
  // 计算k次操作之内，能获得的最长连续字符的数量
  const getLongest = targetChar => {
    let lo = 0, hi = 0; // 滑动窗口边界
    let otherCharCnt = 0; // 窗口中非目标字符的数量
    while (hi < n) {
      const ch_hi = answerKey[hi];
      // 目标字符，直接加入窗口
      // 非目标字符，其数量不能超过k
      if (ch_hi === targetChar || otherCharCnt < k) {
        if (ch_hi !== targetChar)
          otherCharCnt++;
        hi++;
        continue;
      }

      // 非目标字符，且窗口中其数量已达到k，尝试更新长度
      longest = Math.max(longest, hi - lo);
      // 缩小窗口，直到otherCharCnt小于k
      while (lo <= hi && otherCharCnt >= k) {
        const ch_lo = answerKey[lo];
        if (ch_lo !== targetChar)
          otherCharCnt--;
        lo++;
      }
    }
    longest = Math.max(longest, hi - lo);
  }
  getLongest('T');
  getLongest('F');
  return longest;
};