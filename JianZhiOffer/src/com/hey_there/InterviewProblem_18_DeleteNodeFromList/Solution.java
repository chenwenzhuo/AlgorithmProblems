package com.hey_there.InterviewProblem_18_DeleteNodeFromList;

public class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        //给链表添加一个头节点方便操作
        ListNode newHead = new ListNode(-1);
        newHead.next = head;

        ListNode prev = newHead;
        ListNode node = head;
        while (node != null) {
            if (node.val != val) {
                prev = node;
                node = node.next;
                continue;
            }
            prev.next = node.next;
            node = null;
        }
        return newHead.next;
    }
}
