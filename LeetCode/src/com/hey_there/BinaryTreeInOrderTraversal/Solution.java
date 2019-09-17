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

    //非递归的后序遍历
    public List<Integer> non_recursivePostOrderTraversal(TreeNode treeRoot) {
        List<Integer> traversalResult = new ArrayList<>();//List集合储存遍历结果
        Stack<TreeNode> nodeStack = new Stack<>();
        //以键值对的形式保存结点是否被访问过的标记
        Map<TreeNode, Boolean> mark_nodeVisited = new HashMap<>();

        TreeNode node;
        nodeStack.push(treeRoot);
        while (!nodeStack.empty()) {
            node = nodeStack.peek();

            if ((node.left == null && node.right == null) || childrenVisited(node, mark_nodeVisited)) {
                TreeNode tempNode = nodeStack.pop();
                traversalResult.add(tempNode.val);

                mark_nodeVisited.put(tempNode, true);//将访问过的结点标记为true
            } else {
                if (node.right != null) {
                    nodeStack.push(node.right);
                    mark_nodeVisited.put(node.right, false);//将首次入栈的结点标记为false
                }
                if (node.left != null) {
                    nodeStack.push(node.left);
                    mark_nodeVisited.put(node.left, false);
                }
            }
        }

        return traversalResult;
    }

    private boolean childrenVisited(TreeNode node, Map<TreeNode, Boolean> marks) {
        boolean leftVisited = false;
        boolean rightVisited = false;

        if (node.left == null) {
            leftVisited = true;
        } else {
            if (marks.containsKey(node.left)) {
                leftVisited = marks.get(node.left);
            }
        }

        if (node.right == null) {
            rightVisited = true;
        } else {
            if (marks.containsKey(node.right)) {
                rightVisited = marks.get(node.right);
            }
        }

        return leftVisited && rightVisited;
    }
}
