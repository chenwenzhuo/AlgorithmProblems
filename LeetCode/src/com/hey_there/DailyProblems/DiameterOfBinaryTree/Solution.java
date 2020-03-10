package com.hey_there.DailyProblems.DiameterOfBinaryTree;

public class Solution {
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        treeDepth(root);
        return diameter;
    }

    private int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //计算左右子树的深度
        int depth_leftSubtree = treeDepth(root.left);
        int depth_rightSubtree = treeDepth(root.right);

        if (depth_leftSubtree + depth_rightSubtree > diameter) {
            diameter = depth_leftSubtree + depth_rightSubtree;
        }
        return Math.max(depth_leftSubtree, depth_rightSubtree) + 1;
    }

    public static void main(String[] args) {
        //构造树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        Solution solution = new Solution();
        System.out.println(solution.diameterOfBinaryTree(root));
    }
}
