// 计算将子串s[start...end]变成回文串需要的最小修改次数
const getCost = (s, start, end) => {
  let ans = 0;
  while (start < end) {
    if (s[start] !== s[end])
      ans++;
    start++;
    end--;
  }
  return ans;
}

/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
var palindromePartition = function (s, k) {
  const len = s.length;
  // memo[i][j]表示将后缀s[i...]分割为j个回文串需要的最小修改次数
  const memo = new Array(len).fill(0)
    .map(() => new Array(k + 1).fill(-1));

  const backtrack = (start, num) => {
    // 长度为1的子串、长度等于num的字串不需要修改
    if (start === len - 1 || (len - start === num))
      return 0;

    if (memo[start][num] >= 0)
      return memo[start][num]; // 已经计算过

    if (num === 1) { // 分割成1个回文串，直接计算次数
      const cost = getCost(s, start, len - 1);
      memo[start][num] = cost;
      return cost;
    }

    let ans = len; // 修改次数不可能超过len
    // 枚举分割点，分割之后的后缀至少需要num-1个字符
    for (let i = start; i < len - num + 1; i++) {
      const prefCost = getCost(s, start, i); // 将s[start...i]变成回文串需要的修改次数
      const sufCost = backtrack(i + 1, num - 1); // 将s[i+1...]分割成num-1个回文串需要的修改次数
      // 取最小值
      ans = Math.min(ans, prefCost + sufCost);
    }
    memo[start][num] = ans;
    // console.log('start---', start, 'num---', num, 'ans---', ans);
    return ans;
  }
  return backtrack(0, k);
};
