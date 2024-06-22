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
var widthOfBinaryTree = function (root) {
    let maxWidth = 1; // 树非空，宽度至少为1
    // 对节点编号，子树根节点为index时，左子节点为2*index，右子节点为2*index+1
    // 部分测试用例，树的规模很大，index需要使用BigInt
    let level = [[root, 1n]]; // root节点的编号初始化为1
    while (level.length) {
        // 遍历当前层，将下一层的节点数据加入nextLevel
        const nextLevel = [];
        for (const pair of level) {
            const [node, index] = pair; // 当前节点的引用和编号
            if (node.left)
                nextLevel.push([node.left, index * 2n]);
            if (node.right)
                nextLevel.push([node.right, index * 2n + 1n]);
        }
        // 用当前层最左、最右节点的编号，更新宽度
        const curWidth = Number(level[level.length - 1][1] - level[0][1] + 1n);
        maxWidth = Math.max(maxWidth, curWidth);
        level = nextLevel;
    }
    return maxWidth;
};