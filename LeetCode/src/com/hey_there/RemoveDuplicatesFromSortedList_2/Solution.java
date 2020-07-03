package com.hey_there.RemoveDuplicatesFromSortedList_2;

import com.hey_there.LinkedList.ListNode;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //处理特殊值
        //当链表长度为0,1时不会有重复值
        if (head == null || head.next == null) {
            return head;
        }

        //给原链表添加一个头结点方便操作
        ListNode headNode = new ListNode(-1);
        headNode.next = head;

        /*三个辅助引用
         * listRef指向上一个没有重复值的结点，
         * listRefPost指向listRef的后一个结点，
         * listRefPostPost初始指向listRef的后两个结点，
         * 通过比较listRefPost和listRefPostPost结点值是否相同，来决定是否跳过这些结点*/
        ListNode listRef = headNode;
        ListNode listRefPost = listRef.next;
        ListNode listRefPostPost = listRef.next.next;
        //isListRefPostRepeated用来标识listRefPost结点是否是有重复值的结点
        boolean isListRefPostRepeated = false;

        //遍历链表
        while (listRef != null) {
            /*循环判断listRefPost和listRefPostPost值是否相等，
             * 直到到达链表尾或找到一个与listRefPost值不相等的结点*/
            while (listRefPostPost != null &&
                    listRefPost.val == listRefPostPost.val) {
                listRefPostPost = listRefPostPost.next;
                isListRefPostRepeated = true;
            }

            if (isListRefPostRepeated) {//若listRefPost是一个有重复值的结点
                //跳过所有与listRefPost值相等的结点
                listRef.next = listRefPostPost;
                listRefPost = listRef.next;
                if (listRef.next != null) {
                    listRefPostPost = listRef.next.next;
                }
            } else {//若listRefPost是一个没有重复值的结点
                //将listRef后移，更新三个辅助引用的值
                listRef = listRef.next;
                if (listRef != null) {
                    listRefPost = listRef.next;
                }
                if (listRefPost != null) {
                    listRefPostPost = listRefPost.next;
                }
            }
            isListRefPostRepeated = false;//开始下一轮遍历前将isListRefPostRepeated重置为false
        }
        return headNode.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        Solution solution = new Solution();
        head = solution.deleteDuplicates(head);
        while (head != null) {
            System.out.print(head.val + "  ");
            head = head.next;
        }
        System.out.println();
    }
}
