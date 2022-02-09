package com.hey_there._142_LinkedListCycle_2;

public class Solutions {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;//定义快慢指针，指向链表第一个节点
        while (fast != null && fast.next != null) {
            slow = slow.next;//slow每次走一步
            fast = fast.next.next;//fast每次走两步
            if (slow == fast) break;//若二者相遇，退出循环
        }
        //fast为null或fast.next为null，说明fast走到了链表尾部，链表无环
        if (fast == null || fast.next == null) return null;
        //链表有环，寻找环起点
        slow = head;//将slow重新指向链表第一个节点
        while (slow != fast) {
            //二者以相同速度前进
            slow = slow.next;
            fast = fast.next;
        }
        return slow;//相遇时指向的节点就是环起点
    }
}
