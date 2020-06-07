package com.hey_there.Vivo_June_7th;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalLines = sc.nextInt();

        int[][] inputs = new int[totalLines][];
        sc.nextLine();
        int totalElements = 0;
        for (int i = 0; i < totalLines; i++) {
            String line = sc.nextLine();
            String[] numStr = line.split(" ");
            int[] nums = new int[numStr.length];
            for (int j = 0; j < nums.length; j++) {
                nums[j] = Integer.parseInt(numStr[j]);
            }
            inputs[i] = nums;
            totalElements += nums.length;
        }
        int[] inputs_1D = new int[totalElements];
        int idx = 0;
        for (int[] inputLine : inputs) {
            for (int n : inputLine) {
                inputs_1D[idx] = n;
                idx++;
            }
        }
        System.out.println("result:");
        Arrays.sort(inputs_1D);
        for (int n : inputs_1D) {
            System.out.print(n + "  ");
        }
        System.out.println();
    }
}
