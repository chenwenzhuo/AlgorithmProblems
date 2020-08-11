package com.hey_there.InterviewProblem_55.Problem_2_BalancedBinaryTree;

public class Solution {
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;//空树高度为0

        int leftDepth = maxDepth(root.left);//计算左子树高度，并判断左子树是否平衡
        if (leftDepth == -1) return -1;//左子树不平衡，无需向右子树递归
        int rightDepth = maxDepth(root.right);//计算右子树高度，并判断右子树是否平衡
        if (rightDepth == -1) return -1;//右子树不平衡，无需判断当前子树是否平衡

        //判断以root为根节点的子树是否平衡
        boolean isTreeBalanced = (Math.abs(leftDepth - rightDepth) <= 1);
        //返回以root为根节点的树的高度
        return isTreeBalanced ? Math.max(leftDepth, rightDepth) + 1 : -1;
    }
}
