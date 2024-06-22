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
var sumRootToLeaf = function (root) {
    let sum = 0, pathNum = 0;
    const traverse = tree => {
        if (!tree)
            return;
        pathNum = pathNum * 2 + tree.val;
        if (!tree.left && !tree.right)
            sum += pathNum;
        traverse(tree.left);
        traverse(tree.right);
        pathNum = (pathNum - tree.val) / 2;
    }
    traverse(root);
    return sum;
};