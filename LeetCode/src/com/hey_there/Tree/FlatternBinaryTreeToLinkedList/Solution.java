package com.hey_there.FlatternBinaryTreeToLinkedList;

import com.hey_there.BinaryTreeInOrderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Integer> result_preOrderTraversal = new ArrayList<>();

    /*观察到链表序列即为先序遍历序列，
    * 故先序遍历二叉树，获取遍历序列，
    * 再根据先序序列构建链表*/
    public void flatten_preOrderTraversalBased(TreeNode root) {
        preOrderTraversal(root);//获取前序遍历序列

        //按前序遍历序列将所有结点的值链接到右子树
        for (int index = 1; index < result_preOrderTraversal.size(); index++) {
            root.left = null;//将所有结点的左子树置为null

            if (root.right != null) {//若右子树非空，直接赋值
                root.right.val = result_preOrderTraversal.get(index);
            } else {
                //若当前结点没有右子树，则开辟空间
                root.right = new TreeNode(result_preOrderTraversal.get(index));
            }

            root = root.right;
        }
    }

    private void preOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        result_preOrderTraversal.add(node.val);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    /*使用迭代方法，对于每个结点，
    * 将右子树挂到左子树的最右边结点上，
    * 再将左子树挂到右子树上*/
    public void flatten(TreeNode root) {
        while (root != null) {
            //若左子树非空，寻找左子树的最右边结点
            //若左子树为空，直接考虑右子树下一个结点
            if (root.left != null) {
                TreeNode leftChild = root.left;

                //寻找左子树的最右边结点
                while (leftChild.right != null) {
                    leftChild = leftChild.right;
                }

                //将root的右子树挂到左子树的最右边结点
                leftChild.right = root.right;
                //将root的左子树挂到右子树
                root.right = root.left;
                root.left = null;//将每个结点的左子树置为null
            }

            //考虑右子树下一个结点
            root = root.right;
        }
    }
}
