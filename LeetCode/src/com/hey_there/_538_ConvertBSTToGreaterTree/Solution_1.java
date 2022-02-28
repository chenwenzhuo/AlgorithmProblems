package com.hey_there._538_ConvertBSTToGreaterTree;

import java.util.ArrayList;

public class Solution_1 {
    private int idx = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;

        ArrayList<Integer> inorder = new ArrayList<>();
        getInorderTraversalRes(root, inorder);

        int numNodes = inorder.size();//树中节点数量
        int[] suffixSum = new int[numNodes];//各个节点值的后缀和
        suffixSum[numNodes - 1] = inorder.get(numNodes - 1);
        //计算后缀和
        for (int i = numNodes - 2; i >= 0; i--) {
            suffixSum[i] = inorder.get(i) + suffixSum[i + 1];
        }
        inorderFillTreeNode(root, suffixSum);
        return root;
    }

    private void getInorderTraversalRes(TreeNode root, ArrayList<Integer> inorderRes) {
        if (root == null) return;
        getInorderTraversalRes(root.left, inorderRes);
        inorderRes.add(root.val);
        getInorderTraversalRes(root.right, inorderRes);
    }

    private void inorderFillTreeNode(TreeNode root, int[] suffixSum) {
        if (root == null) return;
        inorderFillTreeNode(root.left, suffixSum);
        root.val = suffixSum[idx++];
        inorderFillTreeNode(root.right, suffixSum);
    }
}
