package com.hey_there.InterleavingString;

public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        //计算三个字符串的长度
        int len_s1 = s1.length();
        int len_s2 = s2.length();
        int len_s3 = s3.length();
        //长度不匹配直接返回false
        if (len_s3 != len_s1 + len_s2) {
            return false;
        }
        //将字符串转换为字符数组
        char[] array_s1 = s1.toCharArray();
        char[] array_s2 = s2.toCharArray();
        char[] array_s3 = s3.toCharArray();
        //dp[i][j]表示s3的前i+j个字符的子串是否是由s1的前i个字符和s2的前j个字符交错得到
        boolean[][] dp = new boolean[len_s1 + 1][len_s2 + 1];
        //初始化dp数组的第一行和第一列
        //第一行
        dp[0][0] = true;
        for (int i = 1; i <= len_s2; i++) {
            //第一行，s1贡献0个字符，s2贡献i个字符，故只需要比较各个位置字符是否相同
            //第一列同理
            dp[0][i] = (dp[0][i - 1] && array_s2[i - 1] == array_s3[i - 1]);
        }
        //第一列
        for (int i = 1; i <= len_s1; i++) {
            dp[i][0] = (dp[i - 1][0] && array_s1[i - 1] == array_s3[i - 1]);
        }

        for (int i = 1; i <= len_s1; i++) {
            for (int j = 1; j <= len_s2; j++) {
                if (dp[i - 1][j]) {//若s1贡献i-1个字符且s2贡献j个字符时，能组成s3的前i+j-1个字符的子串
                    //检查s1的第i个字符是否和s3的第i+j个字符相同
                    dp[i][j] = (array_s1[i - 1] == array_s3[i + j - 1]);
                } else if (dp[i][j - 1]) {//若s1贡献i个字符且s2贡献j-1个字符时，能组成s3的前i+j-1个字符的子串
                    //检查s2的第j个字符是否和s3的第i+j个字符相同
                    dp[i][j] = (array_s2[j - 1] == array_s3[i + j - 1]);
                } else {
                    //当前两种情况都不成立时，新增一个字符也一定不能成立
                    dp[i][j] = false;
                }
            }
        }
        return dp[len_s1][len_s2];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
}
