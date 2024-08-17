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
var maxSumBST = function (root) {
  let maxSum = 0;
  const traverse = (tree) => {
    if (!tree)
      return null;
    // 遍历左右子树
    const leftInfo = traverse(tree.left),
      rightInfo = traverse(tree.right);

    let treeSum = tree.val;
    if (!leftInfo && !rightInfo) { // 左右子树都为空，则当前节点为叶节点
      maxSum = Math.max(maxSum, treeSum);
      return {
        isBST: true,
        range: [treeSum, treeSum],
        sum: treeSum,
      };
    }

    // 当前子树不是BST
    if ((leftInfo && (!leftInfo.isBST || leftInfo.range[1] >= tree.val)) ||
      (rightInfo && (!rightInfo.isBST || rightInfo.range[0] <= tree.val))) {
      return { isBST: false };
    }

    treeSum += leftInfo ? leftInfo.sum : 0;
    treeSum += rightInfo ? rightInfo.sum : 0;
    maxSum = Math.max(maxSum, treeSum);

    const curRange = [
      leftInfo ? leftInfo.range[0] : tree.val,
      rightInfo ? rightInfo.range[1] : tree.val,
    ];
    return {
      isBST: true,
      range: curRange,
      sum: treeSum,
    }
  }
  traverse(root);
  return maxSum;
};