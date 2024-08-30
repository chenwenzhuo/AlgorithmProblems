/**
 * @param {number[]} nums
 * @return {number}
 */
var sumDigitDifferences = function (nums) {
  const n = nums.length;
  let res = 0n; // BigInt类型
  while (nums[0] > 0) {
    const cnt = new Array(10).fill(0);
    for (let i = 0; i < n; i++) {
      // 每个nums[i]对10取余数，记录当前数位数字的出现次数
      const remainder = nums[i] % 10;
      cnt[remainder]++;
      nums[i] = Math.floor(nums[i] / 10);
    }
    // 计算当前数位的不同次数
    for (let i = 0; i < 10; i++)
      res += BigInt(n - cnt[i]) * BigInt(cnt[i]);
  }
  return Number(res >> 1n); // 右移一位，即除以2
};