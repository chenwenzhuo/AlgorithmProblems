package com.hey_there._116_PopulatingNextRightPointersInEachNode;

public class Solutions_2 {
    public Node connect(Node root) {
        if (root == null) return null;
        connectTwoNode(root.left, root.right);
        return root;
    }

    private void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) return;
        //将node1的next引用指向node2
        node1.next = node2;
        connectTwoNode(node1.left, node1.right);//对node1的左右子树内部进行连接
        connectTwoNode(node2.left, node2.right);//对node2的左右子树内部进行连接
        //将node1子树的右侧节点连接到node2子树的左侧节点
        connectTwoNode(node1.right, node2.left);
    }
}
