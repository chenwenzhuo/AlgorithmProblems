package com.hey_there.InterviewProblem_24_ReverseList;

public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        ListNode ref = head;
        //将ref指向的节点从链表中取下，接到节点dummy之后
        while (ref != null) {
            ListNode nextNode = ref.next;
            ref.next = dummy.next;
            dummy.next = ref;
            ref = nextNode;
        }
        return dummy.next;
    }
}
