package com.hey_there.RepeatedSubstringPattern;

public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        for (int i = 2; i <= length; i++) {//将字符串s分为i个子串
            if (length % i != 0) continue;//不能整除的无需进行判断
            int lenSub = length / i;//将s分为i份后子串的长度
            String subStr = s.substring(0, lenSub);//取长度为lenSub的子串
            boolean canMatch = true;
            for (int begin = lenSub; begin < length; begin += lenSub) {
                String curSub = s.substring(begin, begin + lenSub);
                if (!curSub.equals(subStr)) {
                    canMatch = false;
                    break;
                }
            }
            if (canMatch) return true;
        }
        return false;
    }
}
