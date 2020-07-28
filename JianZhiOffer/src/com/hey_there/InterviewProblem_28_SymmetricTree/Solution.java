package com.hey_there.InterviewProblem_28_SymmetricTree;

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root != null && isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode treeA, TreeNode treeB) {
        //二者之一为null，则比较是否同时为null
        if (treeA == null || treeB == null)
            return treeA == treeB;
        //二者均不为null时，比较二者的值并向下递归
        return (treeA.val == treeB.val) &&
                isSymmetric(treeA.left, treeB.right) &&
                isSymmetric(treeA.right, treeB.left);
    }
}
