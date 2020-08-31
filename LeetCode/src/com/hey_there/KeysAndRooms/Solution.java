package com.hey_there.KeysAndRooms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int numRooms = rooms.size();//房间数量
        //canEnterRoom[i]表示能否进入下标为i的房间
        boolean[] canEnterRoom = new boolean[numRooms];
        LinkedList<Integer> queue = new LinkedList<>();//辅助队列，记录待进入的房间号
        HashSet<Integer> set = new HashSet<>();//记录队列中已有的房间号
        queue.offer(0);
        set.add(0);

        int enteredRoomsCounter = 0;//记录能进入的房间数量
        while (!queue.isEmpty()) {
            int curRoom = queue.poll();//从队列中获得一个房间
            set.remove(curRoom);
            canEnterRoom[curRoom] = true;//进入房间，标记为true
            enteredRoomsCounter++;

            //拿到此房间中的钥匙
            List<Integer> keysInCurRoom = rooms.get(curRoom);
            for (int key : keysInCurRoom) {//遍历所有钥匙
                //钥匙对应的房间若没有进入过，则将房间号入队
                if (!canEnterRoom[key] && !set.contains(key)) {
                    queue.offer(key);
                    set.add(key);
                }
            }
        }
        //比较能进入的房间数量和总的房间数量
        return enteredRoomsCounter >= numRooms;
    }
}
