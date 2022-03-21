package com.hey_there._653_TwoSum4_InputIsBST;

import java.util.HashSet;

public class Solution {
    private HashSet<Integer> valSet = new HashSet<>();
    private int target;
    private boolean found = false;

    public boolean findTarget(TreeNode root, int k) {
        this.target = k;
        traversal(root);
        return found;
    }

    private void traversal(TreeNode root) {
        if (root == null || found) return;
        if (valSet.contains(target - root.val)) {
            found = true;
            return;
        }
        valSet.add(root.val);
        traversal(root.left);
        traversal(root.right);
    }
}
