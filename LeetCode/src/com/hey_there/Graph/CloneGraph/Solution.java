package com.hey_there.Graph.CloneGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public Node cloneGraph(Node node) {
        //若为空图直接返回
        if (node == null) {
            return null;
        }

        HashMap<Integer, Node> clonedNodes = new HashMap<>();//克隆出的新结点与其结点值一起存在map中
        List<Node> oldGraphNodes = new ArrayList<>();//将旧图结点存在list中，方便操作

        oldGraphNodes.add(node);//将给定结点入队

        int oldGraphNodesSize = oldGraphNodes.size();
        for (int i = 0; i < oldGraphNodesSize; i++) {
            Node oldNode = oldGraphNodes.get(i);
            Node copiedNode;
            if (!clonedNodes.containsKey(oldNode.val)) {
                //若map中没有当前结点的拷贝，则创建一个新结点
                copiedNode = new Node();
            } else {
                //否则从map中取出此结点
                copiedNode = clonedNodes.get(oldNode.val);
                if (copiedNode.neighbors.size() > 0) {
                    //若此拷贝的邻居数量大于0，说明它是已克隆完成的结点
                    //则继续遍历图中下一个结点
                    continue;
                }
            }
            copiedNode.val = oldNode.val;
            copiedNode.neighbors = new ArrayList<>();
            clonedNodes.put(copiedNode.val, copiedNode);//将拷贝的结点存入map

            //复制当前结点的邻居
            for (Node oldNodeNeighbor : oldNode.neighbors) {
                if (!clonedNodes.containsKey(oldNodeNeighbor.val)) {
                    //若oldNode的当前邻居未被复制过，创建此邻居结点，加入oldNode的neighbors属性
                    Node copiedNeighbor = new Node();
                    copiedNeighbor.neighbors = new ArrayList<>();
                    copiedNode.neighbors.add(copiedNeighbor);

                    oldGraphNodes.add(oldNodeNeighbor);
                    clonedNodes.put(oldNodeNeighbor.val, copiedNeighbor);
                } else {
                    Node copiedNeighbor = clonedNodes.get(oldNodeNeighbor.val);
                    copiedNode.neighbors.add(copiedNeighbor);
                }
            }
            oldGraphNodesSize = oldGraphNodes.size();
        }

        return clonedNodes.get(node.val);
    }

    public static void main(String[] args) {
        //创建一个简单的图
        Node first = new Node();
        Node second = new Node();
        Node third = new Node();
        first.val = 1;
        first.neighbors = new ArrayList<>();
        first.neighbors.add(second);
        first.neighbors.add(third);
        second.val = 2;
        second.neighbors = new ArrayList<>();
        second.neighbors.add(first);
        second.neighbors.add(third);
        third.val = 3;
        third.neighbors = new ArrayList<>();
        third.neighbors.add(first);
        third.neighbors.add(second);

        Solution solution = new Solution();
        solution.cloneGraph(first);
    }
}
