package com.hey_there.InsersionSortList;

import com.hey_there.LinkedList.ListNode;

public class Solution {
    //两层嵌套循环算法
    public ListNode insertionSortList_nestedLoop(ListNode head) {
        //给原链表加上头结点，方便操作
        ListNode headNode = new ListNode(Integer.MIN_VALUE);
        headNode.next = head;

        ListNode prevNode = headNode;//head指向的结点的前一个结点的引用，初始指向头结点
        int prevNodeVal = headNode.val;//head指向的结点的前一个结点的值，初始为头结点的值
        //遍历链表
        while (head != null) {
            int curNodeVal = head.val;//当前结点的值
            //若当前结点值大于等于前一结点的值，则不进行排序
            if (curNodeVal >= prevNodeVal) {
                prevNode = head;//更新prevNode的指向
                prevNodeVal = head.val;//更新prevNodeVal的值
                head = head.next;//head指向下一结点
                continue;
            }

            ListNode prevListRef = headNode;
            while (prevListRef != head) {//从头遍历链表，寻找插入位置
                //若找到插入位置
                if (prevListRef.val <= curNodeVal && prevListRef.next.val >= curNodeVal) {
                    //将当前结点从后方链表上取下
                    prevNode.next = head.next;

                    //将当前结点插入正确位置
                    head.next = prevListRef.next;
                    prevListRef.next = head;
                    break;
                }
                prevListRef = prevListRef.next;
            }
            head = prevNode.next;//完成插入后更新head的值
        }
        return headNode.next;
    }



    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        Solution solution = new Solution();
        head = solution.insertionSortList_nestedLoop(head);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
