/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
var pseudoPalindromicPaths = function (root) {
    let count = 0;
    // 节点值在区间[1,9]中，用数组记录路径上不同节点值出现次数
    const pathValuesCnt = new Array(10).fill(0);
    const traverse = tree => {
        if (!tree)
            return;
        pathValuesCnt[tree.val]++; // 节点值计数

        // 叶节点，检查路径上节点值是否满足要求
        if (!tree.left && !tree.right) {
            // 统计路径上出现奇数次的值的数量
            let oddCnt = 0;
            for (let value of pathValuesCnt) {
                if (value % 2 === 1)
                    oddCnt++;
            }
            // 路径上出现奇数次的值最多一个，否则不能排列成回文串
            if (oddCnt <= 1)
                count++;
        }
        traverse(tree.left);
        traverse(tree.right);
        // 返回上一层前，将此节点值的计数减去
        pathValuesCnt[tree.val]--;
    }
    traverse(root);
    return count;
};