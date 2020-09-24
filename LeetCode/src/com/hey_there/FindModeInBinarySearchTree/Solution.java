package com.hey_there.FindModeInBinarySearchTree;

import java.util.HashSet;

public class Solution {
    private HashSet<Integer> ansSet = new HashSet<>();
    private Integer curNum = null;
    private int count;
    private int maxCount;

    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];

        inorderTraversal(root);
        if (count > maxCount) {
            ansSet.clear();
            ansSet.add(curNum);
        } else if (count == maxCount) {
            ansSet.add(curNum);
        }

        int[] ansArr = new int[ansSet.size()];
        int idx = 0;
        for (int n : ansSet) {
            ansArr[idx++] = n;
        }
        return ansArr;
    }

    private void inorderTraversal(TreeNode root) {
        if (root == null) return;

        inorderTraversal(root.left);
        if (curNum == null) {
            curNum = root.val;
            count = 1;
            maxCount = 1;
        } else {
            if (root.val == curNum) {
                count++;
            } else {
                if (count == maxCount) {
                    ansSet.add(curNum);
                    curNum = root.val;
                    count = 1;
                } else if (count > maxCount) {
                    ansSet.clear();
                    ansSet.add(curNum);
                    curNum = root.val;
                    maxCount = count;
                    count = 1;
                } else {
                    curNum = root.val;
                    count = 1;
                }
            }
        }
        inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        /*TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);

        Solution solution = new Solution();
        int[] ans = solution.findMode(root);
        for (int a : ans) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
