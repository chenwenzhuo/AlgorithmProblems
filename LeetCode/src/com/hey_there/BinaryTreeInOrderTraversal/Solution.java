package com.hey_there.BinaryTreeInOrderTraversal;


import java.util.*;

public class Solution {
    private List<Integer> result_inOrderTraversal = new ArrayList<>();

    public List<Integer> inOrderTraversal(TreeNode root) {
        non_recursive_inOrderTraversal(root);

        return result_inOrderTraversal;
    }

    //递归的中序遍历
    public void recursive_inOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        recursive_inOrderTraversal(node.left);
        result_inOrderTraversal.add(node.val);
        recursive_inOrderTraversal(node.right);
    }

    //非递归的中序遍历
    public void non_recursive_inOrderTraversal(TreeNode treeRoot) {
        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode node = treeRoot;

        while (!nodeStack.empty() || node != null) {
            while (node != null) {
                nodeStack.push(node);
                node = node.left;
            }

            if (!nodeStack.empty()) {
                TreeNode tempNode = nodeStack.pop();
                result_inOrderTraversal.add(tempNode.val);
                node = tempNode.right;
            }
        }
    }
}
