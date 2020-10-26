package com.hey_there.Beike_October_26th;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < testCases; i++) {
            //输入三个整数
            String[] line = sc.nextLine().split(" ");
            long[] inputs = new long[3];
            inputs[0] = Long.parseLong(line[0]);
            inputs[1] = Long.parseLong(line[1]);
            inputs[2] = Long.parseLong(line[2]);
            Arrays.sort(inputs);//排序

            if (inputs[1] == inputs[2]) {
                System.out.println("YES");
                System.out.println(inputs[1] + " " + inputs[0] + " " + inputs[1]);
            } else {
                System.out.println("NO");
            }
        }
        sc.close();
    }

    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < testCases; i++) {
            //输入三个整数
            String[] line = sc.nextLine().split(" ");
            int[] inputs = new int[3];
            inputs[0] = Integer.parseInt(line[0]);
            inputs[1] = Integer.parseInt(line[1]);
            inputs[2] = Integer.parseInt(line[2]);

            if (inputs[0] == inputs[1] && inputs[0] >= inputs[2]) {
                System.out.println("YES");
                System.out.println(inputs[2] + " " + inputs[2] + " " + inputs[0]);
            } else if (inputs[0] == inputs[2] && inputs[0] >= inputs[1]) {
                System.out.println("YES");
                System.out.println(inputs[1] + " " + inputs[1] + " " + inputs[0]);
            } else if (inputs[1] == inputs[2] && inputs[1] >= inputs[0]) {
                System.out.println("YES");
                System.out.println(inputs[0] + " " + inputs[0] + " " + inputs[1]);
            } else {
                System.out.println("NO");
            }
        }
        sc.close();
    }*/
}
