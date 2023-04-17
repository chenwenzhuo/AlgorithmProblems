package com.hey_there._2409_CountDaysSpentTogether;

public class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        //daysBeforeCurMon[i]表示i+1月之前有多少天
        int[] daysBeforeCurMon = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int[] dateAlice = {
                Integer.parseInt(arriveAlice.substring(0, 2)), Integer.parseInt(arriveAlice.substring(3)),
                Integer.parseInt(leaveAlice.substring(0, 2)), Integer.parseInt(leaveAlice.substring(3))
        };//alice的到达、离开日期
        int[] dateBob = {
                Integer.parseInt(arriveBob.substring(0, 2)), Integer.parseInt(arriveBob.substring(3)),
                Integer.parseInt(leaveBob.substring(0, 2)), Integer.parseInt(leaveBob.substring(3))
        };//bob的到达、离开日期
        //alice到达、离开日是一年的第几天
        int aliceArriveDay = daysBeforeCurMon[dateAlice[0] - 1] + dateAlice[1];
        int aliceLeaveDay = daysBeforeCurMon[dateAlice[2] - 1] + dateAlice[3];
        //bob到达、离开日是一年的第几天
        int bobArriveDay = daysBeforeCurMon[dateBob[0] - 1] + dateBob[1];
        int bobLeaveDay = daysBeforeCurMon[dateBob[2] - 1] + dateBob[3];
        int cnt = 0;
        for (int i = 1; i <= 365; i++) {
            if (aliceArriveDay <= i && i <= aliceLeaveDay && bobArriveDay <= i && i <= bobLeaveDay)
                cnt++;
        }
        return cnt;
    }
}
