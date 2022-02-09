package com.hey_there._21_MergeTwoSortedLists;

public class Solutions_1 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //合并后的List,为其添加一个头结点以方便操作
        ListNode mergedList = new ListNode(-1);
        ListNode mergedListRef = mergedList;
        ListNode l1_Ref = l1;
        ListNode l2_Ref = l2;

        while (null != l1_Ref || null != l2_Ref) {
            //获取两链表中当前结点值，若当前链表引用为null，则赋值为Integer.MAX_VALUE
            int val_L1 = (null != l1_Ref) ? l1_Ref.val : Integer.MAX_VALUE;
            int val_L2 = (null != l2_Ref) ? l2_Ref.val : Integer.MAX_VALUE;

            //将二者中较小的一个加入新链表
            if (val_L1 < val_L2) {
                mergedListRef.next = new ListNode(val_L1);
                if (null != l1_Ref) {
                    l1_Ref = l1_Ref.next;
                }
            } else {
                mergedListRef.next = new ListNode(val_L2);
                if (null != l2_Ref) {
                    l2_Ref = l2_Ref.next;
                }
            }
            mergedListRef = mergedListRef.next;
        }
        return mergedList.next;
    }
}
