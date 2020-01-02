package com.hey_there.LinkedList.CopyListWithRandomPointer;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> copiedNodes = new HashMap<>();//已经被复制的结点，旧结点和新结点间的映射

        Node copiedListHead = new Node(0);
        Node copiedListRef = copiedListHead;

        //遍历旧链表
        while (head != null) {
            Node curNodeCopy;//当前head指向的结点的拷贝

            //检查旧链表中当前head结点是否已复制过，若已复制过则直接从map中取出使用，否则创建新结点
            if (copiedNodes.containsKey(head)) {
                curNodeCopy = copiedNodes.get(head);//取出结点
                copiedListRef.next = curNodeCopy;//加到新链表上
            } else {
                //复制当前结点
                curNodeCopy = new Node(head.val);
                copiedListRef.next = curNodeCopy;
                //将复制出的结点存入map中
                copiedNodes.put(head, curNodeCopy);
            }

            //检查当前head结点的random指向的结点是否已被复制
            //首先检查当前head结点的random是否为null，
            //若当前head结点的random为null，复制出的结点的random也应为null
            //若当前head结点的random不为null，则检查random结点是否已复制过
            if (head.random != null) {
                if (copiedNodes.containsKey(head.random)) {
                    //若random结点已复制过，直接从map中取出赋值
                    curNodeCopy.random = copiedNodes.get(head.random);
                } else {
                    //若random结点还未被复制，则复制此random结点
                    Node randomCopy = new Node(head.random.val);
                    curNodeCopy.random = randomCopy;

                    //将此random结点存入map中
                    copiedNodes.put(head.random, randomCopy);
                }
            }
            head = head.next;
            copiedListRef = copiedListRef.next;
        }
        return copiedListHead.next;
    }

    public static void main(String[] args) {
        //初始化链表
        //题目描述中的用例
        /*Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);
        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;*/
        //存在结点值相同的用例
        /*Node head = new Node(3);
        head.next = new Node(3);
        head.next.next = new Node(3);
        head.random = null;
        head.next.random = head;
        head.next.next.random = null;*/
        //有两个结点的random指向同一个结点的用例
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.random = head.next.next;
        head.next.random = head.next.next;
        head.next.next.random = null;
        head.next.next.next.random = null;

        Solution solution = new Solution();
        head = solution.copyRandomList(head);

        while (head != null) {
            System.out.print("current node: " + head.val);
            if (head.random != null) {
                System.out.println(", current node random: " + head.random.val);
            } else {
                System.out.println(", current node random: " + head.random);
            }

            head = head.next;
        }
    }
}
