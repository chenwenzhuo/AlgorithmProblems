package com.hey_there.LongestArithmeticSequence;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    public int longestArithSeqLength_my(int[] A) {
        int len = A.length;
        if (len <= 2) {
            return len;
        }
        int LASL = 2;//长度不小于3的数组中，等差子序列的长度至少为2
        //dp[i][j]表示以A[i]和A[j]两个数结尾的等差子序列的长度
        int[][] dp = new int[len][len];
        //初始化dp数组的第一行
        /*Arrays.fill(dp[0], 2);
        dp[0][0] = 0;*/
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = 2;
                //A[j]和A[i]的差值
                int diff = A[j] - A[i];
                for (int k = 0; k < i; k++) {
                    if (A[i] - A[k] == diff) {
                        dp[i][j] = dp[k][i] + 1;
                        LASL = Math.max(LASL, dp[i][j]);
                    }
                }
            }
        }
        return LASL;
    }

    public int longestArithSeqLength(int[] A) {
        int len = A.length;
        //将A数组中所有不同的值及其下标存入map
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        ArrayList<Integer> temp;
        for (int i = 0; i < len; i++) {
            if (hashMap.get(A[i]) == null) {
                temp = new ArrayList<>();
                temp.add(i);
                hashMap.put(A[i], temp);
            } else {
                hashMap.get(A[i]).add(i);
            }
        }
        //dp[i][j]表示以A[i]和A[j]两个数结尾的等差子序列的长度
        int[][] dp = new int[len][len];
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = 2;//初始化
            }
        }
        int max_len = 2;
        for (int i = 1; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                //A[j]和A[i]的差值
                int diff = A[j] - A[i];
                //检查数组A中是否有数字A[i]-diff
                ArrayList<Integer> list = hashMap.get(A[i] - diff);
                if (list != null) {
                    //数组A中有数字A[i]-diff时，遍历list中存储的下标，计算dp[i][j]
                    for (int index : list) {
                        if (index < i) {
                            //dp[i][j]的值最终为小于i的最大index对应的dp[index][i]加上1
                            dp[i][j] = dp[index][i] + 1;
                        } else {
                            break;//当index的值超过i时不再遍历
                        }
                    }
                }
                max_len = Math.max(max_len, dp[i][j]);
            }
        }
        return max_len;
    }
}
