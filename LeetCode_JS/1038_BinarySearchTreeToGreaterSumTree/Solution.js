const {
    levelOrderTraverseTree
} = require("../utils/BinaryTree.js");

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
var bstToGst = function (root) {
    // 先右子树后左子树的中序遍历
    const traverseTree = (root, sumGreater) => {
        if (!root)
            return 0;
        const sumRight = traverseTree(root.right, sumGreater);
        // 只有每个子树的最右边节点需要加上sumGreater，其余节点加sumRight即可
        root.val = root.val + (sumRight > 0 ? sumRight : sumGreater);
        const sumLeft = traverseTree(root.left, root.val);
        return root.left ? sumLeft : root.val;
    }
    traverseTree(root, 0);
    return root;
};

var bstToGst = function (root) {
    let greaterSum = 0;
    // 先右子树后左子树的中序遍历
    const traverse = tree => {
        if (!tree)
            return;
        traverse(tree.right);
        tree.val += greaterSum;
        greaterSum = tree.val;
        traverse(tree.left);
    }
    traverse(root);
    return root;
}

let root = {
    val: 4,
    left: {
        val: 1,
        left: {
            val: 0,
        },
        right: {
            val: 2,
            right: {
                val: 3
            }
        }
    },
    right: {
        val: 6,
        left: {
            val: 5
        },
        right: {
            val: 7,
            right: {
                val: 8
            }
        }
    }
}

console.log(levelOrderTraverseTree(bstToGst(root)));