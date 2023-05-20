package com.hey_there._270_ClosestBinarySearchTreeValue;

public class Solution {
    private int ans;
    private double diff = Double.MAX_VALUE;

    public int closestValue(TreeNode root, double target) {
        recursiveSearch(root, target);
        return ans;
    }

    private void recursiveSearch(TreeNode root, double target) {
        if (root == null) return;
        double curDiff = Math.abs(((double) root.val) - target);//当前两数距离
        if (curDiff < diff || (curDiff == diff && root.val < ans)) {
            ans = root.val;
            diff = curDiff;
        }
        recursiveSearch(root.left, target);
        recursiveSearch(root.right, target);
    }
}
