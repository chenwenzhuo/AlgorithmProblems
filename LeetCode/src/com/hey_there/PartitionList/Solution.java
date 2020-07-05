package com.hey_there.PartitionList;

public class Solution {
    public ListNode partition(ListNode head, int x) {
        //初始化两个链表的头结点，用来保存值小于x的结点和值大于等于x的结点
        ListNode smaller = new ListNode(-1);
        ListNode biggerOrEqual = new ListNode(-1);

        ListNode smallerRef = smaller;
        ListNode biggerOrEqualRef = biggerOrEqual;

        while (head != null) {
            if (head.val < x) {
                smallerRef.next = head;
                smallerRef = smallerRef.next;
            } else {
                biggerOrEqualRef.next = head;
                biggerOrEqualRef = biggerOrEqualRef.next;
            }
            head = head.next;
        }
        smallerRef.next = null;
        biggerOrEqualRef.next = null;

        smallerRef.next = biggerOrEqual.next;

        return smaller.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        Solution solution = new Solution();
        head = solution.partition(head, 3);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
