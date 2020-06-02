package com.hey_there.DailyProblems.May.SymmetricTree;

public class Solution {
    private StringBuilder traversalRes_leftFirst;
    private StringBuilder traversalRes_rightFirst;

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        traversalRes_leftFirst = new StringBuilder();
        traversalRes_rightFirst = new StringBuilder();

        treeTraversal_leftFirst(root.left);
        treeTraversal_rightFirst(root.right);

        String res1 = traversalRes_leftFirst.toString();
        String res2 = traversalRes_rightFirst.toString();
        return res1.equals(res2);
    }

    private void treeTraversal_leftFirst(TreeNode tree) {
        if (tree == null) {
            traversalRes_leftFirst.append('n');
            return;
        }
        traversalRes_leftFirst.append(tree.val);
        treeTraversal_leftFirst(tree.left);
        treeTraversal_leftFirst(tree.right);
    }

    private void treeTraversal_rightFirst(TreeNode tree) {
        if (tree == null) {
            traversalRes_rightFirst.append('n');
            return;
        }
        traversalRes_rightFirst.append(tree.val);
        treeTraversal_rightFirst(tree.right);
        treeTraversal_rightFirst(tree.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.right.left = new TreeNode(2);

        Solution solution = new Solution();
        boolean ans = solution.isSymmetric(root);
        System.out.println(ans);
    }
}
