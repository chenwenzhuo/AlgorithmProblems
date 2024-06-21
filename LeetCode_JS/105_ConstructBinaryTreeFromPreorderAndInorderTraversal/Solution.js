/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} preorder
 * @param {number[]} inorder
 * @return {TreeNode}
 */
var buildTree = function (preorder, inorder) {
    const inorderMap = new Map(); // 保存中序序列值和下标的映射
    inorder.forEach((val, ind) => inorderMap.set(val, ind));

    const build = (preSt, preEd, inSt, inEd) => {
        if (preSt > preEd)
            return null;
        // 将前序序列的第一个元素作为根节点值
        const root = new TreeNode(preorder[preSt]);

        // 根节点值在中序序列的下标
        const index = inorderMap.get(root.val);
        // 左右子树节点数
        const leftSize = index - inSt, rightSize = inEd - index;
        if (leftSize > 0)
            root.left = build(preSt + 1, preSt + leftSize, inSt, index - 1);
        if (rightSize > 0)
            root.right = build(preEd - rightSize + 1, preEd, index + 1, inEd);
        return root;
    }

    return build(0, preorder.length - 1, 0, inorder.length - 1);
};