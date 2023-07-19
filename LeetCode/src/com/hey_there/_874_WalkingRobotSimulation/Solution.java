package com.hey_there._874_WalkingRobotSimulation;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    //用两个Map存储障碍物的位置。
    //key为横纵坐标，value集合为一个横纵坐标对应的所有纵横坐标集合
    private HashMap<Integer, HashSet<Integer>> xMap = new HashMap<>();
    private HashMap<Integer, HashSet<Integer>> yMap = new HashMap<>();
    private int[] robotCord = {0, 0};//机器人所处的坐标
    private int direction = 0;//机器人面朝的方向，0123分别表示北东南西

    public int robotSim(int[] commands, int[][] obstacles) {
        //将障碍物坐标存入两个Map
        for (int[] ob : obstacles) {
            if (xMap.containsKey(ob[0]))
                xMap.get(ob[0]).add(ob[1]);
            else {
                HashSet<Integer> set = new HashSet<>();
                set.add(ob[1]);
                xMap.put(ob[0], set);
            }
            if (yMap.containsKey(ob[1]))
                yMap.get(ob[1]).add(ob[0]);
            else {
                HashSet<Integer> set = new HashSet<>();
                set.add(ob[0]);
                yMap.put(ob[1], set);
            }
        }
        //执行命令
        int ans = 0;
        for (int command : commands) {
            //转弯命令
            if (command == -1 || command == -2) {
                changeDirection(command);
                continue;
            }
            //前进命令
            if (direction == 0 || direction == 2)
                moveY(command);//纵向移动
            else
                moveX(command);//横向移动
            //计算距离
            int curDistance = robotCord[0] * robotCord[0] + robotCord[1] * robotCord[1];
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }

    //横向移动机器人的位置，参数为移动的距离
    private void moveX(int distance) {
        //检查yMap，在当前纵坐标上是否有障碍
        if (!yMap.containsKey(robotCord[1])) {
            //没有障碍，直接移动
            if (direction == 1) robotCord[0] += distance;
            else robotCord[0] -= distance;
            return;
        }
        //有障碍
        int destination;//预计能到达的横坐标
        if (direction == 1)
            destination = robotCord[0] + distance;
        else
            destination = robotCord[0] - distance;
        HashSet<Integer> curObs = yMap.get(robotCord[1]);//当前纵坐标上的所有障碍
        while (robotCord[0] != destination) {
            int nextPos;//下一个位置的横坐标
            if (direction == 1)
                nextPos = robotCord[0] + 1;
            else
                nextPos = robotCord[0] - 1;
            //下一个位置是障碍，则无法进行移动，本次移动结束
            if (curObs.contains(nextPos))
                return;
            robotCord[0] = nextPos;//下一个位置不是障碍，移动一步
        }
    }

    //纵向移动机器人的位置，参数为移动的距离
    private void moveY(int distance) {
        if (!xMap.containsKey(robotCord[0])) {
            //没有障碍，直接移动
            if (direction == 0) robotCord[1] += distance;
            else robotCord[1] -= distance;
            return;
        }
        //有障碍
        int destination;//预计能到达的横坐标
        if (direction == 0)
            destination = robotCord[1] + distance;
        else
            destination = robotCord[1] - distance;
        HashSet<Integer> curObs = xMap.get(robotCord[0]);//当前横坐标上的所有障碍
        while (robotCord[1] != destination) {
            int nextPos;//下一个位置的纵坐标
            if (direction == 0)
                nextPos = robotCord[1] + 1;
            else
                nextPos = robotCord[1] - 1;
            //下一个位置是障碍，则无法进行移动，本次移动结束
            if (curObs.contains(nextPos))
                return;
            robotCord[1] = nextPos;//下一个位置不是障碍，移动一步
        }
    }

    //转弯，参数为转弯的方向
    private void changeDirection(int turn) {
        if (turn == -1)
            direction++;
        else
            direction--;
        direction = (direction + 4) % 4;
        System.out.println("direction---" + direction);
    }
}
