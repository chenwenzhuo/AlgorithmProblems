package com.hey_there.AddTwoNumbers_2;

import java.util.Stack;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //检查是否有加数为0
        if (l1.val == 0) {
            return l2;
        }
        if (l2.val == 0) {
            return l1;
        }
        //将两个数逐位压入栈中
        Stack<Integer> num1Stack = new Stack<>();
        Stack<Integer> num2Stack = new Stack<>();
        ListNode traveller = l1;
        while (traveller != null) {
            num1Stack.push(traveller.val);
            traveller = traveller.next;
        }
        traveller = l2;
        while (traveller != null) {
            num2Stack.push(traveller.val);
            traveller = traveller.next;
        }

        ListNode ans = null;
        int carry = 0;//进位
        //弹栈并相加
        while (!num1Stack.empty() || !num2Stack.empty() || carry != 0) {
            //每次各弹出一位相加
            int digit1 = num1Stack.empty() ? 0 : num1Stack.pop();
            int digit2 = num2Stack.empty() ? 0 : num2Stack.pop();

            int digitSum = digit1 + digit2 + carry;
            carry = digitSum / 10;

            ListNode node;
            if (carry == 0) {
                node = new ListNode(digitSum);
            } else {
                node = new ListNode(digitSum % 10);
            }
            node.next = ans;
            ans = node;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(6);
        n1.next = new ListNode(7);
        n1.next.next = new ListNode(8);
        n1.next.next.next = new ListNode(9);

        ListNode n2 = new ListNode(5);
        n2.next = new ListNode(6);
        n2.next.next = new ListNode(7);

        Solution solution = new Solution();
        ListNode ans = solution.addTwoNumbers(n1, n2);
        while (ans != null) {
            System.out.print(ans.val);
            ans = ans.next;
        }
        System.out.println();
    }
}
