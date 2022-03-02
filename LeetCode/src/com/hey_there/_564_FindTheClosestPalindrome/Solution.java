package com.hey_there._564_FindTheClosestPalindrome;

import java.util.HashSet;

public class Solution {
    public String nearestPalindromic(String n) {
        //n最长18位，long类型最长19位，可将字符串转为long类型
        long num = Long.parseLong(n);
        long res = -1;
        HashSet<Long> candidates = getCandidates(n);
        for (long candidate : candidates) {
            if (candidate == num) continue;
            if (res == -1 || Math.abs(candidate - num) < Math.abs(res - num) ||
                    (Math.abs(candidate - num) == Math.abs(res - num) && candidate < res)) {
                res = candidate;
            }
        }
        return String.valueOf(res);
    }

    private HashSet<Long> getCandidates(String n) {
        int len = n.length();
        //默认添加两个元素
        HashSet<Long> candidates = new HashSet<Long>() {{
            add((long) Math.pow(10, len - 1) - 1);
            add((long) Math.pow(10, len) + 1);
        }};
        //数字n的前半段12345
        long prefixN = Long.parseLong(n.substring(0, (len + 1) / 2));
        //基于prefixN-1，prefixN，prefixN+1构造回文数
        for (long i = prefixN - 1; i <= prefixN + 1; i++) {
            StringBuilder builder = new StringBuilder();
            String prefixStr = String.valueOf(i);//将当前值转化为字符串
            builder.append(prefixStr);
            //将前缀反转
            StringBuilder suffix = new StringBuilder(prefixStr).reverse();
            //len为奇数时len&1值为1，len为偶数时len&1值为0
            builder.append(suffix.substring(len & 1));
            //将构造的回文数转为long类型加入集合
            candidates.add(Long.parseLong(builder.toString()));
        }
        return candidates;
    }

    public static void main(String[] args) {

    }
}
