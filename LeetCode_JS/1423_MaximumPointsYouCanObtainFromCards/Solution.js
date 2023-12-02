/**
 * @param {number[]} cardPoints
 * @param {number} k
 * @return {number}
 */
const maxScore = function (cardPoints, k) {
    const numCards = cardPoints.length;
    // 只能从首尾取，最后一定剩下连续的numCards-k个，剩下的点数最小，则取走的点数最大
    const maxWinSize = numCards - k; // 滑动窗口的最大宽度
    let winSum = 0, // 滑动窗口中元素之和
        sumCards = 0, // cardPoints数组所有元素之和
        minRest = 1000000000; // 剩下的最小点数
    for (let i = 0; i < numCards; i++) {
        sumCards += cardPoints[i];
        if (i < maxWinSize) { // 窗口大小未达到最大值，继续扩大
            winSum += cardPoints[i];
            continue;
        }
        minRest = Math.min(minRest, winSum); // 取最小的窗口和
        // 更新窗口和
        winSum = winSum + cardPoints[i] - cardPoints[i - maxWinSize];
    }
    minRest = Math.min(minRest, winSum); // 循环结束后还需要比较一次
    return sumCards - minRest;
};