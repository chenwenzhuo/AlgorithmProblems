package com.hey_there.LinkedList.InsersionSortList;

import com.hey_there.LinkedList.ListNode;

//执行时间1ms的算法
public class Solution_1ms {
    private ListNode sentinel = new ListNode(-1);

    public ListNode insertionSortList(ListNode head) {
        return sortList(head);
    }

    private ListNode sortList(ListNode head) {
        int size = getSize(head);
        return internelSortList(head, size);
    }

    private ListNode internelSortList(ListNode head, int size) {
        if (size < 5) return sortInConstantTime(head, size);

        int mid = size >> 1;
        ListNode midNode = getNode(head, mid);
        ListNode lower = internelSortList(head, mid);
        ListNode higher = internelSortList(midNode, size - mid);
        return mergeSortedList(lower, higher);
    }

    private int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            ++size;
            head = head.next;
        }
        return size;
    }

    private ListNode getNode(ListNode head, int index) {
        while (index > 0) {
            head = head.next;
            --index;
        }
        return head;
    }

    // size > 1 is recommended
    private ListNode sortInConstantTime(ListNode head, int size) {
        sentinel.next = head;
        ListNode traveller, before, next;
        for (int i = 1; i < size; ++i) {
            before = sentinel;
            traveller = sentinel.next;
            for (int j = 0; i + j < size; ++j) {
                next = traveller.next;
                if (traveller.val > next.val) {
                    before.next = next;
                    traveller.next = next.next;
                    next.next = traveller;
                } else {
                    traveller = next;
                }
                before = before.next;
            }
            if (i == 1) traveller.next = null;
        }
        return sentinel.next;
    }

    private ListNode mergeSortedList(ListNode a, ListNode b) {
        ListNode last = sentinel;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                last.next = a;
                last = a;
                a = a.next;
            } else {
                last.next = b;
                last = b;
                b = b.next;
            }
        }
        if (a != null) last.next = a;
        else if (b != null) last.next = b;
        return sentinel.next;
    }
}
