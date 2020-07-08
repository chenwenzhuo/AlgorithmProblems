package com.hey_there.CompareVersionNumbers;

public class Solution {
    public int compareVersion(String version1, String version2) {
        //将版本号以点号'.'分割
        String[] arrV1 = version1.split("\\.");
        String[] arrV2 = version2.split("\\.");
        int len_arrV1 = arrV1.length;
        int len_arrV2 = arrV2.length;
        int idx1 = 0;
        int idx2 = 0;
        //从两数组中各取一个字符串比较，直到较短的数组被取完
        while (idx1 < len_arrV1 && idx2 < len_arrV2) {
            //转换为int值
            int intVal1 = Integer.parseInt(arrV1[idx1]);
            int intVal2 = Integer.parseInt(arrV2[idx2]);
            //二者值不同，则可得出比较结果
            if (intVal1 > intVal2) {
                return 1;
            } else if (intVal1 < intVal2) {
                return -1;
            }
            //若二者值相同，继续比较
            idx1++;
            idx2++;
        }
        //若较短的数组被取完也未比较出结果，
        //则检查较长的数组剩余的部分，转换成int值是否有非0的
        //以下两个while最多执行一个
        while (idx1 < len_arrV1) {
            if (Integer.parseInt(arrV1[idx1]) > 0) {
                return 1;
            }
            idx1++;
        }
        while (idx2 < len_arrV2) {
            if (Integer.parseInt(arrV2[idx2]) > 0) {
                return -1;
            }
            idx2++;
        }
        //检查完较长的数组也未得出结果，则两版本号相等
        return 0;
    }
}
