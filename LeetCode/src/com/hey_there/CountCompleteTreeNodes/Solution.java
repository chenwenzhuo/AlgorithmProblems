package com.hey_there.CountCompleteTreeNodes;

public class Solution {
    private int nodeCount = 0;//结点数量

    /**
     * 遍历整棵树的所有结点，计算结点数量
     *
     * @param root 完全二叉树根结点引用
     * @return 完全二叉树中的结点数量
     */
    public int countNodes_searchFullTree(TreeNode root) {
        helper(root);
        return nodeCount;
    }

    private void helper(TreeNode tree) {
        if (tree == null) {
            return;
        }
        nodeCount++;
        helper(tree.left);
        helper(tree.right);
    }

    /**
     * 基于完全二叉树左右子树深度的解法
     *
     * @param root 完全二叉树根结点引用
     * @return 完全二叉树中的结点数量
     */
    public int countNodes_basedOnTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;//若为空树，结点数为0
        }
        //分别计算左右子树深度
        int leftDepth = calcDepth(root.left);
        int rightDepth = calcDepth(root.right);

        //算法详细描述，见AlgorithmDescription.jpg
        if (leftDepth == rightDepth) {
            /*int leftChildTreeAndRootNodes = (int) Math.pow(2, leftDepth);
            return countNodes_basedOnTreeDepth(root.right) + leftChildTreeAndRootNodes;*/
            //用移位代替pow会更快
            return countNodes_basedOnTreeDepth(root.right) + (1 << leftDepth);
        } else {
            /*int rightChildTreeAndRootNodes = (int) Math.pow(2, rightDepth);
            return countNodes_basedOnTreeDepth(root.left) + rightChildTreeAndRootNodes;*/
            //用移位代替pow会更快
            return countNodes_basedOnTreeDepth(root.left) + (1 << rightDepth);
        }
    }

    //由于是完全二叉树，故可以用最左侧路径的长度代表树的深度
    private int calcDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            depth++;
            root = root.left;
        }
        return depth;
    }
}
