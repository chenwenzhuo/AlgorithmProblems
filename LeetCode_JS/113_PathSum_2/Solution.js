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
 * @param {number} targetSum
 * @return {number[][]}
 */
const pathSum = function (root, targetSum) {
    const res = [];
    const fn = (node, target, path) => {
        if (!node) // 空树直接返回
            return;
        path.push(node.val); // 当前节点加入路径
        // 叶节点，节点值等于targetSum时，路径有效
        if (!node.left && !node.right && node.val === target)
            res.push([...path]);
        fn(node.left, target - node.val, path);
        fn(node.right, target - node.val, path);
        path.pop();
    }
    fn(root, targetSum, []);
    return res;
};