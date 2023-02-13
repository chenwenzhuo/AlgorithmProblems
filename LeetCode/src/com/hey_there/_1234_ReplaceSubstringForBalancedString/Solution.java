package com.hey_there._1234_ReplaceSubstringForBalancedString;

public class Solution {
    public int balancedString(String s) {
        char[] sArr = s.toCharArray();
        int length = sArr.length;
        //使用数组对每个字母进行计数
        int[] cnt = new int[26];
        for (char c : sArr)
            cnt[c - 'A']++;
        //已满足条件，直接返回0
        int target = length / 4;
        if (cnt[4] == target && cnt[16] == target &&
                cnt[17] == target && cnt[22] == target)
            return 0;
        int ans = length, left = 0;
        for (int right = 0; right < length; right++) {
            //right指向的字符被滑动窗口覆盖，将其数量减1
            cnt[sArr[right] - 'A']--;
            while (cnt[4] <= target && cnt[16] <= target &&
                    cnt[17] <= target && cnt[22] <= target) {
                ans = Math.min(ans, right - left + 1);
                //left指向的字符移出滑动窗口，将其数量加1
                cnt[sArr[left] - 'A']++;
                left++;
            }
        }
        return ans;
    }
}
