package com.hey_there.Tree.BinarySearchTreeMaximumPathSum;

public class Solution {
    private int longestPathLen = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        int leftLongest = oneSideLongestPath(root.left);
        int rightLongest = oneSideLongestPath(root.right);

        int fullTreeLongest = leftLongest + rightLongest + root.val;
        leftLongest += root.val;
        rightLongest += root.val;

        return Math.max(root.val,
                Math.max(longestPathLen,
                        Math.max(fullTreeLongest,
                                Math.max(leftLongest, rightLongest))));
    }

    private int oneSideLongestPath(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftLongest = Math.max(0, oneSideLongestPath(node.left));
        int rightLongest = Math.max(0, oneSideLongestPath(node.right));
        longestPathLen = Math.max(longestPathLen, leftLongest + rightLongest + node.val);
        return Math.max(leftLongest, rightLongest) + node.val;
    }
}
