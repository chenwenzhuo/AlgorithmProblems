package com.hey_there.InterviewProblem_35_CopyComplicatedList;

import java.util.HashMap;

public class Solution {
    //map中存储旧节点到新节点之间的映射
    private HashMap<Node, Node> old2NewNode = new HashMap<>();

    public Node copyRandomList(Node head) {
        //为新链表创建一个头节点，方便操作
        Node newListHead = new Node(-1);
        copyList(head, newListHead);
        connectRandom(head, newListHead.next);
        return newListHead.next;
    }

    //复制链表，不连接random指针
    private void copyList(Node oldList, Node newListHead) {
        Node newListRef = newListHead;
        while (oldList != null) {
            //新建节点，复制旧节点的值
            newListRef.next = new Node(oldList.val);
            newListRef = newListRef.next;
            //存储旧节点到新节点的映射
            old2NewNode.put(oldList, newListRef);

            oldList = oldList.next;
        }
    }

    //连接新链表中的random指针
    private void connectRandom(Node oldList, Node newList) {
        while (oldList != null) {
            //当前旧节点的random指针不为null时，连接新节点中的random指针
            if (oldList.random != null) {
                newList.random = old2NewNode.get(oldList.random);
            }
            oldList = oldList.next;
            newList = newList.next;
        }
    }
}
