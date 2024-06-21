/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} inorder
 * @param {number[]} postorder
 * @return {TreeNode}
 */
var buildTree = function (inorder, postorder) {
    const inorderMap = new Map(); // 保存中序序列值和下标的映射
    inorder.forEach((val, ind) => inorderMap.set(val, ind));

    const build = (postSt, postEd, inSt, inEd) => {
        if (postSt > postEd)
            return null;
        // 将后序序列的最后一个元素作为根节点值
        const root = new TreeNode(postorder[postEd]);

        // 根节点值在中序序列的下标
        const index = inorderMap.get(root.val);
        // 左右子树的节点数
        const leftSize = index - inSt, rightSize = inEd - index;
        if (leftSize > 0)
            root.left = build(postSt, postSt + leftSize - 1, inSt, index - 1);
        if (rightSize > 0)
            root.right = build(postEd - rightSize, postEd - 1, index + 1, inEd);
        return root;
    }
    return build(0, postorder.length - 1, 0, inorder.length - 1);
};