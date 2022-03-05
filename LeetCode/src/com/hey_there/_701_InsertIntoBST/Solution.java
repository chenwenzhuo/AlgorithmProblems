package com.hey_there._701_InsertIntoBST;

public class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val, null, null);
        if (val < root.val) root.left = insertIntoBST(root.left, val);
        if (val > root.val) root.right = insertIntoBST(root.right, val);
        return root;
    }
}
