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
 * @param {number} key
 * @return {TreeNode}
 */
var deleteNode = function (root, key) {
    if (!root)
        return null;
    // 当前节点值为key，则需要删除当前节点
    if (root.val === key) {
        // 左右子树有一个为空，则返回另一个作为新的根节点
        if (!root.left || !root.right)
            return !root.left ? root.right : root.left;
        // 二者均非空，将左子树连接到右子树的最左边
        let ref = root.right;
        while (ref.left)
            ref = ref.left;
        ref.left = root.left;
        return root.right;
    }
    // 当前节点值不为key，递归左右子树
    root.left = deleteNode(root.left, key);
    root.right = deleteNode(root.right, key);
    return root;
};