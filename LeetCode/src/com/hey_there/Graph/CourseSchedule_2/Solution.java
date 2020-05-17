package com.hey_there.Graph.CourseSchedule_2;

import java.util.*;

public class Solution {
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = -1;

    private boolean isPossible;
    private Map<Integer, Integer> nodeColor;
    private Map<Integer, List<Integer>> adjacencyTable;
    private List<Integer> topologicalOrder;

    public int[] findOrder_dfsBased(int numCourses, int[][] prerequisites) {
        init(numCourses, prerequisites);
        for (int node : adjacencyTable.keySet()) {
            dfs(node);
        }

        int[] order;
        if (isPossible) {
            order = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                order[i] = topologicalOrder.get(i);
            }
        } else {
            order = new int[0];
        }
        return order;
    }

    private void init(int numCourses, int[][] prerequisites) {
        this.isPossible = true;
        this.nodeColor = new HashMap<>();
        this.adjacencyTable = new HashMap<>();
        this.topologicalOrder = new ArrayList<>();

        //根据给定的边，构建邻接表
        for (int i = 0; i < numCourses; i++) {
            adjacencyTable.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            List<Integer> nextNodes = adjacencyTable.get(prerequisite[1]);
            nextNodes.add(prerequisite[0]);
            adjacencyTable.put(prerequisite[1], nextNodes);
        }

        if (prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                adjacencyTable.put(i, new ArrayList<>());
            }
        } else {
            for (int[] prerequisite : prerequisites) {
                if (!adjacencyTable.containsKey(prerequisite[0])) {
                    List<Integer> nodeList = new ArrayList<>();
                    //nodeList.add(prerequisite[0]);
                    adjacencyTable.put(prerequisite[0], nodeList);
                }
                if (adjacencyTable.containsKey(prerequisite[1])) {
                    adjacencyTable.get(prerequisite[1]).add(prerequisite[0]);
                } else {
                    List<Integer> nextNodes = new ArrayList<>();
                    //nextNodes.add(prerequisite[1]);
                    nextNodes.add(prerequisite[0]);
                    adjacencyTable.put(prerequisite[1], nextNodes);
                }
            }
        }

        //将所有结点的颜色初始化为白色
        for (int i = 0; i < numCourses; i++) {
            nodeColor.put(i, WHITE);
        }
    }

    private void dfs(int node) {
        if (!this.isPossible) {
            return;
        }

        if (nodeColor.get(node) == BLACK) {
            return;
        }
        if (nodeColor.get(node) == GRAY) {
            this.isPossible = false;
            return;
        }

        nodeColor.put(node, GRAY);//将当前结点颜色设为GRAY

        List<Integer> nextNodesOfCurrentNode = adjacencyTable.get(node);//获取当前结点的所有直接后继
        if (nextNodesOfCurrentNode.size() == 0) {
            //若当前结点的后继结点集合大小为0，说明其没有后继结点
            nodeColor.put(node, BLACK);//将当前结点置为黑色
            topologicalOrder.add(0, node);//将当前结点插入拓扑排序队首
        } else {
            //遍历当前结点的所有后继
            for (int nextNode : nextNodesOfCurrentNode) {
                dfs(nextNode);
            }
            //遍历完后继，检查是否出现环
            if (!this.isPossible) {
                return;//有环直接返回
            }
            //若无环：
            nodeColor.put(node, BLACK);//将当前结点置为黑色
            topologicalOrder.add(0, node);//将当前结点插入拓扑排序队首
        }
    }

    //改造CourseSchedule_1中的拓扑排序解法
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] topologicalOrder = new int[numCourses];//图的拓扑排序序列
        int[] inDegrees = new int[numCourses];//用数组存储所有结点的入度
        //统计各结点的入度
        for (int[] pre : prerequisites) {
            inDegrees[pre[0]]++;
        }
        //用队列存储所有入度为0的结点
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        //将入度为0的结点入队
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int index = 0;
        //每次获取一个0入度的结点，从图中去掉它，并更新其他结点的入度值
        while (!queue.isEmpty()) {
            int ZIDNode = queue.poll();//获取一个0入度的结点
            numCourses--;//将其从图中去掉
            //将其加入topologicalOrder
            topologicalOrder[index] = ZIDNode;
            index++;
            //更新其他结点的入度
            for (int[] pre : prerequisites) {//遍历图中所有边
                if (pre[1] != ZIDNode) {
                    continue;//当前边的前驱结点不是ZIDNode，不做操作，继续遍历
                }
                //若当前边的前驱结点是ZIDNode
                //先将后继结点的入度减1，再判断减1后是否为0
                if (--inDegrees[pre[0]] == 0) {
                    queue.offer(pre[0]);//若减1后为0，将此结点加入0入度结点的队列
                }
            }
        }
        if (numCourses == 0) {
            //图中没有剩余节点，则返回topologicalOrder数组
            return topologicalOrder;
        } else {
            //有剩余节点则返回一个空数组
            return new int[0];
        }
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}, {2, 0}};

        Solution solution = new Solution();
        int[] result = solution.findOrder_dfsBased(3, prerequisites);
        for (int n : result) {
            System.out.print(n + "   ");
        }
        System.out.println();
    }
}
