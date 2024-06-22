/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */

/**
 * Encodes a tree to a single string.
 *
 * @param {TreeNode} root
 * @return {string}
 */
var serialize = function (root) {
    if (!root)
        return "";
    // 前序遍历二叉树
    const preorderRes = [];
    const traverse = tree => {
        if (!tree) {
            preorderRes.push("#");
            return;
        }
        preorderRes.push(tree.val);
        traverse(tree.left);
        traverse(tree.right);
    }
    traverse(root);
    return preorderRes.join(",");
};

/**
 * Decodes your encoded data to tree.
 *
 * @param {string} data
 * @return {TreeNode}
 */
var deserialize = function (data) {
    if (data === "")
        return null;
    // 将data分割为数组，并转为数字
    const preorder = data.split(",").map(item => {
        if (item === "#")
            return item;
        return Number(item);
    });

    // 遍历preorder数组，构造二叉树
    let index = 0;
    const buildTree = () => {
        if (index >= preorder.length)
            return;
        if (preorder[index] === "#") {
            index++;
            return null; // "#"代表空树
        }
        const node = new TreeNode(preorder[index]);
        index++;
        node.left = buildTree();
        node.right = buildTree();
        return node;
    }
    return buildTree();
};

/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
*/

function TreeNode(val) {
    this.val = val;
    this.left = this.right = null;
}
