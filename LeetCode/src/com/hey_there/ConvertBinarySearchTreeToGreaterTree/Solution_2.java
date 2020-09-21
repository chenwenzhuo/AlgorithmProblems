package com.hey_there.ConvertBinarySearchTreeToGreaterTree;

class Solution_2 {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}
