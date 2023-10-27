/**
 * @param {number[]} gifts
 * @param {number} k
 * @return {number}
 */
let pickGifts = function (gifts, k) {
    gifts.sort((a, b) => b - a); //将gifts数组降序排序
    //计算gifts数组元素的和
    let total = gifts.reduce((prev, cur) => prev + cur, 0);

    for (let i = 0; i < k; i++) { // 进行k次操作
        const biggest = gifts.shift(); //取最大的一堆
        // 对biggest求平方根，并向下取整
        let squareRoot = Math.floor(Math.sqrt(biggest));
        total -= (biggest - squareRoot); //从总数中减去变化量

        //将这堆剩下的值重新加入数组
        let targetIndex = 0; //squareRoot插入数组时的目标下标
        while (gifts[targetIndex] > squareRoot) {
            targetIndex++;
        }
        gifts.splice(targetIndex, 0, squareRoot);
    }
    return total;
};