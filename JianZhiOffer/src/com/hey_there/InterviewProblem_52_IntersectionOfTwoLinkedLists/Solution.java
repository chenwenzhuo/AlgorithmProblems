package com.hey_there.InterviewProblem_52_IntersectionOfTwoLinkedLists;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode refA = headA, refB = headB;
        //记录两个引用是否曾指向过另一链表的第一个节点
        boolean refAHasBeenB = false;
        boolean refBHasBeenA = false;
        while (refA != refB) {
            //当refA所指节点有后继节点时，将refA后移
            if (refA.next != null) refA = refA.next;
            else {//若refA所指节点没有后继节点
                refA = headB;//将refA指向链表B的第一个节点
                //检查refA是否是第一次指向链表B的第一个节点
                if (!refAHasBeenB)
                    refAHasBeenB = true;//refAHasBeenB为false，是第一次，将refAHasBeenB修改为true
                else return null;//refAHasBeenB为true，表示refA第二次指向headB，可确定两链表没有交点
            }
            if (refB.next != null) refB = refB.next;
            else {
                refB = headA;
                if (!refBHasBeenA) refBHasBeenA = true;
                else return null;
            }
        }
        return refA;
    }
}
