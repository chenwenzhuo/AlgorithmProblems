package com.hey_there.InterviewProblem_36_BinarySearchTree2DoublyLinkedList;

public class Solution_2 {
    private Node head = null;//链表中的第一个节点
    private Node pre = null;//中序遍历时，当前节点的前驱节点

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        //中序遍历构建链表
        inorderTraversal(root);
        //完成后head和pre分别指向链表中的首位节点
        //将首尾相接
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void inorderTraversal(Node node) {
        if (node == null) return;
        inorderTraversal(node.left);//递归向下，转换左子树为链表
        if (pre == null)
            //pre为null，说明当前node是二叉树的最左边节点，在链表中没有前驱
            head = node;
        else {
            //pre不为null时，将前驱节点与当前节点相连
            pre.right = node;
            node.left = pre;
        }
        //当前节点是右子树的前驱节点，向下递归右子树前将pre指向当前节点
        pre = node;
        inorderTraversal(node.right);//递归向下，转换右子树为链表
    }
}
