package com.hey_there.WeeklyCompetition._2020_4_26.MaximumScoreAfterSplittingString;

public class Solution {
    public int maxScore(String s) {
        char[] ch_s = s.toCharArray();
        int max = 0;//最大得分
        int leftScore, rightScore = 0;//左右两部分的得分
        //计算leftScore和rightScore的初值
        if (ch_s[0] == '0') {
            leftScore = 1;
        } else {
            leftScore = 0;
        }
        for (int i = 1; i < ch_s.length; i++) {
            if (ch_s[i] == '1') {
                rightScore++;
            }
        }
        max = leftScore + rightScore;

        //计算不同分割方式下的得分
        for (int i = 1; i < ch_s.length - 1; i++) {
            if (ch_s[i] == '0') {
                leftScore++;
            } else if (ch_s[i] == '1') {
                rightScore--;
            }
            max = Math.max(max, leftScore + rightScore);
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "011101";
        Solution solution = new Solution();
        int ans = solution.maxScore(s);
        System.out.println(ans);
    }
}
