package com.hey_there.Graph.CourseSchedule_2;

import java.util.LinkedList;

public class Solution {
    //改造CourseSchedule_1中的拓扑排序解法
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] topologicalOrder = new int[numCourses];//图的拓扑排序序列
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

        int index = 0;
        //每次获取一个0入度的结点，从图中去掉它，并更新其他结点的入度值
        while (!queue.isEmpty()) {
            Integer pre = queue.removeFirst();//获取一个0入度的结点
            numCourses--;//将其从图中去掉
            //将其加入topologicalOrder
            topologicalOrder[index] = pre;
            index++;

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

        if (numCourses == 0) {
            return topologicalOrder;
        } else {
            return new int[0];
        }
    }
}
