package com.heythere;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> threeNums = new ArrayList<>();
        int numbersCount = nums.length;//数组长度

        //对数组进行排序
        quickSort(nums, 0, numbersCount - 1);

        //用3个List分别存储数组中的正数，负数，0
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        List<Integer> zero = new ArrayList<>();

        List<Integer> positive_NoRepeat = new ArrayList<>();
        List<Integer> negative_NoRepeat = new ArrayList<>();

        for (int num : nums) {
            if (num > 0) {
                positive.add(num);
                if (!positive_NoRepeat.contains(num)) {
                    positive_NoRepeat.add(num);
                }
            } else if (num < 0) {
                negative.add(num);
                if (!negative_NoRepeat.contains(num)) {
                    negative_NoRepeat.add(num);
                }
            } else {
                zero.add(num);
            }
        }
        int positiveCount = positive.size();
        int negativeCount = negative.size();
        int zeroCount = zero.size();

        int positiveCount_NoRepeat = positive_NoRepeat.size();
        int negativeCount_NoRepeat = negative_NoRepeat.size();

        //若数组中有三个以上的0，则在List中加入三个0组成的三元组
        if (zeroCount >= 3) {
            addNewTriadToList(threeNums, 0, 0, 0);
        }

        //若数组中有0，且存在互为相反数的正负数
        if (zeroCount > 0) {
            List<Integer> addedPositive = new ArrayList<>();
            for (int aPositive : positive) {
                if (negative.contains(aPositive * (-1)) && (!addedPositive.contains(aPositive))) {
                    addNewTriadToList(threeNums, 0, aPositive, aPositive * (-1));
                    addedPositive.add(aPositive);
                }
            }
        }

        //检查是否存在由两正一负组成的三元组,其中两正数相等
        for (int i = 0; i < positiveCount; i++) {
            for (int j = i + 1; j < positiveCount; j++) {
                int pos_1 = positive.get(i);
                int pos_2 = positive.get(j);

                if (pos_1 == pos_2) {
                    int twoPosSum = pos_1 + pos_2;

                    if (negative.contains(twoPosSum * (-1))) {
                        addNewTriadToList(threeNums, pos_1, pos_2, twoPosSum * (-1));
                    }
                }
            }
        }
        //检查是否存在由两正一负组成的三元组,其中两正数不相等
        for (int i = 0; i < positiveCount_NoRepeat; i++) {
            for (int j = i + 1; j < positiveCount_NoRepeat; j++) {
                int pos_1 = positive_NoRepeat.get(i);
                int pos_2 = positive_NoRepeat.get(j);
                int twoPosSum = pos_1 + pos_2;

                if (negative.contains(twoPosSum * (-1))) {
                    addNewTriadToList(threeNums, pos_1, pos_2, twoPosSum * (-1));
                }
            }
        }

        //检查是否存在由两负一正组成的三元组,其中两负数相等
        for (int i = 0; i < negativeCount; i++) {
            for (int j = i + 1; j < negativeCount; j++) {
                int neg_1 = negative.get(i);
                int neg_2 = negative.get(j);

                if (neg_1 == neg_2) {
                    int twoNegSum = neg_1 + neg_2;

                    if (positive.contains(twoNegSum * (-1))) {
                        addNewTriadToList(threeNums, neg_1, neg_2, twoNegSum * (-1));
                    }
                }
            }
        }
        //检查是否存在由两负一正组成的三元组,其中两负数不相等
        for (int i = 0; i < negativeCount_NoRepeat; i++) {
            for (int j = i + 1; j < negativeCount_NoRepeat; j++) {
                int pos_1 = negative_NoRepeat.get(i);
                int pos_2 = negative_NoRepeat.get(j);
                int twoPosSum = pos_1 + pos_2;

                if (negative.contains(twoPosSum * (-1))) {
                    addNewTriadToList(threeNums, pos_1, pos_2, twoPosSum * (-1));
                }
            }
        }

        return threeNums;
    }

    private void addNewTriadToList(List<List<Integer>> threeNums, int num_1, int num_2, int num_3) {
        List<Integer> anotherThree = new ArrayList<>();
        anotherThree.add(num_1);
        anotherThree.add(num_2);
        anotherThree.add(num_3);

        threeNums.add(anotherThree);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = nums[left];//取基准值
        int low = left;
        int high = right;

        while (low < high) {
            while (low < high && nums[high] >= pivot) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] <= pivot) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        quickSort(nums, left, low - 1);
        quickSort(nums, low + 1, right);
    }


    //暴力搜索法，可行但复杂度过高
    public List<List<Integer>> threeSum_ViolentSearching(int[] nums) {
        List<List<Integer>> threeNums = new ArrayList<>();
        int length = nums.length;//整数数组的长度

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (0 == nums[i] + nums[j] + nums[k] &&
                            !checkExist(threeNums, nums[i], nums[j], nums[k])) {
                        List<Integer> anotherThree = new ArrayList<>();
                        anotherThree.add(nums[i]);
                        anotherThree.add(nums[j]);
                        anotherThree.add(nums[k]);

                        threeNums.add(anotherThree);
                    }
                }
            }
        }
        return threeNums;
    }

    private boolean checkExist(List<List<Integer>> threeNums, int num_1, int num_2, int num_3) {
        for (List<Integer> aList : threeNums) {
            int first = aList.get(0);
            int second = aList.get(1);
            int third = aList.get(2);

            if ((first == num_1 && second == num_2 && third == num_3) ||
                    (first == num_1 && second == num_3 && third == num_2) ||
                    (first == num_2 && second == num_1 && third == num_3) ||
                    (first == num_2 && second == num_3 && third == num_1) ||
                    (first == num_3 && second == num_1 && third == num_2) ||
                    (first == num_3 && second == num_2 && third == num_1)) {
                return true;
            }
        }
        return false;
    }
}
