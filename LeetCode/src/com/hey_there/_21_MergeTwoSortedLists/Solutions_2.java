package com.hey_there._21_MergeTwoSortedLists;

public class Solutions_2 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);//为合并后的链表定义一个哑节点作为头节点
        ListNode tailMerged = dummy;//一个引用指向合并后链表的尾节点
        ListNode ref1 = list1, ref2 = list2;//两个引用分别指向两个链表的待合并节点

        //当两个链表都未遍历完时，执行循环
        while (ref1 != null && ref2 != null) {
            //选择两当前节点中值较小的一个，合并到新链表
            if (ref1.val < ref2.val) {
                tailMerged.next = ref1;
                ref1 = ref1.next;
            } else {
                tailMerged.next = ref2;
                ref2 = ref2.next;
            }
            tailMerged = tailMerged.next;
        }
        //当两个链表之一遍历完时，将未遍历完当一个的剩余部分加入合并后链表
        if (ref1 != null) tailMerged.next = ref1;
        if (ref2 != null) tailMerged.next = ref2;
        return dummy.next;
    }
}
