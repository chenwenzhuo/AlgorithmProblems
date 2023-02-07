package com.hey_there._1604_AlertUsingSameKeyCardThreeOrMoreTimesInOneHourPeriod;

import java.util.*;

public class Solution {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        List<String> res = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        int length = keyName.length;
        //将名称和时间的键值对存入map
        for (int i = 0; i < length; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            ArrayList<String> list = map.getOrDefault(name, new ArrayList<>());
            list.add(time);
            if (!map.containsKey(name)) {
                map.put(name, list);//map的键集合中不包含当前名字，则将键值对加入map
            }
        }
        //遍历map
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            String name = entry.getKey();
            ArrayList<String> timeList = entry.getValue();
            int size = timeList.size();
            if (size<3)
                continue;//使用次数小于3，不用比较
            Collections.sort(timeList);//排序
            for (int i = 2; i < size; i++) {
                String time1 = timeList.get(i - 2);
                String time2 = timeList.get(i);
                if (compareTimeStr(time1, time2)) {
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);//返回前进行排序
        return res;
    }

    private boolean compareTimeStr(String time1, String time2) {
        //将字符串形式的时间转为数字
        int[] timeArr1 = new int[2];
        int[] timeArr2 = new int[2];
        timeArr1[0] = Integer.parseInt(time1.substring(0, 2));
        timeArr1[1] = Integer.parseInt(time1.substring(3));
        timeArr2[0] = Integer.parseInt(time2.substring(0, 2));
        timeArr2[1] = Integer.parseInt(time2.substring(3));
        //比较时间，满足以下条件时，两个时间在同一天的一小时之内
        //1.小时数相等，且time1的分钟数小于等于time2的分钟数；
        //2.小时数相差为1，且time2比time1大，且time2的分钟数小于等于time1的分钟数
        return (timeArr1[0] == timeArr2[0] && timeArr1[1] <= timeArr2[1]) ||
                (timeArr2[0] - timeArr1[0] == 1 && timeArr2[1] <= timeArr1[1]);
    }
}
