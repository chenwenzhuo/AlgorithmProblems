package com.hey_there.InterviewProblem_6_PrintListFromTail2Head;

import java.util.ArrayDeque;
import java.util.List;

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

    //遍历链表，将结点值存入数组，再反转数组
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
        int idx = 0;
        while (ref != null) {
            resArr[idx++] = ref.val;
            ref = ref.next;
        }
        //反转数组
        int left = 0, right = listLen - 1;
        while (left < right) {
            int tmp = resArr[left];
            resArr[left] = resArr[right];
            resArr[right] = tmp;
            left++;
            right--;
        }
        return resArr;
    }
}
