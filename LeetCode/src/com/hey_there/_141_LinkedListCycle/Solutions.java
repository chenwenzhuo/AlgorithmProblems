package com.hey_there._141_LinkedListCycle;

public class Solutions {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;//定义快慢指针指向链表第一个节点
        while (fast != null && fast.next != null) {
            slow = slow.next;//slow每次走一步
            fast = fast.next.next;//fast每次走两步
            if (slow == fast) break;//二者相等时退出循环
        }
        //fast为null或fast.next为null，说明fast走到了链表尾部，链表无环
        if (fast == null || fast.next == null) return false;
        return true;//否则链表有环
    }
}
