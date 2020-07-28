package com.hey_there.InterviewProblem_27_MirrorOfBinaryTree;

public class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        //交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //向下递归
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}
