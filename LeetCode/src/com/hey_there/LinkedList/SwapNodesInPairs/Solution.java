package com.hey_there.LinkedList.SwapNodesInPairs;

import com.hey_there.LinkedList.ListNode;

public class Solution {
    public ListNode swapPairs_nonRecursive(ListNode head) {
        //处理特殊值
        if (head == null || head.next == null) {
            /*当head为null（链表长度为0）或当head.next为null（链表长度为1），
             * 直接返回head*/
            return head;
        }

        //给原链表添加一个头结点，方便操作
        ListNode headNode = new ListNode(-1);
        headNode.next = head;

        //待交换的一对结点
        ListNode nodePair_1 = head;
        ListNode nodePair_2;

        //待交换的一对结点的前一个结点
        ListNode nodePairPrev = headNode;
        //待交换的一对结点的后一个结点
        ListNode nodePairPost;

        //当交换完一对结点后，若还有后续结点，则继续循环
        while (nodePair_1 != null) {
            //若还剩下至少两个结点，则继续操作，否则退出循环
            if (nodePair_1.next != null) {
                nodePair_2 = nodePair_1.next;
            } else {
                break;
            }

            nodePairPost = nodePair_2.next;//记录当前两个待交换结点的下一个结点

            //执行交换操作
            nodePairPrev.next = nodePair_2;
            nodePair_2.next = nodePair_1;
            nodePair_1.next = nodePairPost;

            //更新引用的值，将其向后移
            nodePairPrev = nodePair_1;
            nodePair_1 = nodePairPost;
        }
        return headNode.next;
    }

    public ListNode swapPairs_recursive(ListNode head) {
        //若没有结点可交换或只剩一个结点，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        //待交换的两个结点
        ListNode nodePair_1 = head;
        ListNode nodePair_2 = head.next;
        //待交换的两个结点的下一个
        ListNode nodePairPost = head.next.next;

        //交换
        nodePair_2.next = nodePair_1;
        nodePair_1.next = swapPairs_recursive(nodePairPost);

        return nodePair_2;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Solution solution = new Solution();
        head = solution.swapPairs_recursive(head);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
