package com.hey_there.LongestSubstring_NoRepeatingChars;

public class Solutions {
    public int lengthOfLongestSubstring(String s) {
        StringBuilder longestSubstring = new StringBuilder();//盛装子字符串

        int index = 0;//用来从头到尾遍历字符串
        int lengthOfString = s.length();//原始字符串的长度
        int lengthOfSubstring = 0;//最长子字符串的长度

        //从头到尾遍历原字符串
        while (index < lengthOfString) {
            String aChar = "" + s.charAt(index);//将index位置的字符取出

            /*
            若当前子字符串中不包含此字符，
            将此字符加入子串
             */
            if (!longestSubstring.toString().contains(aChar)) {
                longestSubstring.append(aChar);
                index++;
                if (longestSubstring.length() > lengthOfSubstring) {
                    lengthOfSubstring++;
                }
                continue;
            }

            //若当前子字符串包含此字符
            int firstIndexInSubstring = longestSubstring.indexOf(aChar);//此字符在子串中的位置

            if (firstIndexInSubstring + 1 >= longestSubstring.length()) {
                //若此字符在子串中结尾位置，将子串清空并填入此字符
                longestSubstring.replace(0, longestSubstring.length(), aChar);
            } else {
                //若此字符不在子串中结尾位置
                //将子串中此字符及其前面的字符删除，再将字符加在结尾
                String substringAfterAChar = longestSubstring.substring(firstIndexInSubstring + 1) + aChar;
                longestSubstring.replace(0, longestSubstring.length(), substringAfterAChar);
                //若新子串长度大于原最大长度，更新最大子串长度值
                if (longestSubstring.length() > lengthOfSubstring) {
                    lengthOfSubstring = longestSubstring.length();
                }
            }

            index++;
        }


        return lengthOfSubstring;
    }
}
