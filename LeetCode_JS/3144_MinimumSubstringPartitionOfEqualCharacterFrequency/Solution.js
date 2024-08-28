/**
 * @param {string} s
 * @return {number}
 */
var minimumSubstringsInPartition = function (s) {
  const numBalancedSubs = {}; // 记录每个子串能分割成的平衡子串数量
  // 计算字符串str最少能分割成多少个平衡子字符串
  const fn = str => {
    if (str.length === 1)
      numBalancedSubs[str] = 1;
    if (numBalancedSubs[str]) // 避免重复计算
      return numBalancedSubs[str];

    const l_str = str.length;
    const charsMap = new Map(); // 记录子串中不同字符的出现次数
    let maxMapVal = 0; // charsMap中的最大值
    let minBalancedSubs = str.length;
    for (let i = 0; i < l_str; i++) {
      const ch = str[i];
      if (charsMap.has(ch))
        charsMap.set(ch, charsMap.get(ch) + 1);
      else
        charsMap.set(ch, 1);
      maxMapVal = Math.max(maxMapVal, charsMap.get(ch));

      // 判断下标[0...i]范围内的子串是否平衡
      if (maxMapVal * charsMap.size === i + 1) {
        // 平衡，则尝试将后续子串拆分
        const suffix = str.substring(i + 1);
        minBalancedSubs = Math.min(minBalancedSubs, fn(suffix) + 1);
      }
    }
    numBalancedSubs[str] = minBalancedSubs; // 记录当前str的最少分割数量
    return minBalancedSubs;
  }
  return fn(s);
};

var minimumSubstringsInPartition = function (s) {
  const ls = s.length;
  // dp[i]表示子串s[0...i)最少能分割成的平衡子串数量
  const dp = new Array(ls + 1).fill(0);
  dp[1] = 1; // base case，长度为1的字符串，只有一种分割方式
  for (let i = 2; i <= ls; i++) {
    dp[i] = i; // 将前缀分割成长度全为1的子串，则所有子串都一定平衡

    // 检查是否存在下标j，满足0<=j<i，使子串s[j...i)平衡
    const charsMap = new Map(); // 记录子串中不同字符的出现次数
    let maxMapVal = 0; // charsMap中的最大值
    for (let j = i - 1; j >= 0; j--) {
      // 将字符s[j]加入charsMap
      const ch = s[j];
      if (charsMap.has(ch))
        charsMap.set(ch, charsMap.get(ch) + 1);
      else
        charsMap.set(ch, 1);
      maxMapVal = Math.max(maxMapVal, charsMap.get(ch));
      if (maxMapVal * charsMap.size === i - j) {
        // 子串s[j...i)平衡，尝试更新dp[i]
        dp[i] = Math.min(dp[i], dp[j] + 1);
      }
    }
  }
  return dp[ls];
}