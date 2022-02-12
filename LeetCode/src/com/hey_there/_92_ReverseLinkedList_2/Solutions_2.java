package com.hey_there._92_ReverseLinkedList_2;

//一种更优的递归算法
public class Solutions_2 {
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

    //反转链表第left个到第right个节点
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //left为1时，问题退化为反转链表前right个节点
        if (left == 1) return reverseN(head, right);

        /*对于链表head，需要反转[left,right]之间的节点
         * 对于链表head.next，则需要反转[left-1,right-1]之间的节点
         * 对于链表head.next.next，则需要反转[left-2,right-2]之间的节点...*/
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }
}
