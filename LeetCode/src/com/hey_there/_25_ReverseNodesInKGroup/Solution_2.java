package com.hey_there._25_ReverseNodesInKGroup;

public class Solution_2 {
    //反转链表前n个节点时，第n个节点的后继节点
    private ListNode successor = null;

    //反转链表前n个节点
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;//记录后继节点
            return head;//仅反转一个节点时，直接返回
        }
        //反转head节点之后的n-1个节点
        ListNode rev = reverseN(head.next, n - 1);
        head.next.next = head;//将head节点接到后n-1个节点之后
        head.next = successor;
        return rev;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        //判断链表节点数是否大于等于k
        if (head == null) return null;
        int cnt = 0;
        ListNode ref = head;
        while (ref != null && cnt < k) {
            cnt++;
            ref = ref.next;
        }
        if (cnt < k) return head;//若链表head节点数小于k，直接返回此链表

        //链表节点数不少于k，反转前k个节点
        ListNode rev = reverseN(head, k);
        //successor此时指向链表第k+1个节点（或successor为null），递归反转
        //head节点此时为successor节点的前驱，将反转后的链表接到head节点之后
        head.next = reverseKGroup(successor, k);
        return rev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        Solution_2 solution = new Solution_2();
        head = solution.reverseKGroup(head, 3);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
