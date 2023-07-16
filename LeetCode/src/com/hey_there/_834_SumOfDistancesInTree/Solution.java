package com.hey_there._834_SumOfDistancesInTree;

import java.util.ArrayList;

public class Solution {
    private ArrayList<ArrayList<Integer>> allNeighbors;//用集合记录每个节点的邻居
    private int[] ans, sizes;//ans：结果数组，sizes[i]：以i为根的树的大小

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        //初始化neighbors集合
        allNeighbors = new ArrayList<>();
        for (int i = 0; i < n; i++)
            allNeighbors.add(new ArrayList<>());
        for (int[] edge : edges) {//存储每个节点的所有邻居
            allNeighbors.get(edge[0]).add(edge[1]);
            allNeighbors.get(edge[1]).add(edge[0]);
        }
        ans = new int[n];
        sizes = new int[n];
        dfs(0, -1, 0);
        reroot(0, -1);
        return ans;
    }

    //深度优先遍历，node：当前节点，parent：父节点，depth：当前节点深度
    private void dfs(int node, int parent, int depth) {
        ans[0] += depth;
        sizes[node] = 1;//子树的大小，初始化为1（仅包含根节点）
        //遍历node的所有邻居，并递归
        for (int neighbor : allNeighbors.get(node)) {
            if (neighbor != parent) {//避免访问父节点
                dfs(neighbor, node, depth + 1);
                //累加子树的大小，得到当前树的大小
                sizes[node] += sizes[neighbor];
            }
        }
    }

    private void reroot(int node, int parent) {
        ArrayList<Integer> neighbors = allNeighbors.get(node);
        for (int neighbor : neighbors) {
            if (neighbor != parent) {//避免访问父节点
                ans[neighbor] = ans[node] + allNeighbors.size() - 2 * sizes[neighbor];
                reroot(neighbor, node);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] edges = {{2, 0}, {1, 0}};
        int n = 3;
        int[] res = solution.sumOfDistancesInTree(n, edges);
        for (int r : res)
            System.out.print(r + " ");
    }
}
