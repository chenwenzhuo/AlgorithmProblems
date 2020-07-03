package com.hey_there.ScrambleString;

public class Solution {
    public boolean isScramble(String s1, String s2) {
        int len_s1 = s1.length();
        int len_s2 = s2.length();
        if (len_s1 != len_s2) {
            return false;
        }

        boolean[][][] scrambleDP = new boolean[len_s1][len_s1][len_s1 + 1];
        for (int i = 0; i < len_s1; i++) {
            for (int j = 0; j < len_s2; j++) {
                scrambleDP[i][j][1] = (s1.charAt(i) == s2.charAt(j));
            }
        }

        // 枚举区间长度 2～n
        for (int len = 2; len <= len_s1; len++) {
            // 枚举 S 中的起点位置
            for (int i = 0; i <= len_s1 - len; i++) {
                // 枚举 T 中的起点位置
                for (int j = 0; j <= len_s1 - len; j++) {
                    // 枚举划分位置
                    for (int k = 1; k <= len - 1; k++) {
                        // 第一种情况：S1 -> T1, S2 -> T2
                        if (scrambleDP[i][j][k] && scrambleDP[i + k][j + k][len - k]) {
                            scrambleDP[i][j][len] = true;
                            break;
                        }
                        // 第二种情况：S1 -> T2, S2 -> T1
                        // S1 起点 i，T2 起点 j + 前面那段长度 len-k ，S2 起点 i + 前面长度k
                        if (scrambleDP[i][j + len - k][k] && scrambleDP[i + k][j][len - k]) {
                            scrambleDP[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return scrambleDP[0][0][len_s1];
    }

    public static void main(String[] args) {
        /*String s1 = "great";
        String s2 = "rgtae";*/
        String s1 = "abcde";
        String s2 = "caebd";

        Solution solution = new Solution();
        System.out.println(solution.isScramble(s1, s2));
    }
}
