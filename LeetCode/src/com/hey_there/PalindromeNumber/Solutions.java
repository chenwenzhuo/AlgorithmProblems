package com.hey_there.PalindromeNumber;


public class Solutions {
    /**
     * 将待检测的数转换为字符串进行判断
     *
     * @param x 待检测的数
     * @return x是回文数返回true，不是则返回false
     */
    public boolean isPalindrome_JudgeByString(int x) {
        //当x为负数,或x为10的倍数且非0，则它一定不是回文数
        if (x < 0 || (0 == x % 10 && x / 10 > 0)) {
            return false;
        }

        //一位数0到9一定是回文数
        if (x <= 9) {//执行到这一步，x一定非负故不需要x>=0的判断条件
            return true;
        }

        //将x转换为字符串，方便比较
        String numString = "" + x;
        int len = numString.length();//获得字符串长度
        int i = 0, j = len - 1;
        while (i < j) {
            if (numString.charAt(i) != numString.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    /**
     * 将待检测的数反转，与原数比较
     * 此处忽略了反转后可能溢出的问题
     *
     * @param x 待检测的数
     * @return x是回文数返回true，不是则返回false
     */
    public boolean isPalindrome_JudgeByReverseNum(int x) {
        //当x为负数,或x为10的倍数且非0，则它一定不是回文数
        if (x < 0 || (0 == x % 10 && x / 10 > 0)) {
            return false;
        }

        //一位数0到9一定是回文数
        if (x <= 9) {//执行到这一步，x一定非负故不需要x>=0的判断条件
            return true;
        }

        int reversedX = reverseNumber(x);
        return x == reversedX;
    }

    /**
     * 将参数首尾反转
     * 此处忽略了反转后可能溢出的问题
     *
     * @param x 待反转的数
     * @return 反转后的数，如123反转为321
     */
    public int reverseNumber(int x) {
        int reversedNum = 0;
        while (0 != x) {
            reversedNum = reversedNum * 10 + x % 10;
            x /= 10;
        }

        return reversedNum;
    }

    /**
     * 将参数的较低位的一半数字反转
     * 如输入123321，反转一半的结果为123
     * 用反转后的较低位与较高位的一半比较
     *
     * @param x 待检测的数
     * @return x是回文数返回true，不是则返回false
     */
    public boolean isPalindrome_JudgeByReverseHalf(int x) {
        //当x为负数,或x为10的倍数且非0，则它一定不是回文数
        if (x < 0 || (0 == x % 10 && x / 10 > 0)) {
            return false;
        }

        //一位数0到9一定是回文数
        if (x <= 9) {//执行到这一步，x一定非负故不需要x>=0的判断条件
            return true;
        }

        int reversedHalf = 0;
        while (reversedHalf < x) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        return (x == reversedHalf) || (x == reversedHalf / 10);
    }
}
