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
 * @param {number[]} queries
 * @return {number[][]}
 */
const closestNodes = function (root, queries) {
    // 中序遍历BST，获得升序序列
    const inorder = [];
    inorderTraversal(root, inorder);

    // 对于每个查询，在数组中二分查找
    const results = [];
    queries.forEach(value => {
        const arr = [];
        const index = binarySearch(inorder, value);
        // 下标有效，则其指向的值为小于等于value的最大值
        if (index >= 0) {
            arr.push(inorder[index]);
            // 大于等于value的最小值为当前下标的值或后一个值
            if (inorder[index] === value)
                arr.push(inorder[index]);
            else if (index + 1 < inorder.length)
                arr.push(inorder[index + 1]);
            else arr.push(-1); // 没有后一个值时为-1
        } else {
            // 下标无效，则所有值都比value大
            arr.push(-1, inorder[0]);
        }
        results.push(arr);
    });
    return results;
};

// 中序遍历获得升序序列
function inorderTraversal(root, inorder) {
    if (!root) return;
    inorderTraversal(root.left, inorder);
    inorder.push(root.val);
    inorderTraversal(root.right, inorder);
}

// 在升序数组中二分查找小于等于target的最大值对应的下标
function binarySearch(arr, target) {
    const len = arr.length;
    let low = 0, high = len - 1, index = -1;
    while (low <= high) {
        const mid = (low + high) >> 1;
        if (arr[mid] > target)
            high = mid - 1;
        else {
            low = mid + 1;
            index = mid;
        }
    }
    return index;
}