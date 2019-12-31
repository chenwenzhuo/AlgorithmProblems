package com.hey_there.LinkedList.CopyListWithRandomPointer;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public Node copyRandomList(Node head) {
        Map<Integer, Node> randomValAndNodeMap = new HashMap<>();//结点的random结点值和此结点的映射
        Map<Integer, Node> copiedNodes = new HashMap<>();//已经被复制的结点，结点值和结点引用的映射

        Node copiedListHead = new Node(0);
        Node copiedListRef = copiedListHead;

        while (head != null) {
            //复制当前结点
            Node curNodeCopy = new Node(head.val);
            copiedListRef.next = curNodeCopy;
            //将复制出的结点存入map中
            copiedNodes.put(curNodeCopy.val, curNodeCopy);

            //检查是否有结点的random指向此次复制出的结点
            if (randomValAndNodeMap.containsKey(curNodeCopy.val)) {
                //在head指向值为1的结点时，此句应让值为11的结点的random指向值为1的结点，但未生效
                randomValAndNodeMap.get(curNodeCopy.val).random = curNodeCopy;
                randomValAndNodeMap.remove(curNodeCopy.val);
            }

            //检查此次复制出的结点的random指向的结点是否已被复制
            //首先检查此次被复制的结点的random是否为null，
            //若被复制结点的random为null，复制出的结点的random也应为null
            //若被复制结点的random不为null，则为其赋值或保存此random结点的val和当前head结点的映射
            if (head.random != null) {
                if (copiedNodes.containsKey(head.random.val)) {
                    //若当前head结点的random指向的结点已被复制，直接给nodeCopy的random赋值
                    curNodeCopy.random = copiedNodes.get(head.random.val);
                } else {
                    //若当前head结点的random指向的结点还未被复制，将此random结点的val和当前head结点的映射存入map
                    randomValAndNodeMap.put(head.random.val, head);
                }
            }
            head = head.next;
            copiedListRef = copiedListRef.next;
        }
        return copiedListHead.next;
    }

    public static void main(String[] args) {
        //初始化链表
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);

        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;

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
