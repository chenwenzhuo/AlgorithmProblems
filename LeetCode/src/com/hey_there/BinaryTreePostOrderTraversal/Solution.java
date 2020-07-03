package com.hey_there.BinaryTreePostOrderTraversal;

import java.util.*;

public class Solution {
    public List<Integer> postOrderTraversal_1(TreeNode root) {
        ArrayList<Integer> traversalResult = new ArrayList<>();//List集合储存遍历结果
        if (root == null) {
            return traversalResult;//若为空树，直接返回
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        //以键值对的形式保存结点是否被访问过的标记
        Map<TreeNode, Boolean> mark_nodeVisited = new HashMap<>();

        TreeNode node;
        nodeStack.push(root);
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

    /*借鉴先序遍历的思想,
     * 将结点按 根->左->右 的顺序加入队列，
     * 每次获取队尾结点，并且使用LinkedList每次在链首加入数字，
     * 最后得到的list就是后序遍历序列*/
    public List<Integer> postOrderTraversal_2(TreeNode root) {
        LinkedList<Integer> traversalResult = new LinkedList<>();//遍历结果
        if (root == null) {
            return traversalResult;//若为空树，直接返回
        }

        LinkedList<TreeNode> helperList = new LinkedList<>();//辅助队列
        helperList.add(root);//根结点入队

        while (!helperList.isEmpty()) {
            TreeNode currentNode = helperList.pollLast();//获取队尾结点
            traversalResult.addFirst(currentNode.val);//将结点值加入链首

            if (currentNode.left != null) {
                helperList.add(currentNode.left);
            }
            if (currentNode.right != null) {
                helperList.add(currentNode.right);
            }
        }

        return traversalResult;
    }
}
