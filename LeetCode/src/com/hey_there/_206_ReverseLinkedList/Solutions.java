package com.hey_there._206_ReverseLinkedList;

public class Solutions {
    public ListNode reverseList(ListNode head) {
        ListNode reversedHead = new ListNode(-1);

        while (head != null) {
            ListNode nextNode = head.next;

            head.next = reversedHead.next;
            reversedHead.next = head;

            head = nextNode;
        }
        return reversedHead.next;
    }

    public ListNode reverseList_recursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList_recursive(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
}
