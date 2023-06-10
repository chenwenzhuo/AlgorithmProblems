package com.hey_there._1170_CompareStringsByFrequencyOfSmallestCharacter;

public class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        //计算words数组中每个f(words[i])
        //由于words[i].length<=10，f(words[i])也不超过10，
        //用一个数组统计所有不同f(words[i])的出现次数
        int[] counts = new int[11];
        for (int i = 0; i < words.length; i++) {
            int cur = f(words[i]);
            counts[cur - 1]++;
        }
        //计算counts数组的后缀和
        for (int i = 9; i >= 0; i--)
            counts[i] += counts[i + 1];

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int cur = f(queries[i]);
            ans[i] = counts[cur];
        }
        return ans;
    }

    private int f(String s) {
        char ch = 'z';
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == ch)
                cnt++;
            else if (c < ch) {
                ch = c;
                cnt = 1;
            }
        }
        return cnt;
    }
}
