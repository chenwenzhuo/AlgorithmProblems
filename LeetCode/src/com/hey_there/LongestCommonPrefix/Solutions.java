package com.hey_there.LongestCommonPrefix;

public class Solutions {
    public String longestCommonPrefix_1(String[] strs) {
        int strsLength = strs.length;//字符数组的长度
        if (strsLength < 1) {
            return "";
        }
        /*获得字符数组中最短字符串的长度。
         *公共前缀的最大长度只能达到 shortestStrLen 的值*/
        int shortestStrLen = Integer.MAX_VALUE;
        for (String str : strs) {
            if (str.length() < shortestStrLen) {
                shortestStrLen = str.length();
            }
        }
        String tempPrefix = "";//中间前缀
        int tempPrefixLen = 1;//中间前缀长度
        while (tempPrefixLen <= shortestStrLen) {
            //对数组中第一个字符串获取当前长度的前缀
            tempPrefix = strs[0].substring(0, tempPrefixLen);

            //对数组中其他字符串获取当前长度的前缀，检查是否匹配
            for (int i = 1; i < strsLength; i++) {
                //若遇到不匹配，则将长度减一，返回减一后的长度的前缀
                if (!tempPrefix.equals(strs[i].substring(0, tempPrefixLen))) {
                    int finalLen = tempPrefixLen - 1;
                    return tempPrefix.substring(0, finalLen);
                }
            }
            tempPrefixLen++;
        }
        return tempPrefix;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }
        int len_strs = strs.length;
        String lcp = strs[0];//将第一个字符串暂时作为最长公共前缀
        for (int i = 1; i < len_strs; i++) {
            //将lcp字符串和当前strs[i]按长度排序
            String shorter = lcp.length() < strs[i].length() ? lcp : strs[i];
            String longer = lcp.length() < strs[i].length() ? strs[i] : lcp;
            //对shorter和longer取同样长的前缀进行比较是否相等
            int len_lcp = shorter.length();
            while (!shorter.substring(0, len_lcp).equals(longer.substring(0, len_lcp))) {
                len_lcp--;
            }
            //若shorter和longer的最长公共前缀长度为0，则直接返回
            if (len_lcp == 0) {
                return "";
            }
            //shorter和longer的最长公共前缀长度不为0时
            //取最长公共前缀，与strs数组后续字符串再求公共前缀
            lcp = shorter.substring(0, len_lcp);
        }
        return lcp;
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        Solutions solution = new Solutions();
        String lcp = solution.longestCommonPrefix(strs);
        System.out.println(lcp);
    }
}
