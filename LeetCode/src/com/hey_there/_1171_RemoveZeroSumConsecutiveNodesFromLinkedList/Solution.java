package com.hey_there._1171_RemoveZeroSumConsecutiveNodesFromLinkedList;

import java.util.HashMap;

public class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        //新增一个哑节点方便记录前驱
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //计算链表前缀和，用map记录不同前缀和对应的最后一个节点
        int prefixSum = 0;
        HashMap<Integer, ListNode> map = new HashMap<>();
        for (ListNode node = dummy; node != null; node = node.next) {
            prefixSum += node.val;
            map.put(prefixSum, node);
        }
        //重新计算前缀和
        //map中记录了一个前缀和最后一次出现的节点，遍历链表遇到此前缀和第一次出现的节点，
        //两节点中间的部分，和一定为0，可将其删除
        prefixSum = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            prefixSum += node.val;//计算前缀和
            node.next = map.get(prefixSum).next;//删除节点
        }
        return dummy.next;
    }
}
