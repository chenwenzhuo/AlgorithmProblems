package com.hey_there.RemoveDuplicateNode;

import java.util.HashSet;

public class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        HashSet<Integer> set = new HashSet<>();
        ListNode ref = head.next;//遍历链表的引用
        ListNode prev = head;//ref所指向的节点的前一节点
        set.add(head.val);
        while (ref != null) {
            if (set.contains(ref.val)) {
                //遇到重复值则从链表中移除当前节点
                prev.next = ref.next;
            } else {
                set.add(ref.val);//遇到未出现过的值则将其加入set集合
                prev = prev.next;
            }
            ref = ref.next;
        }
        return head;
    }
}
