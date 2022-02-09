package com.hey_there._23_MergeKSortedLists;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solutions_2 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);//定义一个哑节点作为新链表的头节点
        ListNode tailMerged = dummy;//一个引用指向新链表的尾节点

        //定义一个优先队列（小顶堆），将值最小的节点放在堆顶
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        });
        //将所有链表的第一个节点放入小顶堆中
        for (ListNode l : lists)
            if (l != null) minHeap.offer(l);
        //当小顶堆不为空时，执行循环
        while (!minHeap.isEmpty()) {
            //从小顶堆中取出值最小的节点，加入新链表
            ListNode minNode = minHeap.poll();
            tailMerged.next = minNode;
            tailMerged = tailMerged.next;

            //若取出的最小节点不是此链表的尾节点，则将其后继节点加入小顶堆
            if (minNode.next != null)
                minHeap.offer(minNode.next);
        }
        return dummy.next;
    }
}
