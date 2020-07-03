package com.hey_there.ConvertSortedListToBinarySearchTree;

import com.hey_there.LinkedList.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        //遍历链表，将所有结点存到ArrayList中
        List<ListNode> listNodes = new ArrayList<>();
        while (head != null) {
            listNodes.add(head);
            head = head.next;
        }

        int listLen = listNodes.size();//原链表中结点个数
        return buildBST(listNodes, 0, listLen - 1);
    }

    private TreeNode buildBST(List<ListNode> listNodes, int left, int right) {
        if (left > right) {
            return null;//当左边界大于右边界时直接返回
        }

        int mid = (left + right) / 2;//中间链表结点的下标
        TreeNode treeNode = new TreeNode(listNodes.get(mid).val);//以中间链表结点的值建立一个树结点

        treeNode.left = buildBST(listNodes, left, mid - 1);//构建当前子树的左子树
        treeNode.right = buildBST(listNodes, mid + 1, right);//构建当前子树的右子树
        return treeNode;
    }

    public void preOrderSearchTree(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + "   ");
        preOrderSearchTree(root.left);
        preOrderSearchTree(root.right);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        Solution solution = new Solution();
        TreeNode bst = solution.sortedListToBST(head);
        solution.preOrderSearchTree(bst);
        System.out.println();
    }
}
