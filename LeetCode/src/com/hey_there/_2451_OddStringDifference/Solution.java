package com.hey_there._2451_OddStringDifference;

import java.util.Arrays;

public class Solution {
    public String oddString(String[] words) {
        int len_diff = words[0].length() - 1;//diff数组的长度
        int index1 = 0, index2 = -1;
        int cnt1 = 1, cnt2 = 0;
        int[] diffArr1 = new int[len_diff];
        for (int i = 0; i < len_diff; i++)
            diffArr1[i] = words[0].charAt(i + 1) - words[0].charAt(i);

        int[] diffArr2 = null;
        for (int i = 1; i < words.length; i++) {
            int[] diffTemp = new int[len_diff];
            for (int j = 0; j < len_diff; j++)
                diffTemp[j] = words[i].charAt(j + 1) - words[i].charAt(j);
            if (Arrays.equals(diffArr1, diffTemp)) {//当前diff数组与diffArr1相同
                cnt1++;
                if (diffArr2 != null)
                    return words[index2];
            } else {
                if (diffArr2 == null) {
                    diffArr2 = diffTemp;
                    index2 = i;
                    cnt2++;
                } else
                    return words[index1];
            }
        }
        if (cnt1 == 1)
            return words[index1];
        if (cnt2 == 1)
            return words[index2];
        return "";
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"adc", "wzy", "abc"};
        String res = solution.oddString(words);
        System.out.println(res);
    }
}
