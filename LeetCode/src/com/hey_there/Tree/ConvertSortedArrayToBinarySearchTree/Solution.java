package com.hey_there.Tree.ConvertSortedArrayToBinarySearchTree;

public class Solution {
    private int[] numsArray;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.numsArray = nums;


        return convertHelper(0, nums.length);
    }

    private TreeNode convertHelper(int arrLeft, int arrRight) {
        if (arrLeft == arrRight) {
            return null;
        }

        int mid = (arrLeft + arrRight) / 2;//数字范围的中间值
        TreeNode currentRoot = new TreeNode(numsArray[mid]);
        currentRoot.left = convertHelper(arrLeft, mid);
        currentRoot.right = convertHelper(mid + 1, arrRight);

        return currentRoot;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-10, -3, 0, 5, 9};
        System.out.println(solution.sortedArrayToBST(nums).val);
    }
}
