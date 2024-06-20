/**
 * @param {number[]} nums
 * @return {number}
 */
const countBeautifulPairs = function (nums) {
    // 计算最大公约数
    const gcd = (a, b) => {
        if (a > b) // 保证a<b
            return gcd(b, a);
        if (a === 0)
            return b;
        return gcd(b % a, a);
    }

    const n = nums.length;
    let ans = 0;
    const count = new Array(10).fill(0); // 记录元素的第一位数字的出现次数

    // 遍历数组，检查每个元素的最后一位与[1...9]是否互质，成立时ans加上count[i]
    // 遍历过程中count里存储的是之前的元素的第一位数字的出现次数
    nums.forEach(elem => {
        // 计算当前元素的最后一位与之前所有元素的第一位的互质情况
        const lastDigit = elem % 10;
        for (let i = 1; i < 10; i++) {
            if (gcd(i, lastDigit) === 1)
                ans += count[i];
        }

        // 计算当前elem的第一位数字
        let firstDigit = elem;
        while (firstDigit >= 10)
            firstDigit = Math.floor(firstDigit / 10);
        count[firstDigit]++;
    });
    return ans;
};