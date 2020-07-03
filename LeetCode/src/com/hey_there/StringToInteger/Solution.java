package com.hey_there.StringToInteger;

public class Solution {
    private String digits = "1234567890";//数字

    public int myAtoi_1(String str) {
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

    public int myAtoi_2(String str) {
        char[] strChars = str.toCharArray();
        boolean parsing = false;//表示是否已经开始进行字符转数字的处理
        boolean negative = false;
        long numFromStr = 0;
        for (char strChar : strChars) {
            switch (strChar) {
                case '-':
                    if (!parsing) {//遇到负号时若未开始进行转换
                        parsing = true;//将标志位置为true，从下一个字符开始进行转换
                        negative = true;
                    } else {//若遇到负号时正在进行转换
                        return (int) numFromStr;//结束转换，返回
                    }
                    break;
                case '+':
                    if (!parsing) {
                        parsing = true;
                    } else {
                        return (int) numFromStr;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    //遇到数字字符，若未开始转换则改变标志位，开始转换
                    if (!parsing) {
                        parsing = true;
                    }
                    //改变数值
                    numFromStr *= 10;
                    if (negative) {
                        numFromStr -= (strChar - '0');
                    } else {
                        numFromStr += (strChar - '0');
                    }
                    //检查数字是否超过32位有符号整数的范围
                    if (numFromStr >= Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    } else if (numFromStr <= Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                    break;
                case ' ':
                    //未开始转换时遇到空白符则略过，
                    //开始转换后遇到空白符则结束
                    if (parsing) {
                        return (int) numFromStr;
                    }
                    break;
                default:
                    //遇到负号、数字和空白符以外的字符，直接退出
                    return (int) numFromStr;
            }
        }
        //从遍历中退出后，返回数字的值
        return (int) numFromStr;
    }

    public static void main(String[] args) {
        //String str = "words and 987";
        String str = "  0000000000012345678";
        Solution solution = new Solution();
        System.out.println(solution.myAtoi_2(str));
    }
}
