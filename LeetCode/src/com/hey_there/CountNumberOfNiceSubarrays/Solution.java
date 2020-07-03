package com.hey_there.CountNumberOfNiceSubarrays;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int numberOfSubarrays_my(int[] nums, int k) {
        //存放所有奇数在nums中的下标
        List<Integer> oddIndexes = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                oddIndexes.add(i);
            }
        }
        //若奇数个数不足k个，返回0
        int numOdds = oddIndexes.size();
        if (numOdds < k) {
            return 0;
        }

        //双指针，表示奇数在nums中的下标，leftOdd与rightOdd之间共k个奇数
        int leftOdd, rightOdd;
        int prevOdd = -1;//nums中前一个奇数的下标
        int nextOdd;//nums中后一个奇数的下标
        //记录leftOdd和rightOdd在oddIndexes中的下标
        int leftOddIndex = 0, rightOddIndex = k - 1;
        int ans = 0;
        while (rightOddIndex < numOdds) {
            leftOdd = oddIndexes.get(leftOddIndex);
            rightOdd = oddIndexes.get(rightOddIndex);
            //若rightOddIndex之后还有奇数，则记录其在nums中的下标
            //否则nextOdd为nums.length
            if (rightOddIndex < numOdds - 1) {
                nextOdd = oddIndexes.get(rightOddIndex + 1);
            } else {
                nextOdd = nums.length;
            }

            int even_between_prev_left = leftOdd - (prevOdd + 1);
            int even_between_right_next = nextOdd - (rightOdd + 1);
            ans += (even_between_prev_left + even_between_right_next +
                    even_between_prev_left * even_between_right_next + 1);

            prevOdd = leftOdd;
            leftOddIndex++;
            rightOddIndex++;
        }

        return ans;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        /* list中存放奇数之间间隔的偶数数量
         * 例如对于nums={2,2,2,1,4,3,2,2}，list={3,1,2}*/
        List<Integer> list = new ArrayList<>(nums.length);
        int countEven = 0;
        for (int n : nums) {
            if (n % 2 == 0) {
                countEven++;
            } else {
                list.add(countEven);
                countEven = 0;
            }
        }
        list.add(countEven);

        int left = 0, right = k;
        int ans = 0;
        while (right < list.size()) {
            int leftEven = list.get(left);
            int rightEven = list.get(right);
            ans += (leftEven + rightEven + leftEven * rightEven + 1);

            left++;
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        //int[] nums = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int[] nums = {1, 1, 2, 1, 1};
        Solution solution = new Solution();
        int ans = solution.numberOfSubarrays(nums, 3);
        System.out.println(ans);
    }
}
