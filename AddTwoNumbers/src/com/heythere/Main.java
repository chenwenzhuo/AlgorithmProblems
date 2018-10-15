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
        tail = listNode_2;
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

        /*
        753865680
        798580876
        1552446556
         */
        Solutions solutions = new Solutions();
        ListNode sumList_1 = solutions.addTwoNumbers(listNode_1, listNode_2);

        System.out.println("First argument:" + solutions.getLongFromList(listNode_1));
        System.out.println("Second argument:" + solutions.getLongFromList(listNode_2));
        System.out.println("Sum:" + solutions.getLongFromList(sumList_1));

        /*ListNode sumList_2 = solutions.addTwoNumbersMySolution_2(listNode_1, listNode_2);
        System.out.println(solutions.getIntFromList(sumList_2));*/
    }
}
