package com.hey_there._450_DeleteNodeInBST;

public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        //节点值不等于key，则递归向左右子树中寻找，完成后返回根节点值
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        //节点值等于key，则需要将当前节点从树中删除
        //当前节点为叶子节点，可直接删除
        if (root.left == null && root.right == null) return null;

        if (root.left == null) root = root.right;//左子树为空，使用右子树顶替当前节点的位置
        else if (root.right == null) root = root.left;//右子树为空，使用左子树顶替当前节点的位置
        else {//左右子树均非空，则使用左子树的最大值节点顶替当前节点
            TreeNode leftMax = getMaxNodeInBST(root.left);
            root.val = leftMax.val;//修改节点值
            root.left = deleteNode(root.left, leftMax.val);//从左子树中删除其最大值的节点
        }
        return root;
    }

    //找到BST中最大值的节点
    private TreeNode getMaxNodeInBST(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root;
        while (temp.right != null) temp = temp.right;
        return temp;
    }
}
