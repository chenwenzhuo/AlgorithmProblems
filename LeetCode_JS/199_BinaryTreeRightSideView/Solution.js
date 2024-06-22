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
 * @return {number[]}
 */
var rightSideView = function (root) {
    if (!root)
        return [];
    // 层序遍历二叉树，将每层最后一个节点值加入res
    const res = [], queue = [root];
    while (queue.length) {
        const size = queue.length;
        for (let i = 1; i <= size; i++) {
            const node = queue.shift();
            if (i === size)
                res.push(node.val);
            if (node.left)
                queue.push(node.left);
            if (node.right)
                queue.push(node.right);
        }
    }
    return res;
};