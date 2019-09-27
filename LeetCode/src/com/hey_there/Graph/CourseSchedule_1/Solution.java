package com.hey_there.Graph.CourseSchedule_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    //拓扑排序思想
    public boolean canFinish_mine(int numCourses, int[][] prerequisites) {
        if (prerequisites.length <= 1) {
            return true;//课程之间总共不超过一条边，一定能完成
        }

        //prerequisites的第一列表示的是所有入度不为0的结点，第二列表示的是所有出度不为0的结点
        HashMap<Integer, Integer> postNodes_inDegree = new HashMap<>();//结点值与入度的映射
        HashMap<Integer, Integer> preNodes_outDegree = new HashMap<>();//结点值与出度的映射

        List<Integer> zeroInDegreeNodes = new ArrayList<>();//0入度的结点集合

        //遍历prerequisites数组，填充以上三个集合
        for (int[] prerequisite : prerequisites) {
            int postNode = prerequisite[0];//表示一个有入度的结点
            int preNode = prerequisite[1];//表示一个有出度的结点

            if (postNodes_inDegree.containsKey(postNode)) {
                //此结点值若已存在于map中，将入度加一
                int value = postNodes_inDegree.get(postNode);
                postNodes_inDegree.put(postNode, value + 1);
            } else {
                postNodes_inDegree.put(postNode, 1);//不存在于map中则将此结点值加入map
            }

            if (preNodes_outDegree.containsKey(preNode)) {
                int value = preNodes_outDegree.get(preNode);
                preNodes_outDegree.put(preNode, value + 1);
            } else {
                preNodes_outDegree.put(preNode, 1);
            }

            //若一个有出度的结点不在postNodes_inDegree中，说明其没有入度
            //若它也不在zeroInDegreeNodes中，将其加入zeroInDegreeNodes
            if (!postNodes_inDegree.containsKey(preNode) && !zeroInDegreeNodes.contains(preNode)) {
                zeroInDegreeNodes.add(preNode);
            }

            //若zeroInDegreeNodes中包含一个有入度的结点，则应将其移除
            if (zeroInDegreeNodes.contains(postNode)) {
                /*若不进行装箱，直接调用zeroInDegreeNodes.remove(postNode),
                 * 会删除zeroInDegreeNodes中下表为postNode的项，与目的不符*/
                Integer postNodeInteger = postNode;//为了删除zeroInDegreeNodes中值为postNode的项，必须对其进行装箱
                zeroInDegreeNodes.remove(postNodeInteger);
            }
        }

        //若图中没有0入度的结点，说明一定有环，无法完成
        if (zeroInDegreeNodes.isEmpty()) {
            return false;
        }

        //当图中还有0入度的结点时，就继续操作
        //每次获取一个0入度的结点，从图中去掉它，并更新其他结点的入度出度值
        while (!zeroInDegreeNodes.isEmpty()) {
            //获取一个0入度的结点
            int zeroInDegreeNode = zeroInDegreeNodes.get(0);

            //查询prerequisites，获取zeroInDegreeNode的后继结点，将其入度减一
            for (int[] nodePair : prerequisites) {
                if (nodePair[1] != zeroInDegreeNode) {
                    continue;
                }

                //后继结点
                int next = nodePair[0];
                int inDegreeOfNext = postNodes_inDegree.get(next);
                if (inDegreeOfNext == 1) {
                    //若next结点的入度为1，去掉zeroInDegreeNode后其入度为0，应从postNodes_inDegree中移除
                    postNodes_inDegree.remove(next);
                    //将next结点加入zeroInDegreeNodes中
                    zeroInDegreeNodes.add(next);
                } else {
                    //若next结点的入度不为1，将其入度减1
                    postNodes_inDegree.put(next, inDegreeOfNext - 1);
                }
            }
            //0入度的结点被移除，它也应同时从zeroInDegreeNodes和preNodes_outDegree中移除
            zeroInDegreeNodes.remove(0);
            preNodes_outDegree.remove(zeroInDegreeNode);
        }

        //当图中没有入度为0的结点时，判断是否还有其他结点
        //若没有其他结点，返回true,若有，返回false
        return preNodes_outDegree.isEmpty();
    }

    //拓扑排序思想
    public boolean canFinish_TopologicalSorting(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];//用数组存储所有结点的入度
        //统计各结点的入度
        for (int[] cp : prerequisites) {
            inDegrees[cp[0]]++;
        }

        //用队列存储所有入度为0的结点
        LinkedList<Integer> queue = new LinkedList<>();
        //将入度为0的结点入队
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.addLast(i);
            }
        }

        //每次获取一个0入度的结点，从图中去掉它，并更新其他结点的入度值
        while (!queue.isEmpty()) {
            Integer pre = queue.removeFirst();//获取一个0入度的结点
            numCourses--;//将其从图中去掉
            //更新其他结点的入度
            for (int[] req : prerequisites) {//遍历图中所有边
                if (req[1] != pre) {
                    continue;//当前边的前驱结点不是pre，不做操作，继续遍历
                }
                //若当前边的前驱结点是pre
                //先将后继结点的入度减1，再判断减1后是否为0
                if (--inDegrees[req[0]] == 0) {
                    queue.add(req[0]);//若减1后为0，将此结点加入0入度结点的队列
                }
            }
        }
        return numCourses == 0;//判断图中还有没有剩余结点
    }

    //深度优先遍历思想
    public boolean canFinish_DFS(int numCourses, int[][] prerequisites) {
        int[][] adjacency = new int[numCourses][numCourses];
        int[] flags = new int[numCourses];
        for (int[] cp : prerequisites)
            adjacency[cp[1]][cp[0]] = 1;
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency, flags, i)) return false;
        }
        return true;
    }

    private boolean dfs(int[][] adjacency, int[] flags, int i) {
        if (flags[i] == 1) return false;
        if (flags[i] == -1) return true;
        flags[i] = 1;
        for (int j = 0; j < adjacency.length; j++) {
            if (adjacency[i][j] == 1 && !dfs(adjacency, flags, j)) return false;
        }
        flags[i] = -1;
        return true;
    }

    public static void main(String[] args) {
        int nodesCount = 3;//结点数
        //结点连接情况
        int[][] prerequisites = {{1, 0}, {2, 1}};

        Solution solution = new Solution();
        System.out.println(solution.canFinish_mine(nodesCount, prerequisites));
    }
}
