// 计算所有数值平衡数
const isBalanced = num => {
    const cnt = new Array(10).fill(0);
    while (num > 0) {
        const digit = num % 10;
        cnt[digit]++;
        num = Math.floor(num / 10);
    }
    for (let i = 0; i < 10; i++) {
        if (cnt[i] > 0 && cnt[i] !== i)
            return false;
    }
    return true;
}
const balancedNums = [];
let num = 1;
while (num < 1000000) {
    if (isBalanced(num)) {
        balancedNums.push(num);
    }
    num++;
}
num = 1000001;
while (!isBalanced(num))
    num++;
balancedNums.push(num);

/**
 * @param {number} n
 * @return {number}
 */
const nextBeautifulNumber = function (n) {
    // 在balancedNums中二分查找，寻找比n大的最小平衡数
    let low = 0,
        high = balancedNums.length - 1;
    while (low <= high) {
        const mid = (low + high) >> 1;
        const b = balancedNums[mid];
        if (b <= n)
            low = mid + 1;
        else high = mid - 1;
    }
    return balancedNums[low];
};

console.log(nextBeautifulNumber(486));