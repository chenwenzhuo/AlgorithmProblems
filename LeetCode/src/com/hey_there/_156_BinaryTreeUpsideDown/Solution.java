package com.hey_there._156_BinaryTreeUpsideDown;

public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return upsideDownBinaryTree(root, null, null);
    }

    private TreeNode upsideDownBinaryTree(TreeNode root, TreeNode leftChild, TreeNode rightChild) {
        if (root == null)//空树无需翻转
            return null;
        //root在原树中为叶节点，则root是原树中最左侧节点，无需继续递归
        boolean recurseFlag = !(root.left == null && root.right == null);
        //保存root节点原本的左右子结点引用，并将左右子树指向参数leftChild、rightChild
        TreeNode leftCopy = root.left, rightCopy = root.right;
        root.left = leftChild;
        root.right = rightChild;
        if (!recurseFlag)
            return root;
        //原左子结点作为新的根节点，原右子节点作为新的左子结点，原root作为新的右子节点
        return upsideDownBinaryTree(leftCopy, rightCopy, root);
    }
}
