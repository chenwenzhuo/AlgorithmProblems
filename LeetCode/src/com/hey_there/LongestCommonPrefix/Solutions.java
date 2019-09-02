package com.hey_there.LongestCommonPrefix;

public class Solutions {
    public String longestCommonPrefix(String[] strs) {
        int strsLength = strs.length;//字符数组的长度
        if (strsLength < 1) {
            return "";
        }

        /*
         *获得字符数组中最短字符串的长度。
         *公共前缀的最大长度只能达到 shortestStrLen 的值
         */
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
}
