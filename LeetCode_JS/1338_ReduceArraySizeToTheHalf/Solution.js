/**
 * @param {number[]} arr
 * @return {number}
 */
var minSetSize = function (arr) {
  // 统计每个不同数字的出现次数
  const numsOccurMap = {};
  arr.forEach(num => {
    if (numsOccurMap[num])
      numsOccurMap[num]++;
    else
      numsOccurMap[num] = 1;
  });

  // 转为数组，元素为数字的出现次数
  const numsOccurArr = Object
    .keys(numsOccurMap)
    .map(key => numsOccurMap[key]);
  numsOccurArr.sort((a, b) => b - a); // 按出现次数降序排序

  let cnt = 0, delSize = 0;
  for (const occur of numsOccurArr) {
    delSize += occur;
    cnt++;
    if (delSize >= arr.length / 2)
      return cnt;
  }
  return cnt;
};