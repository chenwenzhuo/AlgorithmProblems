package com.hey_there.InterviewProblem_38_StringPermutation;

import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    private int length;
    private char[] chs;
    private boolean[] used;
    private StringBuilder builder;
    private HashSet<String> resSet;

    public String[] permutation(String s) {
        this.length = s.length();
        this.chs = s.toCharArray();
        this.used = new boolean[length];
        this.builder = new StringBuilder();
        this.resSet = new HashSet<>();

        Arrays.sort(chs);
        backtrack(0);//回溯构建排列字符串
        //将集合中的结果转存到数组
        String[] resArr = new String[resSet.size()];
        int idx=0;
        for (String res : resSet)
            resArr[idx++] = res;
        return resArr;
    }

    private void backtrack(int builderLen) {
        if (builderLen == length) {
            resSet.add(builder.toString());
            return;
        }
        for (int i = 0; i < length; i++) {
            if (used[i]) continue;
            if (i > 0 && chs[i] == chs[i - 1] && !used[i - 1]) continue;

            builder.append(chs[i]);
            used[i] = true;
            backtrack(builderLen + 1);
            builder.delete(builderLen, builderLen + 1);
            used[i] = false;
        }
    }
}
