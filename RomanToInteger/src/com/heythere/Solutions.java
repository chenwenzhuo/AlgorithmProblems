package com.heythere;

public class Solutions {
    public int romanToInt(String s) {


        return 0;
    }

    private int executeThousand(String s, int startAt) {
        String thousand = s.substring(startAt, startAt + 3);

        if (thousand.equals("MMM")) {
            return 3;
        } else {
            thousand = s.substring(startAt, startAt + 2);
        }

        if (thousand.equals("MM")) {
            return 2;
        } else {
            thousand = s.substring(startAt, startAt + 1);
        }

        if (thousand.equals("M")) {
            return 1;
        }
        return 0;
    }
}
