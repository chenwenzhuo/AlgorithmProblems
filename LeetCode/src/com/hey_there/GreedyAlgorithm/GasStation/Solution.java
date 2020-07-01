package com.hey_there.GreedyAlgorithm.GasStation;

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        int total_tank = 0;
        int curr_tank = 0;
        int starting_station = 0;
        for (int i = 0; i < n; ++i) {
            total_tank += gas[i] - cost[i];
            curr_tank += gas[i] - cost[i];
            // If one couldn't get here,
            if (curr_tank < 0) {
                // Pick up the next station as the starting one.
                starting_station = i + 1;
                // Start with an empty tank.
                curr_tank = 0;
            }
        }
        return total_tank >= 0 ? starting_station : -1;
    }

    public int canCompleteCircuit_1(int[] gas, int[] cost) {
        int len = gas.length;
        for (int start = 0; start < len; start++) {
            if (gas[start] < cost[start]) {
                continue;
            }
            if (canComplete(gas, cost, start)) {
                return start;
            }
        }
        return -1;
    }

    private boolean canComplete(int[] gas, int[] cost, int start) {
        int len = gas.length;
        int passedStations = 0;//已经走过的加油站数量
        int curPos = start;
        int obtainedGas = gas[curPos];
        while (passedStations < len) {
            if (obtainedGas < cost[curPos]) {
                return false;//已有的汽油不够走到下一站时直接返回false
            }
            obtainedGas = obtainedGas - cost[curPos] + gas[(curPos + 1) % len];
            curPos = (curPos + 1) % len;
            passedStations++;
        }
        return true;
    }
}
