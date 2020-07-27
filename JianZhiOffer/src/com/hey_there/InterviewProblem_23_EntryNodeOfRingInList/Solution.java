package com.hey_there.InterviewProblem_23_EntryNodeOfRingInList;

public class Solution {
    public ListNode findRingEntry(ListNode head) {
        if (head == null) {
            return null;
        }
        //先使用快慢指针法判断链表是否有环
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        //若fast或fast.next为null则表示链表没有环
        if (fast == null || fast.next == null) {
            return null;
        }
        /* fast和fast.next均不为null则表示链表有环
         * 计算环中的节点数量*/
        fast = fast.next;
        int ringLen = 1;//环的长度
        while (fast != slow) {
            fast = fast.next;
            ringLen++;
        }
        //将fast指向链表头，再向后移动ringLen个节点
        fast = head;
        int count = 0;
        while (count < ringLen) {
            fast = fast.next;
            count++;
        }
        //将slow指向链表头，同时移动slow和fast直到二者相等
        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
