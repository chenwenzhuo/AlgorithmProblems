package com.hey_there.LongestPalindromicSubstring;

import java.util.HashMap;

public class Solutions {
    public String longestPalindrome_CenterExpanding(String s) {
        //检查参数是否合法
        if (null == s) {
            throw new IllegalArgumentException("参数非法，字符串不可为null");
        }
        if (s.equals("") || 1 == s.length()) {
            return s;
        }

        double expandCenter = 0.5;//扩展中心
        int longestStart = 0, longestEnd = 0;//最长回文子串的起始字符和结束字符下标
        int expandLeft, expandRight;//向左右扩展的标记值
        int sLength = s.length();

        //扩展中心最大只能到sLength-0.5
        while (expandCenter < sLength) {
            //初始化左右标记
            if (expandCenter == (int) expandCenter) {
                //扩展中心为整数，则子串长为奇数
                expandLeft = (int) expandCenter - 1;
                expandRight = ((int) expandCenter) + 1;
            } else {
                //扩展中心为整数，则子串长为偶数
                expandLeft = (int) expandCenter;
                expandRight = ((int) expandCenter) + 1;
            }

            //左右扩展标记不能越界
            while (expandLeft >= 0 && expandRight < sLength) {
                //字符相等则继续扩展，不等则退出循环
                if (s.charAt(expandLeft) == s.charAt(expandRight)) {
                    //新长度大于旧长度，更新旧长度的两个标记
                    if (expandRight - expandLeft > longestEnd - longestStart) {
                        longestStart = expandLeft;
                        longestEnd = expandRight;
                    }
                } else {
                    break;
                }
                expandLeft--;
                expandRight++;
            }
            expandCenter += 0.5;
        }
        return s.substring(longestStart, longestEnd + 1);
    }


    public String longestPalindrome_DynamicProgramming(String s) {
        //检查参数是否合法
        if (null == s) {
            throw new IllegalArgumentException("参数非法，字符串不可为null");
        }
        if (s.equals("") || 1 == s.length()) {
            return s;
        }

        int sLength = s.length();
        int longestStart = 0, longestEnd = 0;//最长回文子串的起始字符和结束字符下标
        boolean isPalindrome[][] = new boolean[sLength][sLength];

        for (int i = 0; i < sLength; i++) {
            for (int j = 0; j < sLength; j++) {
                //初始化矩阵的值
                //长度为1的字符串一定回文
                if (i == j) {
                    isPalindrome[i][j] = true;
                    continue;
                }
                //找出所有长度为2的回文子串
                if (1 == j - i) {
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j));
                    if (isPalindrome[i][j]) {
                        longestStart = i;
                        longestEnd = j;
                    }
                    continue;
                }
                isPalindrome[i][j] = false;//将长度＞2的子串标记初始化为false
            }
        }

        int subStringStart;
        int subStringLen = 3;
        while (true) {
            subStringStart = 0;
            while (subStringStart + subStringLen - 1 < sLength) {
                isPalindrome[subStringStart][subStringStart + subStringLen - 1] =
                        (isPalindrome[subStringStart + 1][subStringStart + subStringLen - 2] &&
                                s.charAt(subStringStart) == s.charAt(subStringStart + subStringLen - 1));

                if (isPalindrome[subStringStart][subStringStart + subStringLen - 1] &&
                        subStringLen - 1 > longestEnd - longestStart) {
                    longestStart = subStringStart;
                    longestEnd = subStringStart + subStringLen - 1;
                }
                subStringStart++;
            }
            subStringLen++;
            if (subStringLen > sLength) {
                break;
            }
        }

        return s.substring(longestStart, longestEnd + 1);
    }

    //时间复杂度太高O(n^3)
    public String longestPalindrome(String s) {
        //检查参数是否合法
        if (null == s) {
            throw new IllegalArgumentException("参数非法，字符串为null");
        }

        //长度为0或1，则本身即为最长回文子串
        if (s.length() <= 1) {
            return s;
        }

        //用HashMap保存s中所有字符及字符出现次数
        HashMap<Character, Integer> chars_Occurrences = new HashMap<>();
        //最长回文子串
        //字符串不为null或空，则至少存在长度为1的回文子串
        String longestPalindromicSubstring = "" + s.charAt(0);

        for (int i = 0; i < s.length(); i++) {
            char aCharOfStr = s.charAt(i);//当前遇到的字符

            if (!chars_Occurrences.containsKey(aCharOfStr)) {
                //遇到新字符，将其加入HashMap中
                chars_Occurrences.put(s.charAt(i), 1);
                continue;
            }

            //遇到HashMap中已有的字符，更新它的出现次数
            int oldOccurrence = chars_Occurrences.get(aCharOfStr);
            chars_Occurrences.replace(aCharOfStr, oldOccurrence, oldOccurrence + 1);

            /*
            下一步检查：
            更新了occurrence的字符与前方此字符的occurrence构成的子串是否回文
             */
            int index_aCharOfStr = i;//aCharOfStr在前方的occurrence的下标
            for (int j = 0; j < oldOccurrence; j++) {
                index_aCharOfStr = s.substring(0, index_aCharOfStr).lastIndexOf(aCharOfStr);
                String substringToCheck = s.substring(index_aCharOfStr, i + 1);
                if (isStringPalindromic(substringToCheck) &&
                        substringToCheck.length() > longestPalindromicSubstring.length()) {
                    longestPalindromicSubstring = substringToCheck;
                }
            }
        }

        return longestPalindromicSubstring;
    }

    public boolean isStringPalindromic(String strToCheck) {
        boolean isStrPalindromic = true;
        int begin = 0, end = strToCheck.length() - 1;
        while (begin < end) {
            if (strToCheck.charAt(begin) == strToCheck.charAt(end)) {
                begin++;
                end--;
                continue;
            }

            isStrPalindromic = false;
            break;
        }
        return isStrPalindromic;
    }
}
