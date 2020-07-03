package com.hey_there.RotateList;

import com.hey_there.LinkedList.ListNode;

public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        //若链表长度为0或1，直接返回原链表
        if (head == null || head.next == null) {
            return head;
        }

        //计算链表长度
        ListNode listRef = head;
        int listLen = 1;
        while (listRef.next != null) {
            listRef = listRef.next;
            listLen++;
        }
        //将最后一个结点的next指向第一个结点，使链表连成环
        ListNode tail = listRef;
        tail.next = head;

        //用 k 除以 listLen 取余，获得最小旋转步数
        //因为对一个长为3的链表，旋转1步，旋转4步，旋转7步结果是一样的
        int stepsToRotate = k % listLen;

        //若k是链表长度的整数倍，则不需要旋转
        if (stepsToRotate == 0) {
            //将连成环的链表断开后返回
            tail.next = null;
            return head;
        }

        //获得旋转后的新链表的第一个结点
        ListNode newHead = getPostNode(head, listLen - stepsToRotate);
        //获得旋转后的新链表的最后一个结点
        ListNode newTail = getPostNode(tail, listLen - stepsToRotate);

        //将连成环的链表断开
        newTail.next = null;

        return newHead;
    }

    //获得nodeRef结点的后方steps个位置的结点
    private ListNode getPostNode(ListNode nodeRef, int steps) {
        while (steps > 0) {
            nodeRef = nodeRef.next;
            steps--;
        }
        return nodeRef;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Solution solution = new Solution();
        head = solution.rotateRight(head, 4);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
