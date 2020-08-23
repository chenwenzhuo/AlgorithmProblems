package com.hey_there.BitwiseAndOfNumbersRange;

import java.util.Arrays;

public class Solution {
    public int rangeBitwiseAnd_mine(int m, int n) {
        if (m == 0 || n == 0) return 0;
        if (m == n) return m;

        int[] numBits = new int[32];
        Arrays.fill(numBits, 1);

        int shiftRight = 0;//将数字右移的位数
        for (int i = 31; i >= 0; i--) {
            //检查[m,n]范围内的数的各个位
            for (int j = m; j <= n; j++) {
                int temp = j >> shiftRight;//将当前数字j右移
                if (temp % 2 == 0) {
                    numBits[i] = 0;
                    break;
                }
            }
            shiftRight++;
        }
        //计算结果
        int res = 0, exp = 1;
        for (int i = 31; i >= 0; i--) {
            if (numBits[i] == 1) res += exp;
            exp *= 2;
        }
        return res;
    }

    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

    public static void main(String[] args) {
        int m = 600000000;
        int n = 2147483645;

        Solution solution = new Solution();
        int res = solution.rangeBitwiseAnd_mine(m, n);
        System.out.println(res);
    }
}
