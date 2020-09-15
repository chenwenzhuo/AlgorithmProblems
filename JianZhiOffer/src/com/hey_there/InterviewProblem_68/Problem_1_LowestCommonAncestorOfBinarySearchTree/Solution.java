package com.hey_there.InterviewProblem_68.Problem_1_LowestCommonAncestorOfBinarySearchTree;

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //三者之中任意一个为null，则返回null
        if (root == null || p == null || q == null) return null;

        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        else if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        else return root;
    }
}
