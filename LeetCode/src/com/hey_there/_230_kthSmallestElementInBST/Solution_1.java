package com.hey_there._230_kthSmallestElementInBST;

public class Solution_1 {
    private int k;
    private int count = 0;//在递归中表示在当前次递归的tree.val是第几小的值
    private int targetValue;//目标值

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        helper(root);
        return targetValue;
    }

    private void helper(TreeNode tree) {
        if (tree == null) {
            return;
        }

        helper(tree.left);

        System.out.println("tree.val:   " + tree.val);
        //为提前终止递归，将处理过程写成这样
        if (count < k) {
            count++;
        }
        if (count == k) {
            targetValue = tree.val;//若为第k小，赋值给targetValue
            count++;
        }
        if (count > k) {
            return;
        }

        //如此处理，遍历整棵树以获取第k小的值
        /*if (++count == k) {
            targetValue = tree.val;//若为第k小，赋值给targetValue
        }*/

        helper(tree.right);
    }

    public void inOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrder(treeNode.left);
        System.out.print(treeNode.val + "   ");
        inOrder(treeNode.right);
    }

    public static void main(String[] args) {
        //建树
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(1);

        Solution_1 solution = new Solution_1();
        solution.inOrder(root);
        System.out.println();
        System.out.println("第k小的数：" + solution.kthSmallest(root, 7));
    }
}
