package com.hey_there.Graph.CourseSchedule;

import java.util.HashMap;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //prerequisites的第一列表示的是所有入度不为0的结点，第二列表示的是所有出度不为0的结点
        HashMap<Integer, Integer> postNodes_inDegree = new HashMap<>();//结点值与入度的映射
        HashMap<Integer, Integer> preNodes_outDegree = new HashMap<>();//结点值与出度的映射

        int rows = prerequisites.length;//二维数组的行数
        for (int[] prerequisite : prerequisites) {
            int post = prerequisite[0];
            int pre = prerequisite[1];

            if (postNodes_inDegree.containsKey(post)) {
                //此结点值若已存在于map中，将入度加一
                int value = postNodes_inDegree.get(post);
                postNodes_inDegree.put(post, value + 1);
            } else {
                postNodes_inDegree.put(post, 1);//不存在于map中则将此结点值加入map
            }
            if (preNodes_outDegree.containsKey(pre)) {
                int value = preNodes_outDegree.get(pre);
                preNodes_outDegree.put(pre, value + 1);
            } else {
                preNodes_outDegree.put(pre, 1);
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }
}
