package com.hey_there.BinaryTreePreOrderTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> traversalResult = new ArrayList<>();//遍历结果
        //若为空树，直接返回
        if (root == null) {
            return traversalResult;
        }

        Stack<TreeNode> helperStack = new Stack<>();//辅助遍历的栈
        helperStack.push(root);//根结点入栈
        while (!helperStack.empty()) {
            TreeNode currentNode = helperStack.pop();//当前遍历的结点
            traversalResult.add(currentNode.val);

            //当前结点的子节点入栈
            if (currentNode.right != null) {
                helperStack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                helperStack.push(currentNode.left);
            }
        }

        return traversalResult;
    }

    public void recursiveTraversal(TreeNode node, List<Integer> traversalResult) {
        if (node == null) {
            return;
        }
        traversalResult.add(node.val);
        recursiveTraversal(node.left, traversalResult);
        recursiveTraversal(node.right, traversalResult);
    }

    public static void main(String[] args) {
        //建树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);

        Solution solution = new Solution();
        List<Integer> result = new ArrayList<>();
        solution.recursiveTraversal(root, result);
        System.out.println(result);
    }
}
