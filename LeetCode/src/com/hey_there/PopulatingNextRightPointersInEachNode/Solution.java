package com.hey_there.PopulatingNextRightPointersInEachNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    //按二叉树的层序遍历的思想完成
    public Node connect_levelOrderTraversalBased(Node root) {
        //若为空树，直接返回
        if (root == null) {
            return null;
        }

        //队列辅助层序遍历
        Queue<Node> traversalQueue = new LinkedList<>();
        //根结点入队
        traversalQueue.add(root);
        traversalQueue.add(null);//分层标记null

        while (!traversalQueue.isEmpty()) {
            Node currentNode = traversalQueue.poll();//队首结点出队
            //队首结点为null，说明前一层已处理完，
            //插入分层标记null后处理下一个结点
            if (currentNode == null) {
                //检查队列中是否还有未处理的树结点，
                //若没有则退出循环
                if (traversalQueue.isEmpty()) {
                    break;
                }
                traversalQueue.add(null);
                continue;
            }

            //traversalQueue.peek()为null，说明currentNode是当前层最后一个结点
            //则currentNode.next应为null
            //traversalQueue.peek()不为null，则currentNode.next指向traversalQueue.peek()
            currentNode.next = traversalQueue.peek();

            if (currentNode.left != null) {
                traversalQueue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                traversalQueue.add(currentNode.right);
            }
        }
        return root;
    }

    //递归解法
    public Node connect_recursive(Node root) {
        if (root == null) {
            return null;//若为空树，返回null
        }
        return connectNext(root);//不为空树时对其进行处理
    }

    private Node connectNext(Node node) {
        if (node.left != null && node.right != null) {
            //左右子树均非空时，递归对其进行处理
            Node left = connectNext(node.left);
            Node right = connectNext(node.right);

            //将左子树每层的最右边结点的next指向右子树每层的最左边结点
            while (left != null && right != null) {
                left.next = right;

                left = left.right;
                right = right.left;
            }
            return node;
        } else {
            return node;
        }
    }
}
