package com.hey_there.ConstructionBank_October_10th;

import java.util.Arrays;

public class Problem_1 {
    public int carsTrans(int[] cars, int num) {
        //cars[i]表示装完i个人最少需要的车辆数
        int[] carsNum = new int[num + 1];
        Arrays.fill(carsNum, num + 1);//num+1表示“无穷大”
        carsNum[0] = 0;//人数为0，所需车辆数也为0

        for (int n = 1; n <= num; n++) {
            for (int car : cars) {
                if (n >= car) {
                    carsNum[n] = Math.min(carsNum[n], carsNum[n - car] + 1);
                }
            }
        }
        return carsNum[num] > num ? -1 : carsNum[num];
    }
}
