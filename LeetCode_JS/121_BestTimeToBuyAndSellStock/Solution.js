/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    let profit = 0;
    let prevMin = prices[0]; // 遍历数组时，遇到过的最小值
    for (let i = 1; i < prices.length; i++) {
        if (prices[i] > prevMin)
            profit = Math.max(profit, prices[i] - prevMin);
        else
            prevMin = prices[i];
    }
    return profit;
};