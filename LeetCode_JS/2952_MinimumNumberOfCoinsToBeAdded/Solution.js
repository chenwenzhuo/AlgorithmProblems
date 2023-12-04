/**
 * @param {number[]} coins
 * @param {number} target
 * @return {number}
 */
const minimumAddedCoins = function (coins, target) {
    coins.sort((a, b) => a - b);
    let rangeRight = 1, // 已得到的区间右边界（开区间），左边界固定为0
        needed = 0, // 需要添加的数量
        i = 0;
    while (rangeRight <= target) {
        if (i < coins.length && coins[i] <= rangeRight) {
            rangeRight += coins[i];
            i++;
        } else {
            rangeRight *= 2;
            needed++;
        }
    }
    return needed;
};

let coins = [1, 4, 10],
    target = 19;
console.log(minimumAddedCoins(coins, target));