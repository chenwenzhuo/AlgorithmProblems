package com.hey_there.RemoveDuplicatesFromSortedList;

import com.hey_there.LinkedList.ListNode;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //给原链表一个引用，方便操作
        ListNode listRef = head;

        //遍历原链表
        while (listRef != null) {
            //向后遍历，判断listRef引用的当前结点值是否与后一个结点值相等
            //若相等，使listRef.next跳过此等值结点，引用其后的结点
            while (listRef.next != null && listRef.next.val == listRef.val) {
                listRef.next = listRef.next.next;
            }
            listRef = listRef.next;//去掉重复值的结点后，listRef指向下一个值的结点
        }
        return head;
    }
}
