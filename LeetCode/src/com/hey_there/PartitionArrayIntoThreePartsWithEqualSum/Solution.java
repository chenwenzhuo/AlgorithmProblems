package com.hey_there.PartitionArrayIntoThreePartsWithEqualSum;

public class Solution {
    public static boolean canThreePartsEqualSum(int[] A) {
        int len = A.length;
        //求数组全部元素总和
        int totalSum = 0;
        for (int a : A) {
            totalSum += a;
        }

        //若总和无法被三整除，则返回false
        if (totalSum % 3 != 0) {
            return false;
        }
        int partitionSum = totalSum / 3;
        int dividePoint_1 = -1, dividePoint_2 = -1;//将数组分成三份的分割点
        int tempSum = 0;
        //找到第一个和为partitionSum分割点
        for (int i = 0; i < len; i++) {
            tempSum += A[i];
            if (tempSum == partitionSum) {
                dividePoint_1 = i;
                break;
            }
        }
        //找到第二个和为partitionSum分割点
        tempSum = 0;
        for (int i = dividePoint_1 + 1; i < len; i++) {
            tempSum += A[i];
            if (tempSum == partitionSum) {
                dividePoint_2 = i;
                break;
            }
        }

        return (dividePoint_1 != -1 && dividePoint_2 != -1 && dividePoint_2 < len - 1);
    }

    public static void main(String[] args) {
        int[] nums = {10, -10, 10, -10, 10, -10, 10, -10};
        System.out.println(canThreePartsEqualSum(nums));
    }
}
