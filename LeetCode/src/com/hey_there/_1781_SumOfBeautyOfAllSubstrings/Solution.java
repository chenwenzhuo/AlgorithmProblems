package com.hey_there._1781_SumOfBeautyOfAllSubstrings;

public class Solution {
    public int beautySum(String s) {
        if (s.length() <= 2)
            return 0;//字符串长度小于等于2，直接返回0
        int length = s.length();
        int res = 0;
        for (int start = 0; start < length; start++) {
            int[] cnt = new int[26];//记录每个字符的出现次数
            int maxFreq = 0;
            for (int end = start; end < length; end++) {
                char c = s.charAt(end);
                cnt[c - 'a']++;//将当前字符数量加1
                maxFreq = Math.max(maxFreq, cnt[c - 'a']);
                //查找当前子串中频率的最小值
                int minFreq = s.length();
                for (int i = 0; i < 26; i++)
                    if (cnt[i] > 0) minFreq = Math.min(minFreq, cnt[i]);
                //更新res
                res += maxFreq - minFreq;
            }
        }
        return res;
    }
}
