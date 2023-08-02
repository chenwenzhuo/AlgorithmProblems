package com.hey_there._1650_LowestCommonAncestorOfBinaryTree_3;

import java.util.HashSet;

public class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        //类似两个链表找相交节点，
        //从p、q分别出发，走到尽头时转向另一个节点继续
        Node pRef = p, qRef = q;
        while (pRef != qRef) {
            pRef = pRef.parent != null ? pRef.parent : q;
            qRef = qRef.parent != null ? qRef.parent : p;
        }
        return pRef;
    }
}
