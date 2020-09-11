package com.hey_there.NetEasy_September_11th;

import java.util.HashMap;

public class Problem_2 {
    public String compress(String raw_str) {
        //a 4
        //b 5
        //c 6
        HashMap<Integer, Character> map = new HashMap<Integer, Character>() {{
            put(4, 'a');
            put(5, 'b');
            put(6, 'c');
            put(7, 'd');
            put(8, 'e');
            put(9, 'f');
            put(10, 'g');
            put(11, 'h');
            put(12, 'i');
            put(13, 'j');
            put(14, 'k');
            put(15, 'l');
            put(16, 'm');
            put(17, 'n');
            put(18, 'o');
            put(19, 'p');
            put(20, 'q');
            put(21, 'r');
            put(22, 's');
            put(23, 't');
            put(24, 'u');
            put(25, 'v');
            put(26, 'w');
            put(27, 'x');
            put(28, 'y');
            put(29, 'z');
            put(30, 'A');
            put(31, 'B');
            put(32, 'C');
            put(33, 'D');
            put(34, 'E');
            put(35, 'F');
            put(36, 'G');
            put(37, 'H');
            put(38, 'I');
            put(39, 'J');
            put(40, 'K');
            put(41, 'L');
            put(42, 'M');
            put(43, 'N');
            put(44, 'O');
            put(45, 'P');
            put(46, 'Q');
            put(47, 'R');
            put(48, 'S');
            put(49, 'T');
            put(50, 'U');
            put(51, 'V');
            put(52, 'W');
            put(53, 'X');
            put(54, 'Y');
            put(55, 'Z');

        }};
        int len_raw = raw_str.length();
        int low = 0, high = 0;
        StringBuilder builder = new StringBuilder();
        while (low < len_raw) {
            while (high < len_raw &&
                    raw_str.charAt(low) == raw_str.charAt(high) &&
                    (high - low < 55)) {
                high++;
            }
            int len_sub = high - low;
            //长度小于4，不压缩
            if (len_sub < 4) {
                builder.append(raw_str, low, high);
                low = high;
                continue;
            }
            //长度大于等于4：
            builder.append('0');
            builder.append(map.get(len_sub));
            builder.append(raw_str.charAt(low));
            low = high;
        }
        return builder.toString();
    }
}
