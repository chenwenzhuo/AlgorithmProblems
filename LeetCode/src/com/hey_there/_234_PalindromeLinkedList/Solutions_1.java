package com.hey_there._234_PalindromeLinkedList;

public class Solutions_1 {
    public boolean isPalindrome(ListNode head) {
        StringBuilder sb = new StringBuilder();
        //反转链表
        ListNode dummy = new ListNode(-1, null);//哑节点辅助操作
        ListNode ref = head;
        while (ref != null) {
            sb.append(ref.val);//节点值加入StringBuilder，构建正向遍历结果
            ListNode nextNode = ref.next;//保存后继节点引用
            //将ref节点插入到dummy节点之后
            ref.next = dummy.next;
            dummy.next = ref;
            ref = nextNode;
        }
        String forward = sb.toString();//正向遍历结果
        sb.delete(0, sb.length());//清空StringBuilder
        //遍历反转后的链表
        ref = dummy.next;
        while (ref != null) {
            sb.append(ref.val);
            ref = ref.next;
        }
        String backward = sb.toString();
        return forward.equals(backward);
    }
}
