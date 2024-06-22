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
 * @return {TreeNode[]}
 */
var findDuplicateSubtrees = function (root) {
    const map = new Map(); // 将子树序列化为字符串作为键，其出现次数作为值
    const res = [];

    const traverse = tree => {
        if (!tree)
            return "null";
        const leftChild = traverse(tree.left);
        const rightChild = traverse(tree.right);

        const treeStructure = `(${leftChild}_${tree.val}_${rightChild})`;
        // 当前子树之前出现过，且只出现过1次，则满足要求，根节点加入res
        if (map.has(treeStructure) && map.get(treeStructure) === 1)
            res.push(tree);

        if (map.has(treeStructure)) // 对重复出现的子树，计数加1
            map.set(treeStructure, map.get(treeStructure) + 1);
        else // 首次出现的子树，记录到map中
            map.set(treeStructure, 1);
        return treeStructure;
    }

    traverse(root);
    return res;
};
