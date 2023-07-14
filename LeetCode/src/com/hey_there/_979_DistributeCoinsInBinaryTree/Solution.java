package com.hey_there._979_DistributeCoinsInBinaryTree;

public class Solution {
    private int cnt = 0;

    public int distributeCoins(TreeNode root) {
        distribute(root);
        return cnt;
    }

    //返回值为一个长为2的数组，分别表示当前子树中已有的值和需要的值
    private int[] distribute(TreeNode root) {
        //空树，已有0，需要也是0
        if (root == null)
            return new int[]{0, 0};
        int[] leftArr = distribute(root.left);
        int[] rightArr = distribute(root.right);
        //若数组两元素不等，则会产生当前节点和子节点之间的移动
        if (leftArr[0] != leftArr[1]) {
            cnt += Math.abs(leftArr[0] - leftArr[1]);
        }
        if (rightArr[0] != rightArr[1]) {
            cnt += Math.abs(rightArr[0] - rightArr[1]);
        }
        return new int[]{
                leftArr[0] + rightArr[0] + root.val,
                leftArr[1] + rightArr[1] + 1,
        };
    }
}
