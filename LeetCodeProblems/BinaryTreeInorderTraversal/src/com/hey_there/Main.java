package com.hey_there;

public class Main {

    public static void main(String[] args) {
        //构造一棵二叉树
        TreeNode root = new TreeNode(1);//第一层
        //第二层
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        //第三层
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        //第四层
        root.left.left.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);
        root.right.left.right = new TreeNode(10);
        root.right.right.left = new TreeNode(11);

        Solution solution = new Solution();
        System.out.println("先序遍历：" + solution.non_recursivePreOrderTraversal(root));
        System.out.println("中序遍历：" + solution.inOrderTraversal(root));
        System.out.println("后序遍历：" + solution.non_recursivePostOrderTraversal(root));
    }
}
