package com.hey_there.UniqueBinarySearchTrees_GenTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        List<TreeNode>[] resultTrees = new ArrayList[n + 1];
        resultTrees[0] = new ArrayList<>();
        resultTrees[0].add(null);

        for (int len = 1; len <= n; len++) {
            resultTrees[len] = new ArrayList<>();

            for (int rootVal = 1; rootVal <= len; rootVal++) {
                int leftTree_len = rootVal - 1;
                int rightTree_len = len - rootVal;
                List<TreeNode> leftTrees = resultTrees[leftTree_len];
                List<TreeNode> rightTrees = resultTrees[rightTree_len];

                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightTree : rightTrees) {
                        TreeNode copiedTree = new TreeNode(rootVal);
                        copiedTree.left = leftTree;
                        copiedTree.right = copyTree(rightTree, rootVal);

                        resultTrees[len].add(copiedTree);
                    }
                }
            }
        }

        return resultTrees[n];
    }

    private TreeNode copyTree(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = copyTree(n.left, offset);
        node.right = copyTree(n.right, offset);
        return node;
    }

    //非递归的先序遍历
    public List<Integer> non_recursivePreOrderTraversal(TreeNode treeRoot) {
        List<Integer> traversalResult = new ArrayList<>();//List集合储存遍历结果
        Stack<TreeNode> nodeStack = new Stack<>();
        //TreeNode node = treeRoot;

        nodeStack.push(treeRoot);
        while (!nodeStack.empty()) {
            TreeNode tempNode = nodeStack.pop();
            traversalResult.add(tempNode.val);

            if (tempNode.right != null) {
                nodeStack.push(tempNode.right);
            }
            if (tempNode.left != null) {
                nodeStack.push(tempNode.left);
            }
        }

        return traversalResult;
    }
}
