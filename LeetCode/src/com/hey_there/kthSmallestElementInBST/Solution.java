package com.hey_there.kthSmallestElementInBST;

import com.hey_there.BinaryTreeInOrderTraversal.TreeNode;

public class Solution {
    private int k;
    private int count = 0;//在递归中表示在当前次递归的tree.val是第几小的值
    private int targetValue;//目标值

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        helper(root);
        return targetValue;
    }

    private void helper(TreeNode tree) {
        if (tree == null || count == k) {
            return;
        }

        helper(tree.left);

        System.out.println("count:   " + count);
        if (++count == k) {
            targetValue = tree.val;//若为第k小，赋值给targetValue
        }

        helper(tree.right);
    }

    public static void main(String[] args) {
        //建树
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(2);

        Solution solution = new Solution();
        System.out.println(solution.kthSmallest(root, 4));
        System.out.println("solution.count:   " + solution.count);
    }
}
