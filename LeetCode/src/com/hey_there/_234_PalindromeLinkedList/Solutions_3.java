package com.hey_there._234_PalindromeLinkedList;

//通过快慢指针找到后半部分链表，反转后半部分，与前半部分比较
public class Solutions_3 {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        /* 若链表有偶数个节点，slow指向中间两个节点的后者，fast为null
         * 若有奇数个节点，slow指向中间节点，fast不为null，此时应将slow后移一位，指向后半部分链表*/
        if (fast != null) slow = slow.next;
        //反转slow链表
        slow = reverse_iterative(slow);//slow指向后半部分链表
        fast = head;//fast指向前半部分链表
        while (slow != null) {
            //发现节点值不相同，返回false
            if (fast.val != slow.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;//没有发现节点值不同，返回true
    }

    //递归反转链表
    private ListNode reverse_recursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode rev = reverse_recursive(head.next);//反转head节点之后的链表
        //此时head.next指向rev链表的尾节点
        head.next.next = head;
        head.next = null;
        return rev;
    }

    //迭代反转链表
    private ListNode reverse_iterative(ListNode head) {
        //额外头节点辅助操作
        ListNode dummy = new ListNode(-1, null);
        while (head != null) {
            ListNode nextNode = head.next;//保存后继节点引用
            //将head节点插入dummy节点之后
            head.next = dummy.next;
            dummy.next = head;
            head = nextNode;//插入完成，head指向后继节点
        }
        return dummy.next;
    }
}
