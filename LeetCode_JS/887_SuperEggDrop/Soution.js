/**
 * @param {number} k
 * @param {number} n
 * @return {number}
 */
var superEggDrop = function (k, n) {
    // dp[i][j]表示使用i个鸡蛋，对j层楼进行测试，最坏情况下的操作次数
    const dp = new Array(k + 1).fill(0).
        map(() => new Array(n + 1).fill(-1));
    // 递归计算dp[i][j]
    const calcOperations = (eggs, floors) => {
        // base case
        if (eggs === 1) return floors; // 只有一个鸡蛋，只能线性扫描，最多floors次
        if (floors === 0) return 0; // 没有楼层，则无需操作

        if (dp[eggs][floors] !== -1) // 检查备忘录避免重复计算
            return dp[eggs][floors];

        let res = Infinity;
        // 在所有楼层进行尝试，取最少操作次数，复杂度过高
        /* for (let i = 1; i <= floors; i++) {
            res = Math.min(res,
                // 碎和没碎取最坏情况
                Math.max(
                    calcOperations(eggs - 1, i - 1), // 碎了，在更低的楼层查找
                    calcOperations(eggs, floors - i) // 没碎，向更高的楼层查找
                ) + 1 // 加上当前楼层操作的一次
            );
        } */

        // 二分搜索
        // 将eggs和floors看做常量，calcOperations(eggs-1,i-1)和calcOperations(eggs,floors-i)
        // 分别是关于 i 的单增、单减函数，对二者求最大值，再在最大值中求最小值，就是求两个函数的交点
        let low = 1, high = floors;
        while (low <= high) {
            const mid = (low + high) >> 1;
            // 在mid层有碎和没碎两种情况
            const broken = calcOperations(eggs - 1, mid - 1),
                not_broken = calcOperations(eggs, floors - mid);
            // res = min(max(碎，没碎) + 1)
            if (broken > not_broken) {
                high = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                low = mid + 1;
                res = Math.min(res, not_broken + 1);
            }
        }

        dp[eggs][floors] = res;
        return res;
    }
    return calcOperations(k, n);
};