package com.heythere;

public class Solutions {
    private String digits = "1234567890";//数字

    public int myAtoi(String str) {
        /*
         *首先检查输入字符串能否转换为整数。
         * 输入为null、str长度为0、str仅包含空白字符、str首个非空白字符不是数字也不是正负号，
         * 以上情况均返回0
         */
        if (null == str || str.equals("")) {
            return 0;
        }
        int strLength = str.length();
        int startIndex = getFirstNon_SpaceIndex(str);//获得str中第一个非空白字符的下标
        /*
         * 检查字符串是否全为空白字符
         * startIndex超过字符串长度则全为空白字符，此时返回0
         */
        if (startIndex >= strLength) {
            return 0;
        }
        /*
         *检查第一个非空白字符是否有效（是否为数字或正负号）
         * 不为数字或正负号则返回0
         */
        String positive_negative_sign = "+-";//正负号
        if (!(digits.contains("" + str.charAt(startIndex)) ||
                positive_negative_sign.contains("" + str.charAt(startIndex)))) {
            return 0;
        }

        //执行到这一步，startIndex代表的字符一定是数字或正负号
        if ('-' == str.charAt(startIndex)) {
            return executeNegative(str, strLength, startIndex + 1);
        } else if ('+' == str.charAt(startIndex)) {
            return executePositive(str, strLength, startIndex + 1);
        } else {
            return executePositive(str, strLength, startIndex);
        }
    }

    private int executeNegative(String str, int strLen, int startIndex) {
        char charToExecute;//下一个要处理的字符
        int executeIndex = startIndex;//待处理的字符的下标
        long numFromStr = 0;//以一个long型数字暂存处理过程中的数字

        while (executeIndex < strLen && digits.contains("" + (charToExecute = str.charAt(executeIndex)))) {
            numFromStr = numFromStr * 10 - Integer.parseInt("" + charToExecute);
            if (numFromStr <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            executeIndex++;
        }

        return (int) numFromStr;
    }

    private int executePositive(String str, int strLen, int startIndex) {
        char charToExecute;//下一个要处理的字符
        int executeIndex = startIndex;//待处理的字符的下标
        long numFromStr = 0;//以一个long型数字暂存处理过程中的数字

        while (executeIndex < strLen && digits.contains("" + (charToExecute = str.charAt(executeIndex)))) {
            numFromStr = numFromStr * 10 + Integer.parseInt("" + charToExecute);
            if (numFromStr >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            executeIndex++;
        }
        return (int) numFromStr;
    }

    private int getFirstNon_SpaceIndex(String str) {
        int index = 0;
        while (index < str.length() && ("" + str.charAt(index)).equals(" ")) {
            index++;
        }
        return index;
    }
}
