package com.hey_there.InterviewProblem_36_BinarySearchTree2DoublyLinkedList;

import java.util.ArrayList;

public class Solution_1 {
    //存储树的中序遍历序列
    private ArrayList<Node> inorderSeq = new ArrayList<>();

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        //中序遍历树
        inorderTraversal(root);
        int listSize = inorderSeq.size();
        for (int i = 0; i < listSize; i++) {
            Node curNode = inorderSeq.get(i);
            curNode.left = inorderSeq.get((i + listSize - 1) % listSize);
            curNode.right = inorderSeq.get((i + 1) % listSize);
        }
        return inorderSeq.get(0);
    }

    private void inorderTraversal(Node tree) {
        if (tree == null) return;
        inorderTraversal(tree.left);
        inorderSeq.add(tree);
        inorderTraversal(tree.right);
    }
}
