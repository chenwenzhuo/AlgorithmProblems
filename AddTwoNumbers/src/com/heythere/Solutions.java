package com.heythere;

public class Solutions {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        检查两链表是否为null，为null则抛出异常
         */
        if (null == l1 || null == l2) {
            throw new IllegalArgumentException("List cannot be null!");
        }

        /*
        按照要求，可以假设除了数字 0 之外，这两个数字都不会以零开头。
        所以若第一个节点为0，则可直接返回另一个
         */
        if (0 == l1.val) {
            return l2;
        }
        if (0 == l2.val) {
            return l1;
        }
        int carry = 0;
        int tempSum = 0;
        ListNode sumList = null;//要返回的结果
        ListNode tail = null;//链表尾指针

        //定义两个引用遍历两链表
        ListNode l1_search = l1;
        ListNode l2_search = l2;

        while (null != l1_search && null != l2_search) {
            tempSum = l1_search.val + l2_search.val + carry;
            carry = tempSum / 10;
            ListNode tempNode = new ListNode(tempSum % 10);

            if (null == sumList) {
                sumList = tempNode;
                tail = sumList;
                l1_search = l1_search.next;
                l2_search = l2_search.next;
                continue;
            }
            tail.next = tempNode;

            tail = tail.next;
            l1_search = l1_search.next;
            l2_search = l2_search.next;
        }
        if (null != l1_search) {
            tempSum = l1_search.val + carry;
            carry = tempSum / 10;
            l1_search.next.val += carry;
        }


        return null;
    }
}
