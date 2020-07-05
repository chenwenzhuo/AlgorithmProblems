package com.hey_there.ReverseNodesInKGroup;

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        //方便操作，给翻转后的新链表添加一个头结点
        ListNode reversedHeadNode = new ListNode(-1);
        //翻转后的链表的最后一个结点
        ListNode reversedTail = null;
        //翻转后的链表的长度（包括头结点）
        int lenReversed = 1;

        //原链表结点的一个引用
        ListNode originalListRef = head;

        while (lenReversed < k + 1) {
            if (originalListRef == null) {
                /*若在循环退出以前，originalListRef已为null，
                 * 说明原链表中剩下的结点不足k个，
                 * 此时返回原链表*/
                return head;
            }

            //新建一个结点，结点值赋为originalListRef引用的结点值
            ListNode newNode = new ListNode(originalListRef.val);

            //将新结点插入翻转后的链表
            newNode.next = reversedHeadNode.next;
            reversedHeadNode.next = newNode;

            //更新链表长度和待插入的下一个结点
            lenReversed++;
            originalListRef = originalListRef.next;

            if (lenReversed == 2) {
                //若此次循环是循环的第一次，此次插入的结点就是新链表的尾结点
                reversedTail = newNode;
            }
        }
        //前面已翻转完成k个结点，下面递归翻转后续k个
        reversedTail.next = reverseKGroup(originalListRef, k);
        return reversedHeadNode.next;//head结点之后的结点翻转完成，返回翻转后的链表
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Solution solution = new Solution();
        head = solution.reverseKGroup(head, 2);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
