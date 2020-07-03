package com.hey_there.HappyNumber;

public class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = digitSquareSum_slow(slow);
            fast = digitSquareSum_fast(fast);
        } while (slow != fast);

        return slow == 1;
    }

    private int digitSquareSum_slow(int n) {
        int sum = 0;
        while (n > 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }
        return sum;
    }

    private int digitSquareSum_fast(int n) {
        int sum = 0;
        while (n > 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }

        n = sum;
        sum = 0;
        while (n > 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isHappy(19));
    }
}
