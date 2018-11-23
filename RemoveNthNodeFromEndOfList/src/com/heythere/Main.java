package com.heythere;

public class Main {

    public static void main(String[] args) {
        ListNode node_1 = new ListNode(1);
        ListNode node_2 = new ListNode(2);
        ListNode node_3 = new ListNode(3);
        ListNode node_4 = new ListNode(4);
        ListNode node_5 = new ListNode(5);
        node_1.next = node_2;
        node_2.next = node_3;
        node_3.next = node_4;
        node_4.next = node_5;

        Solutions solutions = new Solutions();
        ListNode ref = solutions.removeNthFromEnd(node_1, 3);
        while (null != ref) {
            System.out.println(ref.val);
            ref = ref.next;
        }
    }
}
