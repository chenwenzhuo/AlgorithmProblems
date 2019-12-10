package com.hey_there.zuiduanlujing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public String getShortestPath(int iNodes, String strInitPath, String strStart, String strEnd) {
        int[][] graph = buildGraph(iNodes, strInitPath);////以邻接矩阵表示图

        //获得起点和终点的数字表示
        int start = strStart.charAt(0) - 'a';
        int end = strEnd.charAt(0) - 'a';

        StringBuilder[] shortestPaths = new StringBuilder[iNodes];//源点到各个点的最短路径
        for (int i = 0; i < iNodes; i++) {
            shortestPaths[i] = new StringBuilder();
            //将源点加入到路径中
            shortestPaths[i].append(strStart);
        }
        //源点到各个点的最短路径长度，key是结点编号，value是最短路径值
        Map<Integer, Integer> shortestPathsLen = new HashMap<>();
        shortestPathsLen.put(start, 0);//起点到自己的路径长度为0

        Set<Integer> solvedSet = new HashSet<>();//已找到最短路径的点
        Set<Integer> unsolvedSet = new HashSet<>();//未找到最短路径的点

        solvedSet.add(start);
        for (int i = 0; i < iNodes; i++) {
            if (i == start) {
                continue;
            }
            unsolvedSet.add(i);
        }


        int newNode = start;
        while (solvedSet.size() < iNodes) {

            //寻找newNode能连接到的所有点，将其加入shortestPathsLen或更新它在shortestPathsLen中的路径值
            for (int i = 0; i < graph[newNode].length; i++) {
                if (graph[newNode][i] != 0) {//若newNode能连接到到结点i

                    if (!shortestPathsLen.containsKey(i)) {//若当前不知道到结点i的任何一条路径长度
                        //将结点i加入shortestPathsLen中，路径长度为源点到newNode的长度+newNode到结点i的长度
                        shortestPathsLen.put(i, shortestPathsLen.get(newNode) + graph[newNode][i]);

                        //记录源点到结点i的路径
                        shortestPaths[i].delete(0, shortestPaths[i].length());//首先清空旧路径
                        shortestPaths[i]
                                .append(shortestPaths[newNode].toString())
                                .append((char) ('a' + i));
                    } else {
                        //若当前已知一条到结点i的路径，比较已知路径和经过newNode到i的路径长度，取小者
                        if (shortestPathsLen.get(newNode) + graph[newNode][i] < shortestPathsLen.get(i)) {
                            shortestPathsLen.put(i, shortestPathsLen.get(newNode) + graph[newNode][i]);

                            //更新源点到结点i的路径
                            shortestPaths[i].delete(0, shortestPaths[i].length());//首先清空旧路径
                            shortestPaths[i]
                                    .append(shortestPaths[newNode].toString())
                                    .append((char) ('a' + i));//记录新路径
                        }
                    }
                }
            }

            //从unsolvedSet中找出已知路径的点，从中找出路径最短的点加入solvedSet
            int unsolvedNodeWithShortestPath = -1;
            int shortestUnsolvedPath = Integer.MAX_VALUE;
            for (int unsolved : unsolvedSet) {
                if (shortestPathsLen.containsKey(unsolved)
                        && shortestPathsLen.get(unsolved) < shortestUnsolvedPath) {
                    unsolvedNodeWithShortestPath = unsolved;
                    shortestUnsolvedPath = shortestPathsLen.get(unsolved);
                }
            }

            //将上面找到的点从未解决集合中移除，加入已解决集合中
            unsolvedSet.remove(unsolvedNodeWithShortestPath);
            solvedSet.add(unsolvedNodeWithShortestPath);
            newNode = unsolvedNodeWithShortestPath;
        }

        System.out.println("到终点的最短路径长度：" + shortestPathsLen.get(end));


        return shortestPaths[end].toString();
    }

    public int[][] buildGraph(int iNodes, String strInitPath) {
        int[][] graph = new int[iNodes][iNodes];//以邻接矩阵表示图

        //以逗号 , 分割输入字符串，获得形如 a->b:4 的边及边长
        String[] edgesAndWeights = strInitPath.split(",");
        int edgesCount = edgesAndWeights.length;//获取图中边的数量

        String[] edges = new String[edgesCount];//用数组存放图中所有的边
        int[] weights = new int[edgesCount];//用数组存放图中所有边的权重

        for (int i = 0; i < edgesCount; i++) {
            //以冒号 : 分割，oneEdgeAndWeight[0]是形如 a->b 的边，oneEdgeAndWeight[1]是边长4
            String[] oneEdgeAndWeight = edgesAndWeights[i].split(":");

            edges[i] = oneEdgeAndWeight[0];//记录下每条边
            weights[i] = Integer.parseInt(oneEdgeAndWeight[1]);//将边长转成数字，记录

            //获得边的起点和重点的 int 类型值
            int edgeStart = edges[i].charAt(0) - 'a';
            int edgeEnd = edges[i].charAt(edges[i].length() - 1) - 'a';

            //将边的长度记录在邻接矩阵中，由于是无向图，所以邻接矩阵是对称矩阵
            //不可达的点及各个点到自身的距离都以默认值0表示
            graph[edgeStart][edgeEnd] = weights[i];
            graph[edgeEnd][edgeStart] = weights[i];
        }

        return graph;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String strInitPath = "a->b:4,b->h:1,b->e:2,e->d:1,h->g:1,h->c:2,g->f:2,g->c:5,f->c:2,c->d:1";
        System.out.println(solution.getShortestPath(8, strInitPath, "a", "d"));
    }
}
