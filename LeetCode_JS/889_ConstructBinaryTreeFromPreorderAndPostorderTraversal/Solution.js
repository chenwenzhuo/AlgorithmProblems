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
 * @param {number[]} postorder
 * @return {TreeNode}
 */
var constructFromPrePost = function (preorder, postorder) {
    const postorderMap = new Map(); // 存储后序序列值和下标的映射
    postorder.forEach((val, ind) => postorderMap.set(val, ind));

    const buildTree = (preSt, preEd, postSt, postEd) => {
        if (preSt > preEd) // 范围无效，返回空树
            return null;
        if (preSt === preEd) // 范围大小为1，直接构造节点返回
            return new TreeNode(preorder[preSt]);

        // 将前序序列的第一个值作为根节点值
        const root = new TreeNode(preorder[preSt]);

        // 将前序序列的下一个值作为左子树根节点的值，获取其下标
        const index = postorderMap.get(preorder[preSt + 1]);
        // 左右子树的节点数
        const leftSize = index - postSt + 1, rightSize = postEd - index - 1;
        if (leftSize > 0)
            root.left = buildTree(preSt + 1, preSt + leftSize, postSt, postSt + leftSize - 1);
        if (rightSize > 0)
            root.right = buildTree(preEd - rightSize + 1, preEd, postEd - rightSize, postEd - 1);
        return root;
    }
    return buildTree(0, preorder.length - 1, 0, postorder.length - 1);
};