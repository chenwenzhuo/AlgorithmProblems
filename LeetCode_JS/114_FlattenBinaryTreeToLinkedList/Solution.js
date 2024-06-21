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
 * @return {void} Do not return anything, modify root in-place instead.
 */
var flatten = function (root) {
    if (!root) // 空树直接返回
        return null;

    // 展开二叉树，返回值[head,tail]表示此树展开后的头/尾节点
    const fn = tree => {
        if (!tree)
            return null;

        // 叶节点，头/尾均为自身
        if (!tree.left && !tree.right)
            return [tree, tree];
        // 展开左右子树
        const flattenLeft = fn(tree.left);
        const flattenRight = fn(tree.right);

        tree.left = null;
        if (flattenLeft && flattenRight) { // 左右子树均非空
            tree.right = flattenLeft[0];
            flattenLeft[1].right = flattenRight[0];
            return [tree, flattenRight[1]];
        }
        if (flattenLeft) { // 左子树非空
            tree.right = flattenLeft[0];
            return [tree, flattenLeft[1]];
        }
        // 右子树非空
        return [tree, flattenRight[1]];
    }

    const flattenRes = fn(root);
    return flattenRes[0];
};

var flatten = function (root) {
    let listTail = { right: null };

    const fn = tree => {
        if (!tree)
            return null;
        // 将左右子树从当前节点取下
        const leftChild = tree.left, rightChild = tree.right;
        tree.left = null;
        tree.right = null;

        // 将当前节点拼接到链表尾部
        listTail.right = tree;
        listTail = listTail.right;

        // 递归处理子树
        fn(leftChild);
        fn(rightChild);
    }
    fn(root);
}

const root = {
    val: 1,
    left: {
        val: 2,
        left: { val: 3, left: null, right: null },
        right: { val: 4, left: null, right: null },
    },
    right: {
        val: 5,
        left: null,
        right: { val: 6, left: null, right: null },
    },
};
const list = flatten(root);
console.log(list);