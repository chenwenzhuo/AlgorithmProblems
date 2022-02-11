package com.hey_there._160_IntersectionOfTwoLinkedLists;

public class Solutions {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;//二者任意一个为null时都没有交点
        }
        ListNode refA = headA, refB = headB;//refA和refB用来遍历链表
        while (refA != refB) {
            //将refA和refB依次后移，移到一个链表
            refA = refA != null ? refA.next : headB;
            refB = refB != null ? refB.next : headA;
        }
        //refA和refB值相等时，当前节点为相交节点
        //没有相交节点时refA，refB均为null
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

        Solutions solutions = new Solutions();
        ListNode intersection = solutions.getIntersectionNode(headA, headB);
        if (intersection != null) {
            System.out.println("intersection val: " + intersection.val);
        } else {
            System.out.println("intersection val: " + null);
        }
    }
}
