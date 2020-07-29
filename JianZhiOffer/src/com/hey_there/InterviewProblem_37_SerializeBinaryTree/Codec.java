package com.hey_there.InterviewProblem_37_SerializeBinaryTree;

import java.util.LinkedList;

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder levelOrderTraversal = new StringBuilder();//层序遍历结果
        LinkedList<TreeNode> queue = new LinkedList<>();//队列辅助层序遍历
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode != null) {
                levelOrderTraversal.append(curNode.val);
                levelOrderTraversal.append(",");
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            } else {
                //用井号表示null
                levelOrderTraversal.append("#,");
            }
        }
        //删除遍历结果结尾的所有"#,"
        while (levelOrderTraversal.substring(levelOrderTraversal.length() - 2, levelOrderTraversal.length())
                .equals("#,")) {
            levelOrderTraversal.delete(levelOrderTraversal.length() - 2, levelOrderTraversal.length());
        }
        //删除结尾的逗号
        levelOrderTraversal.delete(levelOrderTraversal.length() - 1, levelOrderTraversal.length());
        return levelOrderTraversal.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals(""))
            return null;
        String[] dataArr = data.split(",");
        int dataArrLen = dataArr.length;

        TreeNode root = new TreeNode(Integer.parseInt(dataArr[0]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int dataIdx = 1;
        while (!queue.isEmpty() && dataIdx < dataArrLen) {
            TreeNode curNode = queue.poll();
            if (!dataArr[dataIdx].equals("#")) {
                curNode.left = new TreeNode(Integer.parseInt(dataArr[dataIdx]));
                queue.offer(curNode.left);
            }
            dataIdx++;
            if (dataIdx < dataArrLen && !dataArr[dataIdx].equals("#")) {
                curNode.right = new TreeNode(Integer.parseInt(dataArr[dataIdx]));
                queue.offer(curNode.right);
            }
            dataIdx++;
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
        String serializedTree = codec.serialize(root);
        System.out.println(serializedTree);

        TreeNode deserializedTree = codec.deserialize(serializedTree);
        String serializedDeserializedTree = codec.serialize(deserializedTree);
        System.out.println(serializedDeserializedTree);
    }
}
