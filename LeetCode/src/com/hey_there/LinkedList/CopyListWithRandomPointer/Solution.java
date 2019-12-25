package com.hey_there.LinkedList.CopyListWithRandomPointer;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public Node copyRandomList(Node head) {
        Map<Integer, Node> nodeToRandom = new HashMap<>();
        Map<Integer, Node> copiedNodes = new HashMap<>();//已经被复制的结点，结点值和结点引用的映射

        Node copiedListHead = new Node(0);
        Node copiedListRef = copiedListHead;

        while (head != null) {
            //复制当前结点
            Node nodeCopy = new Node(head.val);
            copiedListRef.next = nodeCopy;
            //将复制出的结点存入map中
            copiedNodes.put(nodeCopy.val, nodeCopy);

            //检查是否有结点的random指向当前复制出的结点
            if (nodeToRandom.containsKey(nodeCopy.val)) {
                nodeToRandom.get(nodeCopy.val).random = nodeCopy;
                nodeToRandom.remove(nodeCopy.val);
            }

            //此if判断中，random可能为null，产生空指针异常
            if (copiedNodes.containsKey(head.random.val)) {
                //若当前head结点的random指向的结点已被复制，直接给nodeCopy的random赋值
                nodeCopy.random = copiedNodes.get(head.random.val);
            } else {
                //若当前head结点的random指向的结点还未被复制，将此random结点的val和当前head结点的映射存入map
                nodeToRandom.put(head.random.val, head);
            }

            head = head.next;
            copiedListRef = copiedListRef.next;
        }

        return copiedListHead.next;
    }
}
