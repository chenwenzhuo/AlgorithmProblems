package com.hey_there.WildcardMatching;

public class Solution {
    private char[] chs_s;
    private char[] chs_p;
    private int[][] dp;

    public boolean isMatch(String s, String p) {
        int len_s = s.length();
        int len_p = p.length();
        this.chs_s = s.toCharArray();
        this.chs_p = p.toCharArray();
        /* dp[i][j]表示将s的[0,i]闭区间内的子串与p的[0,j]闭区间内的模式子串匹配时，最后能否匹配成功
         * dp[i][j] == -1，表示无法匹配
         * dp[i][j] == 1，表示可以匹配
         * dp[i][j] == 0，表示还未检查能否匹配*/
        this.dp = new int[len_s + 1][len_p + 1];

        backtrack(0, 0);
        return dp[len_s][len_p] == 1;
    }

    private void backtrack(int idx_s, int idx_p) {
        if (idx_s < chs_s.length && idx_p == chs_p.length) {
            //当模式串p已被用完，但s还未匹配完时，无法成功匹配
            dp[idx_s][idx_p] = -1;
            return;
        }
        if (idx_s == chs_s.length && idx_p < chs_p.length) {
            //当s已被完全匹配，但模式串还未用完时
            //检查模式串剩余的部分是否全为'*'
            boolean isRestStar = true;
            int idx_tmp = idx_p;
            while (idx_tmp < chs_p.length) {
                if (chs_p[idx_tmp] != '*') {
                    isRestStar = false;
                    break;
                }
                idx_tmp++;
            }
            if (isRestStar) {//当模式串剩下的全为'*'时
                idx_tmp = idx_p;
                while (idx_tmp <= chs_p.length) {
                    dp[idx_s][idx_tmp] = 1;
                    idx_tmp++;
                }
            } else {
                dp[idx_s][idx_p] = -1;
            }
            return;
        }
        if (idx_s == chs_s.length && idx_p == chs_p.length) {
            //当两个串同时用完，则匹配成功
            dp[idx_s][idx_p] = 1;
            return;
        }
        //当两个串都未用完时，进行回溯检查
        if (dp[idx_s][idx_p] == -1) {
            //dp[idx_s][idx_p]为-1，说明已对此匹配方式进行过检查，无法成功匹配
            return;
        }
        //检查模式串p的当前字符
        if (chs_p[idx_p] == '?') {
            //'?'能匹配s中的任何字符，故无需检查chs_s[idx_s]字符，直接向下匹配
            backtrack(idx_s + 1, idx_p + 1);
            dp[idx_s][idx_p] = dp[idx_s + 1][idx_p + 1];
        } else if (chs_p[idx_p] == '*') {
            //'*'能匹配任何字符串，也无需检查chs_s[idx_s]字符，直接向下匹配
            for (int curMatchLen = 0; idx_s + curMatchLen <= chs_s.length; curMatchLen++) {
                backtrack(idx_s + curMatchLen, idx_p + 1);
                dp[idx_s][idx_p] = dp[idx_s + curMatchLen][idx_p + 1];
                if (dp[idx_s][idx_p] == 1) {
                    break;//若遇到一种能匹配成功的方式，不再继续尝试
                }
            }
        } else {
            //模式串p中的当前字符为普通字符时，检查其是否与s中当前字符相同
            if (chs_s[idx_s] == chs_p[idx_p]) {
                backtrack(idx_s + 1, idx_p + 1);//相同则可继续向下匹配
                dp[idx_s][idx_p] = dp[idx_s + 1][idx_p + 1];
            } else {
                dp[idx_s][idx_p] = -1;//不同则一定不能匹配成功
            }
        }
    }

    public static void main(String[] args) {
        /*String s = "acdcb";
        String p = "a*c?b";*/
        /*String s = "mississippi";
        String p = "m??*ss*?i*pi";*/
        /*String s = "";
        String p = "*";*/
        String s = "ho";
        String p = "ho**";
        Solution solution = new Solution();
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
    }
}
