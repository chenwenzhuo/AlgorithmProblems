package com.hey_there.DailyProblems.June.SerializeAndDeserializeBinaryTree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        //队列辅助层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        //层序遍历结果
        StringBuilder traversalRes = new StringBuilder();

        //遍历开始前将根节点存入队列
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            //当前节点为null时无需向队列中添加子节点
            if (curNode == null) {
                //向遍历结果中加入一个null
                traversalRes.append(",null");
                continue;
            }
            /*当前结点非null时需要将其子节点加入队列*/
            //将节点值加入遍历结果中
            if (traversalRes.length() > 0) {
                traversalRes.append(",");
            }
            traversalRes.append(curNode.val);
            queue.offer(curNode.left);
            queue.offer(curNode.right);
        }
        //去掉尾部连续的null
        while (traversalRes.length() > 4 &&
                traversalRes.substring(traversalRes.length() - 4, traversalRes.length()).equals("null")) {
            //去掉一个",null"子串
            traversalRes.delete(traversalRes.length() - 5, traversalRes.length());
        }
        return traversalRes.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        //将输入字符串以逗号分割
        String[] nodeValues = data.split(",");
        int lenData = nodeValues.length;
        //队列辅助重建二叉树
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        //单独创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(nodeValues[0]));
        queue.offer(root);

        int nvIdx = 1;
        while (nvIdx < lenData) {
            TreeNode parent = queue.poll();
            if (!nodeValues[nvIdx].equals("null")) {
                parent.left = new TreeNode(Integer.parseInt(nodeValues[nvIdx]));
                queue.offer(parent.left);
            }
            nvIdx++;
            if (nvIdx < lenData && !nodeValues[nvIdx].equals("null")) {
                parent.right = new TreeNode(Integer.parseInt(nodeValues[nvIdx]));
                queue.offer(parent.right);
            }
            nvIdx++;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Codec codec = new Codec();
        String serializedTree = codec.serialize(root);//序列化树
        System.out.println(serializedTree);
        TreeNode reconstructedTree = codec.deserialize(serializedTree);//反序列化树
        System.out.println(codec.serialize(reconstructedTree));
    }
}
