package com.hey_there._2303_CalculateAmountPaidInTaxes;

public class Solution {
    public double calculateTax(int[][] brackets, int income) {
        if (income == 0) return 0;//没有收入，无需交税
        //收入不超过最低税级，直接计算
        if (income <= brackets[0][0])
            return (double) income * ((double) brackets[0][1] / 100d);
        //最低税级部分所需交的税
        double tax = (double) brackets[0][0] * ((double) brackets[0][1] / 100d);
        for (int i = 1; i < brackets.length; i++) {
            if (income > brackets[i][0]) {
                tax += (double) (brackets[i][0] - brackets[i - 1][0]) *
                        ((double) brackets[i][1] / 100d);
            } else {
                tax += (double) (income - brackets[i - 1][0]) *
                        ((double) brackets[i][1] / 100d);
                break;
            }
        }
        return tax;
    }
}
