package com.hey_there.DivideTwoIntegers;

public class Solution {
    //将除法转化为减法，复杂度太高（超出时间限制）
    public int divide_convertToSubtraction(int dividend, int divisor) {
        //将被除数与除数转换为 long 类型并取绝对值
        long dividendLong = Math.abs(dividend);
        long divisorLong = Math.abs(divisor);
        if (dividendLong < divisorLong) {
            //若被除数绝对值小于除数，商一定为0
            return 0;
        }

        //只有被除数为Integer.MIN_VALUE除数为-1时会溢出，此时直接返回Integer.MAX_VALUE
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int tag_PosOrNeg;//两数是否同号的标记，同号为1，异号为-1
        if (dividend > 0) {
            if (divisor > 0) {
                tag_PosOrNeg = 1;
            } else {
                tag_PosOrNeg = -1;
            }
        } else {
            if (divisor > 0) {
                tag_PosOrNeg = -1;
            } else {
                tag_PosOrNeg = 1;
            }
        }

        long result = 0;//要返回的结果(无符号）
        while (dividendLong >= divisorLong) {
            result++;
            dividendLong -= divisorLong;
        }

        //给结果加上符号
        result *= tag_PosOrNeg;
        return (int) result;
    }

    public int divide(int dividend, int divisor) {
        //将被除数与除数转换为 long 类型并取绝对值
        long dividendAbs = Math.abs((long) dividend);
        long divisorAbs = Math.abs((long) divisor);
        if (dividendAbs < divisorAbs) {
            //若被除数绝对值小于除数，商一定为0
            return 0;
        }
        //只有被除数为Integer.MIN_VALUE除数为-1时会溢出，此时直接返回Integer.MAX_VALUE
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }


        //将被除数的各位数字存储到数组中
        //首先计算被除数的数字位数
        int dividendDigitsCount = 0;
        long tempDividend = dividendAbs;
        while (tempDividend > 0) {
            tempDividend /= 10;
            dividendDigitsCount++;
        }
        //存储各位数字到数组
        int[] digits_dividend = new int[dividendDigitsCount];
        tempDividend = dividendAbs;
        for (int i = dividendDigitsCount - 1; i >= 0; i--) {
            digits_dividend[i] = (int) (tempDividend % 10);
            tempDividend /= 10;
        }


        //模拟列竖式算除法
        int quotient = 0;//商
        tempDividend = 0;
        for (int i = 0; i < dividendDigitsCount; i++) {
            tempDividend = tempDividend * 10 + digits_dividend[i];
            if (tempDividend < divisorAbs) {
                if (quotient != 0) {
                    quotient *= 10;
                }
                continue;
            }

            int tempQuotient = 0;
            while (tempDividend >= divisorAbs) {
                tempQuotient++;
                tempDividend -= divisorAbs;
            }

            quotient = quotient * 10 + tempQuotient;
        }

        //确定商的正负
        if ((dividend > 0 && divisor > 0) ||
                (dividend < 0 && divisor < 0)) {
            //当被除数与除数同号时，商为正
            return quotient;
        }
        //当被除数与除数异号时，商为负
        return quotient * -1;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.pow(2, 31) * (-1));
        /*2147483647
2*/
        Solution solution = new Solution();
        System.out.println(solution.divide(2147483647, 2));
        System.out.println(2147483647 / 2);
    }
}
