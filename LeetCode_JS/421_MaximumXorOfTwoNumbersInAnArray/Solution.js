/**
 * @param {number[]} nums
 * @return {number}
 */
let findMaximumXOR = function (nums) {
    // nums数组中最大值的最高二进制位，为2的 highBit 次方
    const highBit = 31 - Math.clz32(Math.max(...nums));
    const set = new Set();
    let ans = 0,
        mask = 0; // mask用于将低于i的所有位置0
    for (let i = highBit; i >= 0; i--) {
        set.clear();
        mask |= (1 << i); // 加上2的i次方
        // 假设当前二进制位为1，则答案会增加2的i次方
        const newAns = ans | (1 << i);

        // 遍历nums，检查能否在保持之前二进制位的情况下使当前位为1
        for (let n of nums) {
            n &= mask; // 将n的低位全部置0，不影响nums数组中原值
            /* 将 newAns 与 n 异或，若结果曾出现过，
             * 则表示可以从nums中选出两个数，异或后高位与 newAns 相同，
             * 此时假设成立，当前二进制位可以为1，更新答案。
             * 异或的性质 x = a ^ b，则 a = x ^ b。*/
            if (set.has(newAns ^ n)) {
                ans = newAns;
                break;
            }
            set.add(n);
        }
    }
    return ans;
};