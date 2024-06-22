/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number} n
 * @return {TreeNode[]}
 */
var generateTrees = function (n) {
    // 生成节点值在[lower,upper]区间内的BST
    const generate = (lower, upper) => {
        if (lower > upper)
            return null;
        if (lower === upper) {
            const node = new TreeNode(lower);
            return [node];
        }
        // 枚举[lower,upper]区间内的每个值，作为根节点值
        const trees = [];
        for (let val = lower; val <= upper; val++) {
            // 递归生成所有左右子树，[lower,upper]区间不小于2，则二者不同时为空
            const leftTrees = generate(lower, val - 1);
            const rightTrees = generate(val + 1, upper);
            if (!leftTrees) {
                rightTrees.forEach(right => {
                    const root = new TreeNode(val);
                    root.right = right;
                    trees.push(root);
                });
            } else if (!rightTrees) {
                leftTrees.forEach(left => {
                    const root = new TreeNode(val);
                    root.left = left;
                    trees.push(root);
                });
            } else {
                // 左右子树均非空
                for (let i = 0; i < leftTrees.length; i++) {
                    for (let j = 0; j < rightTrees.length; j++) {
                        const root = new TreeNode(val);
                        root.left = leftTrees[i];
                        root.right = rightTrees[j];
                        trees.push(root);
                    }
                }
            }
        }
        return trees;
    }
    return generate(1, n);
};