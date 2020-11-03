package com.hey_there.ValidMountainArray;

public class Solution {
    public boolean validMountainArray_1(int[] A) {
        if (A == null || A.length < 3) return false;
        //Mountain Array的前两个值一定是上升的
        if (A[0] >= A[1]) return false;

        int idx = 1;
        while (idx < A.length) {
            //当前位置的值大于前一个值，则还在上升阶段
            if (A[idx] > A[idx - 1]) {
                idx++;//idx加一，继续检查
                continue;
            }
            //相邻两位置上的值相等，直接返回false
            if (A[idx] == A[idx - 1]) return false;
            //当前位置的值小于前一个值，则开始下降
            break;
        }

        while (idx < A.length) {
            //idx指向数组最后一个位置，且仍为下降，则返回true
            if (idx == A.length - 1 && A[idx] < A[idx - 1]) return true;
            //当前位置的值小于前一个值，则继续向后检查
            if (A[idx] < A[idx - 1]) {
                idx++;
                continue;
            }
            if (A[idx] == A[idx - 1]) return false;
            //当前位置的值大于前一个值，则结束遍历，返回false
            break;
        }
        return false;
    }

    public boolean validMountainArray_2(int[] A) {
        if (A == null || A.length < 3) return false;

        int idx = 0;
        //检查上升阶段，寻找最高点
        while (idx + 1 < A.length && A[idx] < A[idx + 1]) idx++;
        //最高点不能是数组的首尾两个位置
        if (idx == 0 || idx == A.length - 1) return false;
        //检查下降阶段
        while (idx + 1 < A.length && A[idx] > A[idx + 1]) idx++;
        return idx == A.length - 1;
    }
}
