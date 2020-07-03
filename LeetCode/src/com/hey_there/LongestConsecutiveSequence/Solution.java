package com.hey_there.LongestConsecutiveSequence;

import java.util.HashSet;

public class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> numsSet = new HashSet<>();
        for (int n : nums) {
            numsSet.add(n);
        }

        int longestSequence = 0;
        for (int n : numsSet) {
            if (!numsSet.contains(n - 1)) {
                int curNum=n;
                int curSequence=1;

                while (numsSet.contains(curNum + 1)) {
                    curNum++;
                    curSequence++;
                }
                longestSequence = Math.max(longestSequence, curSequence);
            }
        }
        return longestSequence;
    }
}
