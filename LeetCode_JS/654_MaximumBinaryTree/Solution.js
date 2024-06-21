/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} nums
 * @return {TreeNode}
 */
var constructMaximumBinaryTree = function (nums) {
    // 从nums的[start,end]下标范围内取值，构造二叉树
    const buildTree = (start, end) => {
        if (start === end) // 区间大小为1，直接构造节点并返回
            return new TreeNode(nums[start]);

        // 寻找区间的最大值及其下标
        let maxVal = -1, maxInd = -1;
        for (let i = start; i <= end; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxInd = i;
            }
        }

        // 构造二叉树
        const root = new TreeNode(maxVal);
        if (start <= maxInd - 1)
            root.left = buildTree(start, maxInd - 1);
        if (end >= maxInd + 1)
            root.right = buildTree(maxInd + 1, end);
        return root;
    }
    return buildTree(0, nums.length - 1);
};