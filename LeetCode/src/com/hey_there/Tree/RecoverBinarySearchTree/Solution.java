package com.hey_there.Tree.RecoverBinarySearchTree;

public class Solution {
    private TreeNode prev = null;
    private TreeNode swapNode1 = null;
    private TreeNode swapNode2 = null;

    public void recoverTree(TreeNode root) {
        findSwappedNodes(root);
        int temp = swapNode1.val;
        swapNode1.val = swapNode2.val;
        swapNode2.val = temp;
    }

    private void findSwappedNodes(TreeNode root) {
        if (root == null) {
            return;
        }
        findSwappedNodes(root.left);
        if (prev != null && root.val < prev.val) {
            if (swapNode1 == null) {
                swapNode1 = prev;
            }
            swapNode2 = root;
        }
        prev = root;

        findSwappedNodes(root.right);
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.val);
        inorder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        Solution solution = new Solution();
        solution.inorder(root);
        System.out.println();

        solution.recoverTree(root);

        solution.inorder(root);
        System.out.println();
    }
}
