/**
 * @param {number[]} start
 * @param {number} d
 * @return {number}
 */
var maxPossibleScore = function (start, d) {
  start.sort((a, b) => a - b);
  const n = start.length;
  // 二分左边界初值0，即一定能选出n个数，两两之差不小于0
  // 选出的n个数一定在区间 [start[0],start[n - 1]+d] 中，当n个数尽可能均匀分布时，差的绝对值最大
  // 所以当选中start[0]、start[n - 1]+d以及其间的n-2个数，并将以上区间均分时，差的绝对值最大
  // 将这个平均区间长度加1，一定大于任意两数的差的绝对值，所以二分右边界以此为初值
  let low = 0, high = Math.floor((start[n - 1] + d - start[0]) / (n - 1)) + 1;
  while (low <= high) {
    const mid = low + ((high - low) >> 1);
    // 检查得分为mid时，能否选出n个数，两两之差不小于mid
    let flag = true;
    let chosen = Number.MIN_SAFE_INTEGER; // 假设第一个区间之前还选了一个负无穷，方便操作
    for (const s of start) {
      // 在前一个区间中选择的数是chosen，由于得分是mid，所以当前应该选择chosen+mid
      // 但chosen+mid可能小于当前区间左端点，所以需要在二者中取较大者
      chosen = Math.max(chosen + mid, s);
      if (chosen > s + d) { // 当前选择的数超过区间右端点，则无法完成选择
        flag = false;
        break;
      }
    }

    if (flag)
      low = mid + 1;
    else high = mid - 1;
  }
  return high;
};
