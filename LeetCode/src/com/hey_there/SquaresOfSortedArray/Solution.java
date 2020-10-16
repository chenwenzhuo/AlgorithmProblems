package com.hey_there.SquaresOfSortedArray;

public class Solution {
    public int[] sortedSquares(int[] A) {
        if (A.length == 1) return new int[]{A[0] * A[0]};

        int[] res = new int[A.length];
        int resIdx = A.length - 1;
        int left = 0, right = A.length - 1;
        while (left <= right) {
            int leftSquare = A[left] * A[left];
            int rightSquare = A[right] * A[right];
            if (leftSquare >= rightSquare) {
                res[resIdx--] = leftSquare;
                left++;
            }
            else {
                res[resIdx] = rightSquare;
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = {-2, 0};
        Solution solution = new Solution();
        int[] res = solution.sortedSquares(A);
        for (int r : res) {
            System.out.print(r + "  ");
        }
        System.out.println();
    }
}
