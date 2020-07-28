package com.hey_there.InterviewProblem_33_PostorderTraversalSequenceOfBinarySearchTree;

public class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorder(int[] postorder, int begin, int end) {
        //起始下标大于终止下标，表示此子树为空树，返回true
        if (begin > end) return true;
        //起止下标相等，此子树仅有一个节点，返回true
        if (begin == end) return true;

        //下标end处应当是当前子树的根节点值
        int rootVal = postorder[end];
        /* 从下标end向前遍历，
         * 直到找到一个小于rootVal的值，或走到尽头（begin）*/
        int idx_smaller = end - 1;
        while (idx_smaller >= begin && postorder[idx_smaller] > rootVal)
            idx_smaller--;
        /* 从下标begin开始向后遍历，
         * 直到找到一个大于rootVal的值，或走到尽头（end）*/
        int idx_bigger = begin;
        while (idx_bigger < end && postorder[idx_bigger] < rootVal)
            idx_bigger++;
        //idx_smaller和idx_bigger应满足差值为1，否则返回false
        if (idx_smaller + 1 != idx_bigger)
            return false;
        //idx_smaller和idx_bigger满足要求时，递归判断左右子树是否都是BST
        return verifyPostorder(postorder, begin, idx_smaller) &&
                verifyPostorder(postorder, idx_bigger, end - 1);
    }
}
