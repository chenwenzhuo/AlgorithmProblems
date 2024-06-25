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
var rob = function (root) {
    const amountMap = new Map(); // 记录子树根节点与可得金额之间的映射
    const doRob = tree => {
        if (!tree) // 从空树中不能获得任何金额
            return 0;
        if (amountMap.has(tree)) // 避免重复计算
            return amountMap.get(tree);

        let amount1 = tree.val; // 偷当前房子
        // 儿子节点不能偷，直接去孙子节点
        if (tree.left) {
            amount1 += doRob(tree.left.left);
            amount1 += doRob(tree.left.right);
        }
        if (tree.right) {
            amount1 += doRob(tree.right.left);
            amount1 += doRob(tree.right.right);
        }

        let amount2 = 0; // 不偷当前房子，则可以去儿子节点
        amount2 += doRob(tree.left);
        amount2 += doRob(tree.right);

        const maxAmount = Math.max(amount1, amount2);
        amountMap.set(tree, maxAmount);
        return maxAmount;
    }
    return doRob(root);
};