package com.hey_there.Math.RomanToInteger;

import java.util.HashMap;

public class Solutions {
    private HashMap<String, Integer> thousandDigits;
    private HashMap<String, Integer> hundredDigits;
    private HashMap<String, Integer> tenDigits;
    private HashMap<String, Integer> singleDigits;

    private int romanStringIndex = 0;

    public int romanToInt(String s) {
        addThousandKV();
        addHundredKV();
        addTenKV();
        addSingleKV();

        int integerFromRoman = 0;
        integerFromRoman = integerFromRoman + getThousandDigit(s) * 1000;
        integerFromRoman = integerFromRoman + getHundredDigit(s) * 100;
        integerFromRoman = integerFromRoman + getTenDigit(s) * 10;
        integerFromRoman = integerFromRoman + getSingleDigit(s);

        return integerFromRoman;
    }

    private void addThousandKV() {
        thousandDigits = new HashMap<>();

        thousandDigits.put("M", 1);
        thousandDigits.put("MM", 2);
        thousandDigits.put("MMM", 3);
    }

    private void addHundredKV() {
        hundredDigits = new HashMap<>();

        hundredDigits.put("C", 1);
        hundredDigits.put("CC", 2);
        hundredDigits.put("CCC", 3);
        hundredDigits.put("CD", 4);
        hundredDigits.put("D", 5);
        hundredDigits.put("DC", 6);
        hundredDigits.put("DCC", 7);
        hundredDigits.put("DCCC", 8);
        hundredDigits.put("CM", 9);
    }

    private void addTenKV() {
        tenDigits = new HashMap<>();

        tenDigits.put("X", 1);
        tenDigits.put("XX", 2);
        tenDigits.put("XXX", 3);
        tenDigits.put("XL", 4);
        tenDigits.put("L", 5);
        tenDigits.put("LX", 6);
        tenDigits.put("LXX", 7);
        tenDigits.put("LXXX", 8);
        tenDigits.put("XC", 9);
    }

    private void addSingleKV() {
        singleDigits = new HashMap<>();

        singleDigits.put("I", 1);
        singleDigits.put("II", 2);
        singleDigits.put("III", 3);
        singleDigits.put("IV", 4);
        singleDigits.put("V", 5);
        singleDigits.put("VI", 6);
        singleDigits.put("VII", 7);
        singleDigits.put("VIII", 8);
        singleDigits.put("IX", 9);
    }

    private int getThousandDigit(String s) {
        String thousandDigitSubstring;

        for (int i = 3; i > 0; i--) {
            try {
                thousandDigitSubstring = s.substring(romanStringIndex, romanStringIndex + i);
            } catch (StringIndexOutOfBoundsException exception) {
                continue;
            }
            if (thousandDigits.containsKey(thousandDigitSubstring)) {
                romanStringIndex += thousandDigitSubstring.length();
                return thousandDigits.get(thousandDigitSubstring);
            }
        }
        return 0;
    }

    private int getHundredDigit(String s) {
        String hundredDigitSubstring;

        for (int i = 4; i > 0; i--) {
            try {
                hundredDigitSubstring = s.substring(romanStringIndex, romanStringIndex + i);
            } catch (StringIndexOutOfBoundsException exception) {
                continue;
            }
            if (hundredDigits.containsKey(hundredDigitSubstring)) {
                romanStringIndex += hundredDigitSubstring.length();
                return hundredDigits.get(hundredDigitSubstring);
            }
        }
        return 0;
    }

    private int getTenDigit(String s) {
        String tenDigitSubstring;

        for (int i = 4; i > 0; i--) {
            try {
                tenDigitSubstring = s.substring(romanStringIndex, romanStringIndex + i);
            } catch (StringIndexOutOfBoundsException exception) {
                continue;
            }
            if (tenDigits.containsKey(tenDigitSubstring)) {
                romanStringIndex += tenDigitSubstring.length();
                return tenDigits.get(tenDigitSubstring);
            }
        }
        return 0;
    }

    private int getSingleDigit(String s) {
        String singleDigitSubstring;

        for (int i = 4; i > 0; i--) {
            try {
                singleDigitSubstring = s.substring(romanStringIndex, romanStringIndex + i);
            } catch (StringIndexOutOfBoundsException exception) {
                continue;
            }
            if (singleDigits.containsKey(singleDigitSubstring)) {
                romanStringIndex += singleDigitSubstring.length();
                return singleDigits.get(singleDigitSubstring);
            }
        }
        return 0;
    }
}
