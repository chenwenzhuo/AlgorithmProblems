package com.hey_there.jifenchoujiang;

import java.util.Map;

public class Solution {
    public String luckyClients(String clients, int target) {
        //若字符串长度为0，即没有用户信息输入，直接返回NONE
        if (clients.length() == 0) {
            return "NONE";
        }

        //以逗号 , 分割字符串，获得每个用户名和他的积分组成的字符串
        String[] clientsAndPoints = clients.split(",");

        //StringBuilder用来存储所有的中奖用户名字
        StringBuilder luckyNames = new StringBuilder();

        //遍历所有用户和他们的积分
        for (String oneClient : clientsAndPoints) {
            //以冒号 : 分割字符串，获得一个长为2的字符串数组
            // oneClientAndPoints[0]是用户名，oneClientAndPoints[1]是他的积分
            String[] oneClientAndPoints = oneClient.split(":");

            //将积分转成整数，和target比较，若相等，就把名字加入luckNames
            if (Integer.parseInt(oneClientAndPoints[1]) == target) {
                if (luckyNames.length() > 0) {
                    luckyNames.append(",");
                }
                luckyNames.append(oneClientAndPoints[0]);
            }
        }

        //若没有用户中奖，返回NONE
        if (luckyNames.length() == 0) {
            return "NONE";
        }
        //否则返回所有中奖用户的名字
        return luckyNames.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //String luckyNames = solution.luckyClients("tom:123,tim:456,jim:123", 123);
        String luckyNames = solution.luckyClients("tom:234", 123);
        System.out.println(luckyNames);
    }
}
