package com.hey_there.BalancedBinaryTree;

public class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftChildDepth = searchTreeForDepth(root.left);
        int rightChildDepth = searchTreeForDepth(root.right);


        return (Math.abs(leftChildDepth - rightChildDepth) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }

    private int searchTreeForDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = searchTreeForDepth(root.left);
        int rightDepth = searchTreeForDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
