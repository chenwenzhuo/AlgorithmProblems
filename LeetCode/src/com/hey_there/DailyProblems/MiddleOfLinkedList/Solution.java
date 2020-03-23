package com.hey_there.DailyProblems.MiddleOfLinkedList;

public class Solution {
    public static ListNode middleNode_LeetCodeDontPass(ListNode head) {
        //快慢指针
        ListNode slow = head, fast = head;
        while (fast != null) {
            //slow每次后移一步
            slow = slow.next;
            //fast每次后移两步
            fast = fast.next;
            //移动第二部时要判断是否已经到最后一个结点
            if (fast != null) {
                fast = fast.next;
            }
        }
        return slow;
    }

    public static ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        //head.next.next.next.next.next.next = new ListNode(6);

        System.out.println(middleNode(head).val);
    }
}
