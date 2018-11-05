package com.heythere;

public class Solutions {
    public String intToRoman(int num) {
        //检查数字是否可转换
        if (num >= 4000 || num < 0) {
            throw new IllegalArgumentException("可转换的数字范围：1~3999，参数不在范围内！");
        }

        StringBuilder romanBuilder = new StringBuilder();
        executeSingle(romanBuilder, num);
        executeTen(romanBuilder, num);
        executeHundred(romanBuilder, num);
        executeThousand(romanBuilder, num);

        return romanBuilder.toString();
    }

    private void executeSingle(StringBuilder romanBuilder, int num) {
        int single = num % 10;
        if (0 == single) {
            return;
        }
        switch (single) {
            case 1:
                romanBuilder.insert(0, "I");
                break;
            case 2:
                romanBuilder.insert(0, "II");
                break;
            case 3:
                romanBuilder.insert(0, "III");
                break;
            case 4:
                romanBuilder.insert(0, "IV");
                break;
            case 5:
                romanBuilder.insert(0, "V");
                break;
            case 6:
                romanBuilder.insert(0, "VI");
                break;
            case 7:
                romanBuilder.insert(0, "VII");
                break;
            case 8:
                romanBuilder.insert(0, "VIII");
                break;
            case 9:
                romanBuilder.insert(0, "IX");
        }
    }

    private void executeTen(StringBuilder romanBuilder, int num) {
        int ten = (num % 100) / 10;
        if (0 == ten) {
            return;
        }
        switch (ten) {
            case 1:
                romanBuilder.insert(0, "X");
                break;
            case 2:
                romanBuilder.insert(0, "XX");
                break;
            case 3:
                romanBuilder.insert(0, "XXX");
                break;
            case 4:
                romanBuilder.insert(0, "XL");
                break;
            case 5:
                romanBuilder.insert(0, "L");
                break;
            case 6:
                romanBuilder.insert(0, "LX");
                break;
            case 7:
                romanBuilder.insert(0, "LXX");
                break;
            case 8:
                romanBuilder.insert(0, "LXXX");
                break;
            case 9:
                romanBuilder.insert(0, "XC");
                break;
        }
    }

    private void executeHundred(StringBuilder romanBuilder, int num) {
        int hundred = (num % 1000) / 100;
        if (0 == hundred) {
            return;
        }
        switch (hundred) {
            case 1:
                romanBuilder.insert(0, "C");
                break;
            case 2:
                romanBuilder.insert(0, "CC");
                break;
            case 3:
                romanBuilder.insert(0, "CCC");
                break;
            case 4:
                romanBuilder.insert(0, "CD");
                break;
            case 5:
                romanBuilder.insert(0, "D");
                break;
            case 6:
                romanBuilder.insert(0, "DC");
                break;
            case 7:
                romanBuilder.insert(0, "DCC");
                break;
            case 8:
                romanBuilder.insert(0, "DCCC");
                break;
            case 9:
                romanBuilder.insert(0, "CM");
                break;
        }
    }

    private void executeThousand(StringBuilder romanBuilder, int num) {
        int thousand = num / 1000;
        if (0 == thousand) {
            return;
        }
        switch (thousand) {
            case 1:
                romanBuilder.insert(0, "M");
                break;
            case 2:
                romanBuilder.insert(0, "MM");
                break;
            case 3:
                romanBuilder.insert(0, "MMM");
                break;
        }
    }
}
