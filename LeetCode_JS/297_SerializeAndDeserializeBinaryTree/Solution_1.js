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
    // 层序遍历二叉树
    const queue = [root], levelOrder = [];
    while (queue.length) {
        const node = queue.shift(); // 取队列头部的节点
        // 当前节点为null时无需向队列中添加子节点，只需向遍历结果中加入一个null
        if (node === null) {
            levelOrder.push('null'); // 必须使用字符串'null'，否则会在join时被忽略
            continue;
        }
        // 访问当前节点，记录遍历结果，并将子节点入队
        levelOrder.push(node.val);
        queue.push(node.left);
        queue.push(node.right);
    }

    // 去掉尾部连续的null
    while (levelOrder[levelOrder.length - 1] === 'null')
        levelOrder.pop();
    return levelOrder.join(",");
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
    const levelOrder = data.split(",").map(item => {
        if (item === "null")
            return null;
        return Number(item);
    });

    // 单独创建根节点
    const root = new TreeNode(levelOrder[0]);
    const queue = [root];
    let ind = 1;
    while (ind < levelOrder.length) {
        const parent = queue.shift(); // 父节点
        if (levelOrder[ind] !== null) {
            parent.left = new TreeNode(levelOrder[ind]);
            queue.push(parent.left);
        }
        ind++;
        if (ind < levelOrder.length && levelOrder[ind] !== null) {
            parent.right = new TreeNode(levelOrder[ind]);
            queue.push(parent.right);
        }
        ind++;
    }
    return root;
};

/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
*/

function TreeNode(val) {
    this.val = val;
    this.left = this.right = null;
}
