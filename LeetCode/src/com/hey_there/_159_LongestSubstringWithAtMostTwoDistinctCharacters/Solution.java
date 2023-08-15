package com.hey_there._159_LongestSubstringWithAtMostTwoDistinctCharacters;

import java.util.HashMap;

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int ans = 0;
        int low = 0, high = 0;//双指针遍历字符串
        //双指针区间中各个字符及其数量的映射
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chs = s.toCharArray();
        while (high < s.length()) {
            //将下标high位置的字符存入map
            map.put(chs[high], map.getOrDefault(chs[high], 0) + 1);
            high++;
            if (map.size() <= 2) {//区间包含的不同字符数量不超过2，更新区间长度
                ans = Math.max(ans, high - low);
                continue;
            }
            //区间包含的不同字符数量超过2，增加low，直到map大小为2
            while (true) {
                char ch_low = chs[low];
                map.put(ch_low, map.get(ch_low) - 1);//移除下标low位置的字符
                if (map.get(ch_low) == 0) {//数量减到0，将其删除
                    map.remove(ch_low);
                    low++;
                    break;
                }
                low++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcabc";
        int res = new Solution().lengthOfLongestSubstringTwoDistinct(s);
        System.out.println(res);
    }
}
