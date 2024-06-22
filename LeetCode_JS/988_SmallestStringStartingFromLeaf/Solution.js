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
 * @return {string}
 */
var smallestFromLeaf = function (root) {
    let res = "";
    const path = [];
    const traverse = tree => {
        if (!tree)
            return;
        path.unshift(tree.val);
        // 当前tree为叶节点，将path转为字符串
        if (!tree.left && !tree.right) {
            const pathStr = path.
                map(p => String.fromCharCode('a'.charCodeAt() + p)).
                join("");
            if (res === "")
                res = pathStr;
            else res = res < pathStr ? res : pathStr;
        }
        traverse(tree.left);
        traverse(tree.right);
        path.shift();
    }
    traverse(root);
    return res;
};