package com.hey_there._449_SerializeAndDeserializeBST;

import java.util.ArrayDeque;

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //获取BST先序遍历结果
        StringBuilder preRes = new StringBuilder();
        preorderTraversal(root, preRes);
        //空树返回空字符串，非空树去掉最后一个字符（多余分隔符）后返回
        return preRes.length() == 0 ? "" :
                preRes.substring(0, preRes.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //字符串长度为0时表示空树
        if (data.length() == 0) return null;
        //将字符串以逗号","分割，为数组
        String[] dataArr = data.split(",");
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (String d : dataArr)
            queue.offer(Integer.parseInt(d));
        return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, queue);
    }

    //先序遍历二叉搜索树
    private void preorderTraversal(TreeNode root, StringBuilder preorderRes) {
        if (root == null) return;
        //将节点值加入字符串，并加入分隔符","
        preorderRes.append(root.val).append(",");
        //递归遍历子树
        preorderTraversal(root.left, preorderRes);
        preorderTraversal(root.right, preorderRes);
    }

    /* 根据先序遍历结果构建BST。
     * 队列首元素为根节点，后续元素小于首元素的为左子树节点，大于首元素的为右子树节点。
     * 递归构建各级子树*/
    private TreeNode construct(int low, int high, ArrayDeque<Integer> queue) {
        if (queue.isEmpty() || queue.peek() < low || queue.peek() > high)
            return null;
        int val = queue.poll();//取队首元素
        TreeNode root = new TreeNode(val);//构建根节点
        root.left = construct(low, val, queue);
        root.right = construct(val, high, queue);
        return root;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>() {{
            push(1);
            push(2);
            push(3);
            push(4);
        }};
        System.out.println(deque);
        while (!deque.isEmpty()) {
            System.out.println(deque.peek() + "   " + deque.poll());
        }
        System.out.println();

        deque.clear();
        deque.offer(5);
        deque.offer(6);
        deque.offer(7);
        deque.offer(8);
        System.out.println(deque);
        while (!deque.isEmpty()) {
            System.out.println(deque.peek() + "   " + deque.pop());
        }
    }
}
