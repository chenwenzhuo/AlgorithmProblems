package com.hey_there.Beike_October_26th;

import java.util.Scanner;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < testCases; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int xySum = x + y;
            for (int z = 1; z <= Integer.MAX_VALUE; z++) {
                int sum = xySum + z;
                if (isPrime(sum)) {
                    System.out.println(z);
                    break;
                }
            }
        }
        sc.close();
    }

    private static boolean isPrime(int n) {
        if (n <= 3) return n > 1;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
