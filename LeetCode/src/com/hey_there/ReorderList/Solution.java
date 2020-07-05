package com.hey_there.ReorderList;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    //存储原链表并重新连接的算法
    public void reorderList_storeAndReconnect(ListNode head) {
        ListNode listRef = head;//listRef用来遍历链表
        List<ListNode> listNodes = new ArrayList<>();//list集合用来存储链表中结点
        while (listRef != null) {
            ListNode nextNode = listRef.next;//保存下一个结点引用
            listNodes.add(listRef);//将当前结点加入list集合
            listRef.next = null;//将当前结点的next设为null
            listRef = nextNode;//listRef指向下一个结点
        }//完成此while时listRef应为null

        listRef = new ListNode(0);//为方便操作，给listRef初始化为一个新结点
        int listLen = listNodes.size();//链表长度
        int left = 0, right = listLen - 1;//链表待拼接部分的最左和最右结点
        //重新拼接链表（前方已将所有结点的next设为null）
        while (left <= right) {
            listRef.next = listNodes.get(left);
            if (left == right) {
                break;//left与right相等时它们表示同一个结点，避免重复添加，直接break
            }
            listRef.next.next = listNodes.get(right);

            left++;
            right--;
            listRef = listRef.next.next;
        }
    }

    //将原链表按中心结点分为两个链表，反转右链表并将其插入左链表中
    public void reorderList_divideAndInsert(ListNode head) {
        if (head == null) {
            return;
        }
        //1. 使用快慢指针,找出链表的中心节点。
        // 1->2->3->4->5,中心节点为3
        ListNode middle = middleNode(head);

        //2. 将原始链表按照中心链表分割为两个链表，并将右链表反转
        //2.1 原始链表：1->2->3->4->5 左链表：1->2->3 右链表：4->5
        ListNode left = head;
        ListNode right = middle.next;
        middle.next = null;

        //2.2 反转右链表
        //原始右链表：4->5 反转后：5->4
        right = reverse(right);

        //3. 合并两个链表，将右链表插入到左链表
        //左链表：1->2->3 右链表：4->5 合并后：1->5->2->4->3
        merge(left, right);
    }

    //1. 使用快慢指针,找出链表的中心节点
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //2. 通过递归反转链表
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    //3. 合并两个链表，将右链表插入到左链表
    public void merge(ListNode left, ListNode right) {
        ListNode leftTemp;
        ListNode rightTemp;
        while (left.next != null && right != null) {
            //1. 保存next节点
            leftTemp = left.next;
            rightTemp = right.next;

            //2. 将右链表的第一个节点插入到左链表中
            // 左链表：1->2->3 右链表：5->4
            // 合并后的左链表：1->5->2->3
            left.next = right;
            right.next = leftTemp;

            //3. 移动left和right指针
            //左链表变为：2->3 右链表变为：4
            left = leftTemp;
            right = rightTemp;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Solution solution = new Solution();
        solution.reorderList_divideAndInsert(head);
        while (head != null) {
            System.out.println(head.val + "   ");
            head = head.next;
        }
    }
}
