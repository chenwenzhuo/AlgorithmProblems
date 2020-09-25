package com.hey_there.H3C_September_25th;

import java.util.ArrayList;

public class Problem_1 {
    public String character_auto_complete(String str) {
        ArrayList<String> weekdays = new ArrayList<>(7);
        weekdays.add("Monday");
        weekdays.add("Tuesday");
        weekdays.add("Wednesday");
        weekdays.add("Thursday");
        weekdays.add("Friday");
        weekdays.add("Saturday");
        weekdays.add("Sunday");

        StringBuilder builder = new StringBuilder();
        for (String today : weekdays) {
            int idx = today.indexOf(str);
            if (idx == 0) builder.append(today).append(" ");
        }
        if (builder.length() > 0) return builder.toString().trim();
        return "No match";
    }
}
