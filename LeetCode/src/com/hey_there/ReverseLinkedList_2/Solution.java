package com.hey_there.ReverseLinkedList_2;

public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //若反转的起始点和结束点相同，无需反转，返回原链表
        if (m == n) {
            return head;
        }
        //给原链表加一个头结点，方便操作
        ListNode headNode = new ListNode(-1);
        headNode.next = head;

        ListNode nodeBeforeReversed = null;//需要反转部分的前一个结点
        int pos = -1;//当前结点在原链表中的位置，-1表示未开始遍历，0表示头结点，其他值表示原链表中的结点

        ListNode reversedPartHead = new ListNode(-1);//要反转部分的头结点
        ListNode reversedPartTail = null;//要反转部分反转后的的最后一个结点

        head = headNode;//开始遍历之前将head指向头结点
        while (head != null) {
            pos++;//pos自增1，表示当前结点的编号
            //pos小于m时，即对于m之前的结点，不进行反转
            if (pos < m) {
                if (pos == m - 1) {
                    //保存 m 的前一个结点的引用
                    nodeBeforeReversed = head;
                }
                head = head.next;
                continue;
            }

            //当pos大于n时，要反转的部分已反转完毕，退出循环
            if (pos > n) {
                break;
            }

            ListNode nextNode = head.next;//保存下一个结点的引用
            if (pos == m) {
                //pos等于m时，当前结点是反转后部分的最后一个，保存其引用
                reversedPartTail = head;
            }
            //将结点插入反转链表的头结点之后
            head.next = reversedPartHead.next;
            reversedPartHead.next = head;

            head = nextNode;
        }

        /*idea提示可能产生NullPointerException，实际不会，
         * reversedPartTail和nodeBeforeReversed在while循环中一定会被初始化*/
        //将n之后的部分连接到反转后链表上
        reversedPartTail.next = head;
        //将反转后链表连接到m之前的链表上
        nodeBeforeReversed.next = reversedPartHead.next;
        return headNode.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Solution solution = new Solution();
        head = solution.reverseBetween(head, 2, 4);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
