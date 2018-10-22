package com.heythere;

import java.util.HashMap;

public class Solutions {
    public String longestPalindrome(String s) {
        //检查参数是否合法
        if (null == s || s.equals("")) {
            throw new IllegalArgumentException("参数非法，字符串为空或null");
        }

        //用HashMap保存s中所有字符及字符出现次数
        HashMap<Character, Integer> chars_Occurrences = new HashMap<>();
        //最长回文子串
        String longestPalindromicSubstring = null;

        for (int i = 0; i < s.length(); i++) {
            char aCharOfStr = s.charAt(i);

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
        }

        return longestPalindromicSubstring;
    }
}
