package com.hey_there.InterviewProblem_24_ReverseList;

public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode cur = head.next;
        //不断将cur指向的节点插入到newHead节点之后
        while (cur != null) {
            head.next = cur.next;
            cur.next = newHead.next;
            newHead.next = cur;
            cur = head.next;
        }
        return newHead.next;
    }
}
