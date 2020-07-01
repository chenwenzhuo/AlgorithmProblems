package com.hey_there.DailyProblems.July.MaximumLengthOfRepeatedSubarray;

public class Solution {
    public int findLength(int[] A, int[] B) {
        int lenA = A.length, lenB = B.length;
        //dp[i][j]表示A[i:]和B[j:]的最长公共前缀
        //A[i:]表示表示数组 A 中索引 i 到数组末尾的范围对应的子数组
        int[][] dp = new int[lenA + 1][lenB + 1];
        int ans = 0;
        for (int i = lenA - 1; i >= 0; i--) {
            for (int j = lenB - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {0, 1, 1, 1, 1};
        int[] B = {1, 0, 1, 0, 1};
        Solution solution = new Solution();
        int ans = solution.findLength(A, B);
        System.out.println(ans);
    }
}
