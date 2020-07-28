package com.hey_there.InterviewProblem_26_SubStructureOfTree;

public class Solution_2 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //起始时A和B均不能为null
        if (A == null || B == null) return false;
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    public static void main(String[] args) {
        TreeNode A = new TreeNode(10);
        A.left = new TreeNode(12);
        A.right = new TreeNode(6);
        A.left.left = new TreeNode(8);
        A.left.right = new TreeNode(3);
        A.right.left = new TreeNode(11);
        TreeNode B = new TreeNode(10);
        B.left = new TreeNode(12);
        B.right = new TreeNode(6);
        B.left.left = new TreeNode(8);

        Solution_2 solution = new Solution_2();
        boolean res = solution.isSubStructure(A, B);
        System.out.println(res);
    }
}
