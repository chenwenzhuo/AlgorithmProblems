package com.hey_there._234_PalindromeLinkedList;

//通过递归模拟双指针法，从两端向中间遍历链表
public class Solutions_2 {
    private ListNode left;

    /* traverse()方法递归向后遍历链表，相当于将链表节点逐个压入栈中，
     * left引用指向栈底，right指向栈顶，二者在链表中的位置相对于链表中间节点对称，
     * left通过left = left.next向链表中间节点移动，
     * right通过回到上一层递归向链表中间节点移动，
     * 故二者位置始终对称，比较二者的节点值可判断是否回文。*/
    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    private boolean traverse(ListNode right) {
        if (right == null) return true;
        //首先将链表所有节点压入递归栈，right节点指向链表末尾
        boolean res = traverse(right.next);//即判断末尾节点与头部节点是否构成回文
        //更深层递归中链表为回文，且当前left，right节点值相等，表示当前部分链表为回文
        res = res && (left.val == right.val);
        left = left.next;//left向后移动
        return res;
    }
}
