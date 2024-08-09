/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var minimumAddedInteger = function (nums1, nums2) {
  const l1 = nums1.length, l2 = nums2.length;
  // 寻找两数组的最大值，并统计nums1中每个数的出现次数
  let max1 = 0, max2 = 0;
  const map1 = new Map();
  nums1.forEach(n => {
    max1 = Math.max(max1, n);
    if (map1.has(n))
      map1.set(n, map1.get(n) + 1);
    else
      map1.set(n, 1);
  });
  nums2.forEach(n => max2 = Math.max(max2, n));

  let diff = max1 - max2;
  while (true) {
    // 将nums2中每个元素都加上diff，若所得的每个和都存在于nums1中
    // 则当前diff为满足条件的最大值，对应最小的x
    const copyMap = new Map(map1);
    let flag = true;
    for (let i = 0; i < l2; i++) {
      const elemSum = nums2[i] + diff;
      // 某个元素加上diff的和不存在于nums1中，则当前diff不可取
      if (!copyMap.has(elemSum)) {
        flag = false;
        break;
      }
      // 当前elemSum存在于nums1中，则将其计数减1，减到0则移除
      copyMap.set(elemSum, copyMap.get(elemSum) - 1);
      if (copyMap.get(elemSum) === 0)
        copyMap.delete(elemSum);
    }
    // flag为true，则当前diff可取，否则减小diff继续尝试
    if (flag)
      break;
    diff--;
  }
  return -diff;
};

/**
 * nums1去掉两个元素后，将其余元素加上x，得到nums2
 * 则nums2每个元素加上-x也能得到nums1中元素
 * 求最小x，等价于求最大的-x
 * -x的可能的最大值为 max(nums1)-max(nums2)
 */