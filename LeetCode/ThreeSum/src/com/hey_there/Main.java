package com.hey_there;

public class Main {

    public static void main(String[] args) {
        //int[] nums = {-1, 0, 0, 1, 2, -1, -4};
        int[] nums = {0, 0, 0};

        Solution solution = new Solution();
        System.out.println(solution.threeSum(nums));

        EffectiveSolutions effectiveSolutions = new EffectiveSolutions();
        System.out.println(effectiveSolutions.threeSum_LRPointer(nums));
    }
}
