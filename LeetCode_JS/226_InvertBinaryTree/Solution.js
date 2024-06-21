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
 * @return {TreeNode}
 */
var invertTree = function (root) {
    if (!root)
        return null;
    const leftChild = root.left, rightChild = root.right;
    root.left = rightChild;
    root.right = leftChild;
    invertTree(root.left);
    invertTree(root.right);
    return root;
};