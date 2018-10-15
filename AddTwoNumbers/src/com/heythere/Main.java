package com.heythere;

public class Main {

    public static void main(String[] args) {
        ListNode tail = null;

        ListNode listNode_1 = new ListNode(0);
        tail = listNode_1;
        tail.next = new ListNode(8);
        tail = tail.next;
        tail.next = new ListNode(6);
        tail = tail.next;
        tail.next = new ListNode(5);
        tail = tail.next;
        tail.next = new ListNode(6);
        tail = tail.next;
        tail.next = new ListNode(8);
        tail = tail.next;
        tail.next = new ListNode(3);
        tail = tail.next;
        tail.next = new ListNode(5);
        tail = tail.next;
        tail.next = new ListNode(7);

        ListNode listNode_2 = new ListNode(6);
        tail = listNode_1;
        tail.next = new ListNode(7);
        tail = tail.next;
        tail.next = new ListNode(8);
        tail = tail.next;
        tail.next = new ListNode(0);
        tail = tail.next;
        tail.next = new ListNode(8);
        tail = tail.next;
        tail.next = new ListNode(5);
        tail = tail.next;
        tail.next = new ListNode(8);
        tail = tail.next;
        tail.next = new ListNode(9);
        tail = tail.next;
        tail.next = new ListNode(7);

        Solutions solutions = new Solutions();
        ListNode sumList_1 = solutions.addTwoNumbersMySolution(listNode_1, listNode_2);
        System.out.println(solutions.getIntFromList(sumList_1));

        ListNode sumList_2 = solutions.addTwoNumbers(listNode_1, listNode_2);
        System.out.println(solutions.getIntFromList(sumList_2));
    }
}
