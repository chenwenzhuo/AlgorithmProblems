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
 * @return {TreeNode}
 */
const reverseOddLevels = function (root) {
    const cache = []; // 暂存奇数层节点的值
    // 层序遍历二叉树
    const queue = [root];
    let depth = 0;
    while (queue.length > 0) {
        const layerSize = queue.length;
        for (let i = 0; i < layerSize; i++) {
            const node = queue.shift();
            if (depth % 2 === 0) {
                // 偶数层，将下一层的节点值存储到cache中
                if (node.left && node.right) {
                    cache.push(node.left.val);
                    cache.push(node.right.val);
                }
            } else {
                // 奇数层，从cache尾部取值进行赋值
                node.val = cache.pop();
            }
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
        depth++;
    }
    return root;
};

const root = {
    val: 2,
    left: {
        val: 3,
        left: {
            val: 8,
            left: null,
            right: null
        },
        right: {
            val: 13,
            left: null,
            right: null
        }
    },
    right: {
        val: 5,
        left: {
            val: 21,
            left: null,
            right: null
        },
        right: {
            val: 34,
            left: null,
            right: null
        }
    },
};

console.log(reverseOddLevels(root));