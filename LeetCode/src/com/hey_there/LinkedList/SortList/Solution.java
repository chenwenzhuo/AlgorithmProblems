package com.hey_there.LinkedList.SortList;

import com.hey_there.LinkedList.ListNode;

public class Solution {
    //非递归的归并排序
    public ListNode sortList_nonrecursiveMergeSort(ListNode head) {
        //计算链表长度
        int listLen = 0;
        ListNode traveler = head;
        while (traveler != null) {
            listLen++;
            traveler = traveler.next;
        }

        //加上头结点
        ListNode headNode = new ListNode(0);
        headNode.next = head;

        int batch = 1;

        while (batch <= listLen) {
            ListNode prevFirstBatch = headNode;//当前正被操作的两个batch中，第一个batch的前一个结点,初始为头结点
            ListNode postSecondBatch;//当前正被操作的两个batch中，第二个batch的后一个结点

            batch *= 2;
        }
        return null;
    }

    //递归的归并排序
    public ListNode sortList_recursiveMergeSort(ListNode head) {
        if (head == null || head.next == null) {//链表长度为0或1时无需排序
            return head;
        }

        if (head.next.next == null) {//当链表长度为2时，直接比较排序
            if (head.val > head.next.val) {//当第一个结点值大于第二个结点值时，进行交换
                ListNode post = head.next;//保存第二个结点的引用
                head.next = null;//断开链表
                post.next = head;//重新连接
                return post;
            }
            return head;////当第一个结点值小于等于第二个结点值时，直接返回
        }

        ListNode midNode = findMid(head);//获得链表中间结点
        ListNode rightHalf = midNode.next;//保存后半部分链表
        midNode.next = null;//断开前后两部分

        ListNode leftHalf = sortList_recursiveMergeSort(head);//对前半部分排序
        rightHalf = sortList_recursiveMergeSort(rightHalf);//对后半部分排序

        head = mergeLists(leftHalf, rightHalf);//合并两链表
        return head;
    }

    private ListNode mergeLists(ListNode l1, ListNode l2) {
        //创建一个头结点，将两个链表合并到头结点之后
        ListNode mergedList = new ListNode(0);
        ListNode traveler = mergedList;

        while (l1 != null || l2 != null) {
            //若其中一个遍历完，直接将另一个接到新链表上，退出循环
            if (l1 == null) {
                traveler.next = l2;
                break;
            } else if (l2 == null) {
                traveler.next = l1;
                break;
            }

            //若两个链表都未遍历完，则找出两个当前结点中值较小的一个接到新链表上
            traveler.next = l1.val < l2.val ? l1 : l2;//找出值较小的结点并接到新链表上
            //更新引用值
            if (traveler.next == l1) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
            traveler = traveler.next;
        }
        return mergedList.next;
    }

    private ListNode findMid(ListNode head) {
        //快慢指针法获得链表中间结点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(2);
        //head.next.next.next.next.next = new ListNode(6);
        //head.next.next.next.next.next.next = new ListNode(7);

        Solution solution = new Solution();
        head = solution.sortList_nonrecursiveMergeSort(head);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
