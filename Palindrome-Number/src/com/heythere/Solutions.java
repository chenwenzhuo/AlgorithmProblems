package com.heythere;

public class Solutions {
    /**
     * 将待检测的数转换为字符串进行判断
     * @param x 待检测的数
     * @return x是回文数返回true，不是则返回false
     */
    public boolean isPalindrome(int x) {
        //当x为负数,或x为10的倍数且非0，则它一定不是回文数
        if (x < 0 ) {
            return false;
        }
        if (0 == x % 10 && x / 10 > 0) {
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
}
