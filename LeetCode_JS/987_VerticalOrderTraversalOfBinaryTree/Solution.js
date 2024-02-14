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
 * @return {number[][]}
 */
const verticalTraversal = function (root) {
    const mem = [];
    // 先序遍历二叉树，记录节点坐标和节点值
    const traversal = (root, x, y) => {
        if (!root)
            return;
        mem.push([x, y, root.val]);
        traversal(root.left, x + 1, y - 1);
        traversal(root.right, x + 1, y + 1);
    }
    traversal(root, 0, 0);
    // 对mem进行排序
    mem.sort((item1, item2) => {
        if (item1[1] !== item2[1]) // 先按横坐标排序
            return item1[1] - item2[1];
        if (item1[0] !== item2[0]) // 横坐标相同，按纵坐标排序
            return item1[0] - item2[0];
        // 坐标都相同，按节点值排序
        return item1[2] - item2[2];
    });
    const result = [[mem[0][2]]];
    for (let i = 1; i < mem.length; i++) {
        if (mem[i][1] === mem[i - 1][1])
            result[result.length - 1].push(mem[i][2]);
        else result.push([mem[i][2]]);
    }
    return result;
};