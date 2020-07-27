package com.hey_there.InterviewProblem_22_kthNodeFromEndOfList;

import java.util.ArrayDeque;

public class Solution {
    public ListNode getKthFromEnd_1(ListNode head, int k) {
        ListNode ref = head;
        //创建一个队列，向队列中加入k个节点
        ArrayDeque<ListNode> queue = new ArrayDeque<>();
        while (queue.size() < k) {
            queue.offer(ref);
            ref = ref.next;
        }
        while (ref != null) {
            //每次向队列中加入一个节点再移除一个节点，
            //使队列中的节点数不变
            queue.offer(ref);
            queue.poll();
            ref = ref.next;
        }
        return queue.poll();
    }

    public ListNode getKthFromEnd_2(ListNode head, int k) {
        //使用一个引用指向链表的第k个节点
        ListNode front = head;
        int count = 1;
        while (count < k) {
            front = front.next;
            count++;
        }
        //使用一个引用指向链表的第1个节点
        ListNode behind = head;
        //同时移动两个引用，直到front指向最后一个节点
        while (front.next != null) {
            front = front.next;
            behind = behind.next;
        }
        return behind;
    }
}
