package com.hey_there.IntersectionOfTwoLinkedLists;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;//二者任意一个为null时都没有交点
        }
        ListNode refA = headA, refB = headB;//refA和refB用来遍历链表
        ListNode tailA = null, tailB = null;//tailA和tailB用来记录两个链表的尾节点
        while (refA != refB) {
            if (refA.next != null) {
                refA = refA.next;//未走到链表尾部时继续向后
            } else {
                //走到链表尾部时
                tailA = refA;//记录下A链表的尾节点
                //两个链表的尾节点都已找到时，比较两尾节点是否相同
                if (tailB != null && tailA != tailB) {
                    return null;//两尾节点不同则链表不相交
                }
                refA = headB;
            }
            if (refB.next != null) {
                refB = refB.next;
            } else {
                tailB = refB;
                if (tailA != null && tailA != tailB) {
                    return null;
                }
                refB = headA;
            }
        }
        //refA和refB指向同一个节点时，此节点为交点
        return refA;
    }

    public static void main(String[] args) {
        ListNode headA = new ListNode(2);
        headA.next = new ListNode(6);
        headA.next.next = new ListNode(4);

        ListNode headB = new ListNode(1);
        headB.next = new ListNode(5);

        ListNode refA = headA;
        System.out.println("List A:");
        while (refA != null) {
            System.out.print(refA.val + "   ");
            refA = refA.next;
        }
        System.out.println();
        ListNode refB = headB;
        System.out.println("List B:");
        while (refB != null) {
            System.out.print(refB.val + "   ");
            refB = refB.next;
        }
        System.out.println();

        Solution solution = new Solution();
        ListNode intersection = solution.getIntersectionNode(headA, headB);
        if (intersection != null) {
            System.out.println("intersection val: " + intersection.val);
        } else {
            System.out.println("intersection val: " + null);

        }
    }
}
