package com.hey_there._1971_FindIfPathExistsInGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Solution {
    //广度优先遍历
    public boolean validPath_bfs(int n, int[][] edges, int source, int destination) {
        //nodes中存储每个节点i能到达的节点
        ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            nodes.add(list);
        }
        //根据边edges，将连通的顶点信息记录到nodes中
        for (int[] edge : edges) {
            nodes.get(edge[0]).add(edge[1]);
            nodes.get(edge[1]).add(edge[0]);
        }
        //广度优先遍历
        boolean[] visited = new boolean[n];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);//起始顶点入队
        visited[source] = true;//起始顶点标记为已访问
        while (!queue.isEmpty()) {
            int node = queue.poll();//从队列中获得一个顶点
            ArrayList<Integer> canReach = nodes.get(node);//从顶点node出发，能直接到达的顶点
            for (int reach : canReach) {
                if (reach == destination)
                    return true;//找到destination，直接返回true
                //将与node直接连通的未访问顶点入队，标记为已访问
                if (!visited[reach]) {
                    queue.offer(reach);
                    visited[reach] = true;
                }
            }
        }
        return visited[destination];
    }

    //深度优先遍历
    public boolean validPath_dfs(int n, int[][] edges, int source, int destination) {
        //nodes中存储每个节点i能到达的节点
        ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            nodes.add(list);
        }
        //根据边edges，将连通的顶点信息记录到nodes中
        for (int[] edge : edges) {
            nodes.get(edge[0]).add(edge[1]);
            nodes.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        //深度优先遍历
        return dfs(nodes, visited, source, destination);
    }

    private boolean dfs(ArrayList<ArrayList<Integer>> nodes, boolean[] visited, int source, int destination) {
        if (source == destination)
            return true;
        visited[source] = true;
        ArrayList<Integer> canReach = nodes.get(source);//起始顶点source直接相连的顶点
        for (int reach : canReach) {//遍历所有能直接到达的顶点
            //若一个顶点未访问过，且可从此顶点可到达destination
            if (!visited[reach] && dfs(nodes, visited, reach, destination))
                return true;
        }
        return false;
    }
}
