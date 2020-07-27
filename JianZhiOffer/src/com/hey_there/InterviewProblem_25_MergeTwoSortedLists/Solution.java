package com.hey_there.InterviewProblem_25_MergeTwoSortedLists;

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode mergedHead = new ListNode(-1);
        ListNode mergedTail = mergedHead;
        //从l1和l2中选取值较小的节点，插入新链表中，直到其中之一耗尽
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                mergedTail.next = l1;
                l1 = l1.next;
            } else {
                mergedTail = l2;
                l2 = l2.next;
            }
            mergedTail = mergedTail.next;
        }
        //将未耗尽的链表插入到新链表尾部
        //以下两句仅会执行其中之一
        if (l1 != null) mergedTail = l1;
        if (l2 != null) mergedTail = l2;
        return mergedHead.next;
    }
}
