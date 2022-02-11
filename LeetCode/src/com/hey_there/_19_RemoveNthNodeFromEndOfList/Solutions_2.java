package com.hey_there._19_RemoveNthNodeFromEndOfList;

public class Solutions_2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //定义一个额外的哑节点，其next引用指向链表第一个节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode ref = dummy;

        ListNode slow = head, fast = head;//定义双指针，指向链表第一个节点
        //将fast向后移动n-1次
        while (n > 1 && fast != null) {
            fast = fast.next;
            n--;
        }
        //若fast为null，说明链表长度小于n，没有倒数第n个节点，返回null
        if (fast == null) return null;
        //fast不为null，说明链表长度大于n，存在倒数第n个节点
        //将slow、fast和ref以同样速度后移，直到fast指向链表最后一个节点
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            ref = ref.next;
        }
        //此时slow指向的节点就是倒数第n个节点，ref指向slow的前驱节点
        ref.next = slow.next;//从链表中删除slow指向的节点
        return dummy.next;
    }
}
