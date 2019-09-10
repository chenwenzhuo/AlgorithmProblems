package com.hey_there.PathSumEqualsTargetSum;

import com.hey_there.BinaryTreeInOrderTraversal.TreeNode;

public class Solution {
    private int targetSum;

    public boolean hasPathSum(TreeNode root, int sum) {
        this.targetSum = sum;
        return helper(root, 0);
    }

    private boolean helper(TreeNode node, int prevSum) {
        if (node == null) {
            return false;
        }
        int currentSum = prevSum + node.val;//路径前面的结点之和加上当前结点的值

        if (currentSum == targetSum) {
            if (node.left == null && node.right == null) {
                return true;
            }
        }

        return helper(node.left, currentSum) || helper(node.right, currentSum);
    }

    public static void main(String[] args) {
        //建树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(-2);
        root.left.left.left = new TreeNode(-1);

        Solution solution = new Solution();
        System.out.println(solution.hasPathSum(root, -1));
    }
}
