package com.hey_there.RemoveNthNodeFromEndOfList;

public class Solutions {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode[] lastNodesFromEnd = new ListNode[n];//倒数前n个结点
        ListNode pre_NthFromEnd = null;//倒数第n个结点的前驱
        ListNode nodeRef = head;//此引用用来遍历链表

        //初始化结点数组，先将前n个结点存入数组
        for (int i = 0; i < n; i++) {
            lastNodesFromEnd[i] = nodeRef;
            nodeRef = nodeRef.next;
        }

        //若nodeRef为null，则链表长度为n，要移除的是第一个结点
        if (null == nodeRef) {
            return head.next;
        }

        //执行到这里，说明链表长度大于n
        //后移链表中存储的结点，直到链表尾
        while (null != nodeRef) {
            pre_NthFromEnd = lastNodesFromEnd[0];
            moveNodesForward(lastNodesFromEnd);
            lastNodesFromEnd[n - 1] = nodeRef;
            nodeRef = nodeRef.next;
        }

        pre_NthFromEnd.next = lastNodesFromEnd[0].next;

        return head;
    }

    /**
     * 接收一个ListNode类型的数组，
     * 将后n-1个元素的值前移一位
     *
     * @param lastNodesFromEnd 待处理的数组
     */
    private void moveNodesForward(ListNode[] lastNodesFromEnd) {
        int len = lastNodesFromEnd.length;
        for (int i = 0; i < len - 1; i++) {
            lastNodesFromEnd[i] = lastNodesFromEnd[i + 1];
        }
    }
}
