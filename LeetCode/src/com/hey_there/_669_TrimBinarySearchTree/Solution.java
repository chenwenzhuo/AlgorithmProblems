package com.hey_there._669_TrimBinarySearchTree;

public class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null)
            return null;
        //递归修剪左右子树
        TreeNode trimLeft = trimBST(root.left, low, high);
        TreeNode trimRight = trimBST(root.right, low, high);
        //root.val在闭区间[low,high]中，则保留根节点root
        if (low <= root.val && root.val <= high) {
            root.left = trimLeft;
            root.right = trimRight;
            return root;
        }
        //root.val不在闭区间[low,high]中，则左右子树至少一个为null
        //返回其中非null的一个，若都为null则返回null
        if (trimLeft != null)
            return trimLeft;
        return trimRight;
    }
}
