package com.hey_there._1710_MaximumUnitsOnTruck;

import java.util.Arrays;

public class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        //将所有盒子按容量从大到小排序
        Arrays.sort(boxTypes, (n1, n2) -> n2[1] - n1[1]);
        int totalUnits = 0;//总的单元数
        for (int i = 0; i < boxTypes.length && truckSize > 0; i++) {
            int boxCapacity = boxTypes[i][1];//当前盒子的容量
            int boxCnt = boxTypes[i][0];//当前盒子的数量
            if (truckSize >= boxCnt) {//卡车容量比盒子数量多
                totalUnits += boxCapacity * boxCnt;
                truckSize -= boxCnt;
            } else {
                totalUnits += boxCapacity * truckSize;
                truckSize = 0;
            }
        }
        return totalUnits;
    }

    public static void main(String[] args) {
        int[][] boxTypes = {{1, 3}, {2, 2}, {3, 1}};
        int truckSize = 4;
        Solution solution = new Solution();
        int res = solution.maximumUnits(boxTypes, truckSize);
        System.out.println(res);
    }
}
