/**
 * // Definition for a _Node.
 * function _Node(val, left, right, next) {
 *    this.val = val === undefined ? null : val;
 *    this.left = left === undefined ? null : left;
 *    this.right = right === undefined ? null : right;
 *    this.next = next === undefined ? null : next;
 * };
 */

/**
 * @param {_Node} root
 * @return {_Node}
 */
var connect = function (root) {
    if (!root)
        return null;
    const queue = [root]; // 队列辅助层序遍历
    while (queue.length) {
        // 获取队列大小，size个节点都在同一层
        const size = queue.length;
        for (let i = 1; i <= size; i++) {
            const node = queue.shift();
            // 本层的最后一个节点，next指向null
            if (i === size) {
                node.next = null;
            } else {  // 不是最后一个，则next指向队首节点
                node.next = queue[0];
            }

            // 子节点入队
            if (node.left)
                queue.push(node.left);
            if (node.right)
                queue.push(node.right);
        }
    }
    return root;
};

var connect = function (root) {
    if (!root)
        return null;

    const traverse = (node1, node2) => {
        if (!node1 || !node2)
            return;
        node1.next = node2; // 连接传入的两个节点

        // 连接相同父节点的两个子节点
        traverse(node1.left, node1.right);
        traverse(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        traverse(node1.right, node2.left);
    }

    traverse(root.left, root.right);
    return root;
}

const root = {
    val: 1,
    left: {
        val: 2,
        left: { val: 4, left: null, right: null, next: null, },
        right: { val: 5, left: null, right: null, next: null, },
        next: null,
    },
    right: {
        val: 3,
        left: { val: 6, left: null, right: null, next: null, },
        right: { val: 7, left: null, right: null, next: null, },
        next: null,
    },
    next: null,
};

connect(root);