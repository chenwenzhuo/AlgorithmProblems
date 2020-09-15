package com.hey_there.InterviewProblem_66_BuildArrayOfProducts;

public class Solution {
    public int[] constructArr_1(int[] a) {
        if (a == null || a.length == 0) return new int[0];
        /* 数组a的前缀积
         * prefixProd[i]表示a[0]到a[i-1]的乘积*/
        int[] prefixProd = new int[a.length];
        prefixProd[0] = 1;//规定prefixProd[0]为1
        for (int i = 1; i < a.length; i++) {
            prefixProd[i] = prefixProd[i - 1] * a[i - 1];
        }
        /* 数组a的后缀积
         * suffixProd[i]表示a[i+1]到a[n-1]的乘积，n是数组a的长度*/
        int[] suffixProd = new int[a.length];
        suffixProd[a.length - 1] = 1;//规定suffixProd[a.length - 1]为1
        for (int i = a.length - 2; i >= 0; i--) {
            suffixProd[i] = suffixProd[i + 1] * a[i + 1];
        }

        int[] res = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = prefixProd[i] * suffixProd[i];
        }
        return res;
    }

    public int[] constructArr_2(int[] a) {
        if (a == null || a.length == 0) return new int[0];
        int[] res = new int[a.length];
        res[0] = 1;
        for (int i = 1; i < a.length; i++) {
            res[i] = res[i - 1] * a[i - 1];
        }
        int temp = 1;
        for (int i = a.length - 1; i >= 0; i--) {
            res[i] *= temp;
            temp *= a[i];
        }
        return res;
    }
}
