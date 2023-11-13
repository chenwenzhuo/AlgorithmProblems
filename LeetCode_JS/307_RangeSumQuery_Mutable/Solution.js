/**
 * @param {number[]} nums
 */
let NumArray = function (nums) {
    this.nums = nums;
    // 计算前缀和数组
    this.prefixSum = nums.reduce((arr, cur) => {
        const sumNew = arr[arr.length - 1] + cur;
        arr.push(sumNew);
        return arr;
    }, [0]);
};

/** 
 * @param {number} index 
 * @param {number} val
 * @return {void}
 */
NumArray.prototype.update = function (index, val) {
    const {
        nums,
        prefixSum
    } = this;
    // 更新nums[index]和prefixSum中下标大于等于index+1的元素
    const diff = val - nums[index];
    nums[index] = val;
    for (let i = index + 1; i < prefixSum.length; i++) {
        prefixSum[i] += diff;
    }
};

/** 
 * @param {number} left 
 * @param {number} right
 * @return {number}
 */
NumArray.prototype.sumRange = function (left, right) {
    const {
        prefixSum
    } = this;
    return prefixSum[right + 1] - prefixSum[left]
};

/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = new NumArray(nums)
 * obj.update(index,val)
 * var param_2 = obj.sumRange(left,right)
 */