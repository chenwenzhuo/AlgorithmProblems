package com.heythere;

import java.util.HashMap;

public class Solutions {
    boolean[][] isPalindrome;

    public String longestPalindrome_DynamicProgramming(String s) {
        //检查参数是否合法
        if (null == s) {
            throw new IllegalArgumentException("参数非法，字符串不可为null");
        }
        int sLength = s.length();
        isPalindrome = new boolean[sLength + 1][sLength + 1];
        for (int i = 0; i < sLength + 1; i++) {
            for (int j = 0; j < sLength + 1; j++) {
                isPalindrome[i][j] = false;
            }
        }

        for (int i = 0; i < sLength + 1; i++) {
            for (int j = 0; j + i < sLength + 1; j++) {

            }
        }

        return "";
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
