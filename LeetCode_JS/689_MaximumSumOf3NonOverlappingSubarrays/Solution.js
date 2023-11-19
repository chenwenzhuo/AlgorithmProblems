/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
const maxSumOfThreeSubarrays = function (nums, k) {
    const length = nums.length;
    // 计算前缀和数组，用于在O(1)时间内得到子数组的和
    const prefixSum = nums.reduce((prev, n) => {
        const prevLen = prev.length;
        prev.push(prev[prev.length - 1] + n);
        return prev;
    }, [0]);

    // 计算闭区间[0,i]内最大的、长度为k的子数组和，并记录起始坐标
    const max_1kSum = new Array(length).fill(0);
    const startIndexes1 = new Array(length).fill(0);
    let one_kSum = 0,
        start1 = 0;
    for (let x = k - 1; x < length; x++) {
        const cur_kSum = prefixSum[x + 1] - prefixSum[x + 1 - k];
        if (cur_kSum > one_kSum) {
            one_kSum = cur_kSum;
            start1 = x + 1 - k;
        }
        max_1kSum[x] = one_kSum;
        startIndexes1[x] = start1;
    }

    // 计算闭区间[0,i]内最大的、两个长度为k的子数组和，并记录两个子数组的起始坐标
    const max_2kSum = new Array(length).fill(0);
    const startIndexes_2 = Array.from(
        new Array(length),
        () => new Array(2).fill(0));
    let two_kSum = 0,
        start2 = [0, 0];
    for (let y = k * 2 - 1; y < length; y++) {
        const cur_2kSum = max_1kSum[y - k] +
            (prefixSum[y + 1] - prefixSum[y + 1 - k]);
        if (cur_2kSum > two_kSum) {
            two_kSum = cur_2kSum;
            start2[0] = startIndexes1[y - k];
            start2[1] = y + 1 - k;
        }
        max_2kSum[y] = two_kSum;
        startIndexes_2[y][0] = start2[0];
        startIndexes_2[y][1] = start2[1];
    }

    // 枚举第三个子数组的终点，加上前两个子数组的和，计算最大的三个子数组和，并记录三个子数组的起始坐标
    let three_kSum = 0,
        start3 = [0, 0, 0];
    for (let z = k * 3 - 1; z < length; z++) {
        const cur_3kSum = max_2kSum[z - k] +
            (prefixSum[z + 1] - prefixSum[z + 1 - k]);
        if (cur_3kSum > three_kSum) {
            three_kSum = cur_3kSum;
            start3[0] = startIndexes_2[z - k][0];
            start3[1] = startIndexes_2[z - k][1];
            start3[2] = z + 1 - k;
        }
    }
    return start3;
};