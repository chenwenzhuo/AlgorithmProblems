package com.hey_there.RedundantConnection_2;

public class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        /* n个节点的树应有n-1条边，此题中多一条边，
         * 故节点数等于边数*/
        int numNodes = edges.length;//树的节点数

        int[] parent = new int[numNodes + 1];//parent[i]表示节点i的前驱节点，i>=1
        int[] ancestor = new int[numNodes + 1];//ancestor[i]表示节点i的祖先节点，i>=1
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;//初始化各个节点的前驱节点为其自身
            ancestor[i] = i;//初始化各个节点的祖先节点为其自身
        }

        int[] conflict = null;
        int[] cycle = null;
        for (int[] edge : edges) {
            //当前边的起止节点
            int start = edge[0];
            int end = edge[1];
            /* end节点的前驱不是其本身时，
             * 说明end节点有两个前驱，即parent[end]和start，
             * 说明当前边edge使树中出现环路，将edge标记为冲突边*/
            if (parent[end] != end) {
                conflict = edge;
            } else {
                //将end节点的前驱记为start
                parent[end] = start;

                //查找start和end节点的祖先
                int anc_start = find(ancestor, start);
                int anc_end = find(ancestor, end);
                //二者祖先相同，说明当前边edge使树中出现环路
                if (anc_start == anc_end) {
                    cycle = edge;
                } else {
                    //二者祖先不同时，进行合并
                    join(ancestor, start, end);
                }
            }
        }
        if (conflict == null) {
            return cycle;
        } else {
            if (cycle != null) {
                return new int[]{parent[conflict[1]], conflict[1]};
            } else {
                return new int[]{conflict[0], conflict[1]};
            }
        }
    }

    //查找节点son的祖先节点
    private int find(int[] ancestor, int son) {
        int s = son;
        while (ancestor[s] != s) s = ancestor[s];
        return s;
    }

    //将son1和son2两节点所在的分支进行合并
    private void join(int[] ancestor, int son1, int son2) {
        int anc1 = find(ancestor, son1);
        int anc2 = find(ancestor, son2);
        ancestor[anc1] = anc2;
    }
}
