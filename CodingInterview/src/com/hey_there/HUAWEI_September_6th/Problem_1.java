package com.hey_there.HUAWEI_September_6th;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> strList = new ArrayList<>();
        while (true) {
            String curLine = sc.nextLine();
            if (curLine.equals(""))break;
            else strList.add(curLine);
        }
        System.out.println(strList);
        String specified = strList.get(strList.size() - 1);
        String A = strList.get(strList.size() - 2);
        strList.remove(strList.size() - 1);
        strList.remove(strList.size() - 1);

        StringBuilder flagValBuilder = new StringBuilder();
        char[] chs_specified = specified.toCharArray();
        char a = A.charAt(0);
        for (char ch : chs_specified) {
            if (ch<a) flagValBuilder.append(ch);
        }

    }
}
