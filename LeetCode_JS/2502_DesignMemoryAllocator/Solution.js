/**
 * @param {number} n
 */
var Allocator = function (n) {
  this.freeMemoBlocks = [[0, n]]; // 空闲块的起始下标和大小
  this.mID2Blocks = new Map(); // mID -> [[start, size]]
};

/**
 * @param {number} size
 * @param {number} mID
 * @return {number}
 */
Allocator.prototype.allocate = function (size, mID) {
  const { freeMemoBlocks, mID2Blocks } = this;

  let allocateStart = -1;
  for (let i = 0; i < freeMemoBlocks.length; i++) { // 从空闲块中找到第一个合适的块
    const [start, blockSize] = freeMemoBlocks[i];
    if (blockSize >= size) {
      allocateStart = start;
      // 修改剩余空闲块的大小
      if (blockSize > size)
        freeMemoBlocks[i] = [start + size, blockSize - size];
      else
        freeMemoBlocks.splice(i, 1);

      // 记录mID对应的块
      if (!mID2Blocks.has(mID))
        mID2Blocks.set(mID, []);
      mID2Blocks.get(mID).push([allocateStart, size]);
      break;
    }
  }
  return allocateStart;
};

/**
 * @param {number} mID
 * @return {number}
 */
Allocator.prototype.freeMemory = function (mID) {
  const { freeMemoBlocks, mID2Blocks } = this;
  if (!mID2Blocks.has(mID))
    return 0;

  const blocks = mID2Blocks.get(mID);
  blocks.sort((a, b) => a[0] - b[0]); // 按照起始下标排序

  let freeCount = 0;
  for (const block of blocks) {
    const [start, size] = block;
    // 从下标start开始，大小为size的空间被释放
    freeCount += size;

    // 找到插入位置（保持start升序）
    let insertIndex = freeMemoBlocks.findIndex(([s]) => s > start);
    if (insertIndex === -1) insertIndex = freeMemoBlocks.length;

    // 插入新块
    freeMemoBlocks.splice(insertIndex, 0, [start, size]);

    // 合并相邻块逻辑
    let merged = false;

    // 尝试与左边块合并
    if (insertIndex > 0) {
      const left = freeMemoBlocks[insertIndex - 1];
      if (left[0] + left[1] === start) {
        left[1] += size;
        freeMemoBlocks.splice(insertIndex, 1);
        insertIndex--;
        merged = true;
      }
    }

    // 尝试与右边块合并
    if (insertIndex < freeMemoBlocks.length - 1) {
      const right = freeMemoBlocks[insertIndex + 1];
      const current = freeMemoBlocks[insertIndex]; // 必须保留current变量
      if (current[0] + current[1] === right[0]) {
        current[1] += right[1];
        freeMemoBlocks.splice(insertIndex + 1, 1);
        merged = true;
      }
    }

    // 当左右都合并时处理中间块
    if (!merged && insertIndex > 0 && insertIndex < freeMemoBlocks.length) {
      const prev = freeMemoBlocks[insertIndex - 1];
      const current = freeMemoBlocks[insertIndex];
      if (prev[0] + prev[1] === current[0]) {
        prev[1] += current[1];
        freeMemoBlocks.splice(insertIndex, 1);
      }
    }
  }

  mID2Blocks.delete(mID);
  return freeCount;
}

/**
 * Your Allocator object will be instantiated and called as such:
 * var obj = new Allocator(n)
 * var param_1 = obj.allocate(size,mID)
 * var param_2 = obj.freeMemory(mID)
 */

const allocator = new Allocator(10);
console.log(allocator.allocate(1, 1));
console.log(allocator.allocate(1, 2));
console.log(allocator.allocate(1, 3));
console.log(allocator.freeMemory(2));
console.log(allocator.allocate(3, 4));
console.log(allocator.allocate(1, 1));
console.log(allocator.allocate(1, 1));
console.log(allocator.freeMemory(1));
console.log(allocator.allocate(10, 2));
console.log(allocator.freeMemory(7));