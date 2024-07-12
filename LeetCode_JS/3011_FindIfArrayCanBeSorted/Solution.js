/**
 * @param {number[]} nums
 * @return {boolean}
 */
const canSortArray = function (nums) {
  // 计算参数n的二进制形式中1的数量
  const calcOnes = n => {
    let cnt = 0;
    while (n > 0) {
      if (n % 2 === 1)
        cnt++;
      n >>= 1;
    }
    return cnt;
  }

  const n = nums.length;
  const comparator = (a, b) => a - b;
  // 滑动窗口，窗口中数字的二进制形式具有同样多的1，窗口中的数字都可以交换
  let start = 0, end = 0, prevGroupMax = 0;
  while (start < n) {
    let groupMin = nums[start], groupMax = nums[start]; // 窗口中元素的最小最大值
    const startOnes = calcOnes(nums[start]);
    while (end < n && startOnes === calcOnes(nums[end])) {
      groupMin = Math.min(groupMin, nums[end]);
      groupMax = Math.max(groupMax, nums[end]);
      end++;
    }
    // 窗口中最小值应该比前一个窗口的最大值更大，否则nums不能有序化
    if (groupMin < prevGroupMax)
      return false;
    prevGroupMax = groupMax;
    start = end;
  }
  return true;
};

const nums = [3, 16, 8, 4, 2];
console.log(canSortArray(nums));