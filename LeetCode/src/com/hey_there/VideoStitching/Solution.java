package com.hey_there.VideoStitching;

public class Solution {
    public int videoStitching(int[][] clips, int T) {
        //dp[i][j]表示第i分钟到第j分钟的视频所需的最少片段数量，i<=j
        int[][] dp = new int[T + 1][T + 1];
        for (int[] clip : clips) {
            /*for (int i = clip[0]; i <= clip[1] && i <= T; i++) {
                for (int j = i; j <= clip[1] && j <= T; j++) {
                    dp[i][j] = 1;
                }
            }*/
            for (int i = clip[1]; i >= clip[0]; i--) {
                if (i > T) {
                    continue;
                }
                for (int j = clip[1]; j >= clip[0] && j >= i; j--) {
                    if (j > T) {
                        continue;
                    }
                    if (dp[i][j] == 1) {
                        break;
                    }
                    dp[i][j] = 1;
                }
            }
        }
        //检查dp数组对角线元素是否都大于1，若存在dp[i][i]==0，则无法拼接完成
        for (int i = 0; i <= T; i++) {
            if (dp[i][i] == 0) {
                return -1;
            }
        }
        //沿对角线计算dp数组中未被计算的元素
        for (int diff = 1; diff <= T; diff++) {
            for (int base = 0; base < T && base + diff <= T; base++) {
                int curColumn = base + diff;
                //当前位置的值已计算过时，保持当前位置的原值
                if (dp[base][curColumn] > 0) {
                    continue;
                }
                //从当前位置往下，直到找到一个位置值为1
                int row = base + 1;
                while (dp[row][curColumn] != 1) {
                    row++;
                }
                if (dp[base][row] > 0) {
                    dp[base][curColumn] = dp[base][row] + 1;
                }
            }
        }
        if (dp[0][T] > 0) {
            return dp[0][T];
        }
        return -1;
    }

    public static void main(String[] args) {
        /*int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        int T = 10;*/
        /*int[][] clips = {{0, 1}, {6, 8}, {0, 2}, {5, 6}, {0, 4}, {0, 3}, {6, 7},
                {1, 3}, {4, 7}, {1, 4}, {2, 5}, {2, 6}, {3, 4}, {4, 5}, {5, 7}, {6, 9}};
        int T = 9;*/
        /*int[][] clips = {{0, 5}, {6, 8}};
        int T = 7;*/
        int[][] clips = {{0, 4}, {2, 8}};
        int T = 5;

        Solution solution = new Solution();
        int ans = solution.videoStitching(clips, T);
        System.out.println(ans);
    }
}
