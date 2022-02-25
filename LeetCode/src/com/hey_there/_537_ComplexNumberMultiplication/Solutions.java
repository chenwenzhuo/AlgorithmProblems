package com.hey_there._537_ComplexNumberMultiplication;

public class Solutions {
    public String complexNumberMultiply(String num1, String num2) {
        String[] realVirtual1 = extractRealVirtual(num1);
        String[] realVirtual2 = extractRealVirtual(num2);
        int real1 = Integer.parseInt(realVirtual1[0]);
        int virtual1 = Integer.parseInt(realVirtual1[1]);
        int real2 = Integer.parseInt(realVirtual2[0]);
        int virtual2 = Integer.parseInt(realVirtual2[1]);

        int resReal = real1 * real2 + virtual1 * virtual2 * (-1);
        int resVirtual = real1 * virtual2 + real2 * virtual1;
        return resReal + "+" + resVirtual + "i";
    }

    //输入一个复数，以数组形式返回其实部和虚部
    //输入的复数一定是 a+bi 的形式，即使a，b为0
    private String[] extractRealVirtual(String complexNum) {
        int len = complexNum.length();
        String[] res = new String[2];
        /* 判断字符串中是否有加号字符'+'，
         * 若有'+'，则同时有实部和虚部，
         * 若没有'+'，则实部和虚部最多含有一个*/
        int idxPlus = complexNum.indexOf('+');//'+'在字符串中的下标
        res[0] = complexNum.substring(0, idxPlus);
        res[1] = complexNum.substring(idxPlus + 1, len - 1);
        return res;
    }
}
