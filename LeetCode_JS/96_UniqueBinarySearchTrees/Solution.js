/**
 * @param {number} n
 * @return {number}
 */
var numTrees = function (n) {
    const map = {}; // 存储节点数与BST数的映射
    // 计算节点值在区间[lower,upper]中的BST共有几种
    const countBST = (lower, upper) => {
        if (lower > upper) // 空树只有一种
            return 1;
        const cnt = upper - lower + 1;
        if (map[cnt])
            return map[cnt];
        if (cnt === 1) { // 1个节点只有一种树
            map[cnt] = 1;
            return 1;
        }
        // 以区间内每个值作为根节点，计算左右子树的种数
        let total = 0;
        for (let i = lower; i <= upper; i++) {
            const leftCount = countBST(lower, i - 1);
            const rightCount = countBST(i + 1, upper);
            total += (leftCount * rightCount);
        }
        map[cnt] = total;
        return total;
    }
    return countBST(1, n);
};

var numTrees = function (n) {
    // dp[i]表示有i个节点的BST的种数
    const dp = new Array(n + 1).fill(0);
    dp[0] = 1; // 0个节点为空树，空树只有一种

    for (let i = 1; i <= n; i++) { // 一共有i个节点的BST，节点取值范围[1,i]
        // 枚举将j作为根节点的情况，累加BST种数
        // 根节点为j，则左子树有j-1个节点，右子树有i-j个节点
        for (let j = 1; j <= i; j++) {
            dp[i] += (dp[j - 1] * dp[i - j]);
        }
    }
    return dp[n];
}