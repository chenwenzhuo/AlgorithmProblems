// 层序遍历二叉树
const levelOrderTraverseTree = root => {
    const queue = [];
    queue.push(root);
    const result = [];
    while (queue.length > 0) {
        const size = queue.length;
        for (let i = 0; i < size; i++) {
            const node = queue.shift();
            result.push(node.val);
            if (node.left)
                queue.push(node.left);
            if (node.right)
                queue.push(node.right);
        }
    }
    return result.join(' _ ');
}

module.exports = {
    levelOrderTraverseTree
}