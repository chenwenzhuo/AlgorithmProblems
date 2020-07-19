package com.hey_there.InterviewProblem_6_PrintListFromTail2Head;

import java.util.ArrayDeque;

public class Solution {
    //遍历链表，将节点值压入栈中，再从栈中弹出节点值
    public int[] reversePrint_1(ListNode head) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ListNode ref = head;
        while (ref != null) {
            stack.push(ref.val);
            ref = ref.next;
        }
        int[] reversed = new int[stack.size()];
        int idx = 0;
        while (!stack.isEmpty()) {
            reversed[idx++] = stack.pop();
        }
        return reversed;
    }

    //遍历链表，将结点值从尾到头存入数组
    public int[] reversePrint_2(ListNode head) {
        //计算链表长度
        int listLen = 0;
        ListNode ref = head;
        while (ref != null) {
            listLen++;
            ref = ref.next;
        }
        //将结点值存入数组
        int[] resArr = new int[listLen];
        ref = head;
        int idx = listLen - 1;
        while (ref != null) {
            resArr[idx--] = ref.val;
            ref = ref.next;
        }
        return resArr;
    }
}
