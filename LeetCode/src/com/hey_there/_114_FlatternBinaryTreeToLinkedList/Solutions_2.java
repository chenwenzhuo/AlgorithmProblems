package com.hey_there._114_FlatternBinaryTreeToLinkedList;

public class Solutions_2 {
    public void flatten(TreeNode root) {
        if (root != null)//仅当树非空时进行计算
            recurFlatten(root);
    }

    private TreeNode recurFlatten(TreeNode root) {
        //当前节点为叶子节点，直接返回当前节点
        if (root.left == null && root.right == null) return root;
        //分别记录左子树链表的尾节点和右子树链表的尾节点
        TreeNode leftTail = null, rightTail = null;
        //左右子树存在时，分别计算其尾节点
        if (root.left != null)
            leftTail = recurFlatten(root.left);
        if (root.right != null)
            rightTail = recurFlatten(root.right);
        //左子树存在时，将左子树链表插入到根节点和右子树链表之间
        if (leftTail != null) {
            TreeNode rootRight = root.right;//记录下root的右子树
            root.right = root.left;//左子树移动到右子树
            root.left = null;//左子树置空
            leftTail.right = rootRight;//右子树接到左子树尾部
        }
        //存在右子树时返回右子树的尾节点，否则返回左子树的尾节点
        return rightTail != null ? rightTail : leftTail;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        Solutions_2 solutions = new Solutions_2();
        solutions.flatten(root);

        TreeNode node = root;
        while (node != null) {
            System.out.println("val  " + node.val + "     node.left  " + node.left);
            node = node.right;
        }
    }
}
