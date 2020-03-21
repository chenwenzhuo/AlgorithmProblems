package com.hey_there.DailyProblems.WaterAndJugProblem;

public class Solution {
    public static boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == 0 || y == 0) {
            return z == 0;
        }

        //计算最大公约数
        int gcd = x + y;
        if (x == y) {
            gcd = x;
        } else {
            //更相减损
            /*while (gcd > 0) {
                gcd = x - y;
                gcd = gcd > 0 ? gcd : (gcd * -1);//将gcd转化为正值
                if (gcd == Math.min(x, y)) {
                    break;
                } else {
                    x = Math.min(x, y);
                    y = gcd;
                }
            }*/

            //辗转相除
            int remainder;
            int smaller = Math.min(x, y);
            int bigger = Math.max(x, y);
            do {
                remainder = bigger % smaller;

                if (remainder == 0) {
                    gcd = smaller;
                } else {
                    bigger = smaller;
                    smaller = remainder;
                }
            } while (remainder != 0);
        }
        return z % gcd == 0;
    }

    public static void main(String[] args) {
        System.out.println(canMeasureWater(3, 5, 4));
    }
}
