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
 * @param {number} k
 * @return {number}
 */
const kthLargestLevelSum = function (root, k) {
    // 队列辅助层序遍历
    const queue = [], layerSums = [];
    queue.push(root);
    while (queue.length > 0) {
        let sum = 0;
        const layerSize = queue.length;
        for (let i = 0; i < layerSize; i++) {
            const node = queue.shift();
            sum += node.val;
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
        layerSums.push(sum);
    }
    if (layerSums.length < k)
        return -1;
    layerSums.sort((a, b) => b - a);
    return layerSums[k - 1];
};