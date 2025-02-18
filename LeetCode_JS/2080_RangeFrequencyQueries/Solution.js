/**
 * @param {number[]} arr
 */
var RangeFreqQuery = function (arr) {
  // 统计arr中每个不同值的所有下标
  const map = new Map();
  for (let i = 0; i < arr.length; i++) {
    if (!map.has(arr[i]))
      map.set(arr[i], []);
    map.get(arr[i]).push(i);

    this.val2Ind = map;
  }
};

/** 
 * @param {number} left 
 * @param {number} right 
 * @param {number} value
 * @return {number}
 */
RangeFreqQuery.prototype.query = function (left, right, value) {
  const { val2Ind } = this;
  const indArr = val2Ind.get(value);
  if (!indArr)
    return 0;
  // 计算在indArr中满足 left<=indArr[i]<=right 的元素个数
  let resLow, resHigh;
  // 二分查找大于等于left的最小indArr[i]
  let lo = 0, hi = indArr.length - 1;
  while (lo < hi) {
    const mid = (lo + hi) >> 1;
    if (indArr[mid] < left)
      lo = mid + 1;
    else
      hi = mid;
  }
  resLow = lo;

  // 二分查找小于等于right的最大indArr[i]
  lo = 0;
  hi = indArr.length - 1;
  while (lo < hi) {
    const mid = (lo + hi + 1) >> 1;
    if (indArr[mid] > right)
      hi = mid - 1;
    else
      lo = mid;
  }
  resHigh = lo;

  if (indArr[resLow] >= left && indArr[resHigh] <= right)
    return resHigh - resLow + 1;
  return 0;
};

/** 
 * Your RangeFreqQuery object will be instantiated and called as such:
 * var obj = new RangeFreqQuery(arr)
 * var param_1 = obj.query(left,right,value)
 */

const obj = new RangeFreqQuery([1, 1, 1, 2, 2]);
console.log(obj.query(0, 1, 2));
console.log(obj.query(0, 2, 1));