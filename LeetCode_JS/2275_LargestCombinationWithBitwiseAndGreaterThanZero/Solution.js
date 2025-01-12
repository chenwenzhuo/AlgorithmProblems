/**
 * @param {number[]} candidates
 * @return {number}
 */
var largestCombination = function (candidates) {
  const n = candidates.length;
  // 10^7二进制形式为24位，存储每一位上1的数量
  const bitwise = new Array(24).fill(0);
  for (const cand of candidates) {
    for (let i = 0; i < 24; i++) {
      if (cand & (1 << i))
        bitwise[i]++;
    }
  }
  return Math.max(...bitwise);
};