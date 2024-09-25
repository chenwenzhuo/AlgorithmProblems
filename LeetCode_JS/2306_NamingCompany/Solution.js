/**
 * @param {string[]} ideas
 * @return {number}
 */
var distinctNames = function (ideas) {
  // 将ideas按首字母区分子集
  const namesMap = new Map();
  for (const idea of ideas) {
    const ch = idea[0];
    if (!namesMap.has(ch))
      namesMap.set(ch, new Set());
    namesMap.get(ch).add(idea.substring(1));
  }

  // 计算两个集合a、b的交集大小
  const getIntersectionSize = (setA, setB) => {
    let size = 0;
    for (const elemA of setA) {
      if (setB.has(elemA))
        size++;
    }
    return size;
  }

  let validCnt = 0;
  for (const [preA, setA] of namesMap) {
    for (const [preB, setB] of namesMap) {
      if (preA === preB)
        continue;
      const size = getIntersectionSize(setA, setB);
      // 差集大小的乘积即为两个集合中元素能构成的有效名字数量
      validCnt += (setA.size - size) * (setB.size - size)
    }
  }
  return validCnt;
};