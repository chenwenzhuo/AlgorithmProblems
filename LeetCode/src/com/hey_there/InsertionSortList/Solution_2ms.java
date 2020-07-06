package com.hey_there.InsertionSortList;


//执行时间2ms的算法
public class Solution_2ms {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode next = slow.next;
        slow.next = null;
        ListNode left = insertionSortList(head);
        ListNode right = insertionSortList(next);

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = new ListNode(left.val);
                left = left.next;
            }else {
                cur.next = new ListNode(right.val);
                right = right.next;
            }
            cur = cur.next;
        }

        cur.next = left != null ? left : right;
        return dummy.next;
    }
}
