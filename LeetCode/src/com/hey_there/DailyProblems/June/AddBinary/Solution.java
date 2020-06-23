package com.hey_there.DailyProblems.June.AddBinary;

public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sumBuilder = new StringBuilder();
        int len_a = a.length(), len_b = b.length();
        int idx_a = len_a - 1, idx_b = len_b - 1;

        int carry = 0;
        //当a和b都有剩余位可参加运算时，每次各取一位进行计算
        while (idx_a >= 0 && idx_b >= 0) {
            //将两字符作为整数与进位相加
            int tempSum = carry +
                    (a.charAt(idx_a) - '0') + (b.charAt(idx_b) - '0');
            //计算当前位向上一位的进位
            if (tempSum > 1) {
                carry = 1;
            } else {
                carry = 0;
            }
            //当前位的字符值
            char curDigit = (char) ('0' + (carry > 0 ? tempSum - 2 : tempSum));
            sumBuilder.append(curDigit);

            idx_a--;
            idx_b--;
        }
        //当a和b其中一个计算完之后，分开处理a和b
        //以下两循环最多执行其中一个
        while (idx_a >= 0) {
            int tempSum = carry + (a.charAt(idx_a) - '0');
            if (tempSum > 1) {
                carry = 1;
            } else {
                carry = 0;
            }
            char curDigit = (char) ('0' + (carry > 0 ? tempSum - 2 : tempSum));
            sumBuilder.append(curDigit);
            idx_a--;
        }
        while (idx_b >= 0) {
            int tempSum = carry + (b.charAt(idx_b) - '0');
            if (tempSum > 1) {
                carry = 1;
            } else {
                carry = 0;
            }
            char curDigit = (char) ('0' + (carry > 0 ? tempSum - 2 : tempSum));
            sumBuilder.append(curDigit);
            idx_b--;
        }
        //检查进位值是否为0
        if (carry > 0) {
            sumBuilder.append('1');
        }
        return sumBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        /*char a = '1';
        char b = '1';
        char c = (char) (a + 3);
        int ans = a - '0' + 3;
        System.out.println(c);
        System.out.println(ans);*/
        String a = "1010";
        String b = "1011";
        Solution solution = new Solution();
        String sum = solution.addBinary(a, b);
        System.out.println(sum);
    }
}
