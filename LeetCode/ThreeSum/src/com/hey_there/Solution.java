package com.hey_there;

import java.util.*;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> threeNums_SumZero = new ArrayList<>();

        //若数组长度小于3
        if (nums.length < 3) {
            return threeNums_SumZero;
        }

        //获取数组中数字及其出现次数的键值对
        HashMap<Integer, Integer> numsOccurrence = occurrenceOfNums(nums);

        //处理空指针
        if (null == numsOccurrence) {
            throw new NullPointerException("输入数组为空，无法计算！");
        }

        //先处理特殊情况，即三个0的情况
        if (numsOccurrence.containsKey(0) && numsOccurrence.get(0) >= 3) {
            threeNums_SumZero.add(Arrays.asList(0, 0, 0));//将三个0的List加入大List中
        }

        //获取所有键的集合
        Set<Integer> keys = numsOccurrence.keySet();
        //处理三元组中有两个相同数字的情况
        for (int key : keys) {
            if (0 == key) {//避免在只有两个0的情况下出现[0,0,0]三元组
                continue;
            }
            if (numsOccurrence.get(key) >= 2 &&
                    numsOccurrence.containsKey(key * (-2))) {
                threeNums_SumZero.add(Arrays.asList(key, key, key * (-2)));
            }
        }

        //将KeySet有序化
        List<Integer> keysList = new ArrayList<>(keys);
        //处理三元组中三个数字均不相同的情况
        for (int i = 0; i < keysList.size(); i++) {
            for (int j = i + 1; j < keysList.size(); j++) {
                int twoSum = keysList.get(i) + keysList.get(j);
                if (numsOccurrence.containsKey((-1) * twoSum)) {//若KeySet中包含twoSum的相反数
                    //twoSum的相反数在keysList中的位置必须在j之后，否则将出现重复
                    if (keysList.indexOf((-1) * twoSum) > j) {
                        threeNums_SumZero.add(Arrays.asList(keysList.get(i), keysList.get(j), twoSum * (-1)));
                    }
                }
            }
        }

        return threeNums_SumZero;
    }

    /**
     * 以HashMap的形式返回数组中不同数字及其出现次数
     *
     * @param nums 一个整型数组
     * @return 数组中不同数字及其出现次数的键值对
     */
    //@Contract(pure = true)
    private HashMap<Integer, Integer> occurrenceOfNums(int[] nums) {
        //判断数组长度，若为0，则返回null
        if (0 == nums.length) {
            return null;
        }

        //包含键值对的HashMap
        HashMap<Integer, Integer> numsOccurrenceMap = new HashMap<>();
        for (int num : nums) {
            //检查HashMap中是否包含nums的第i个元素
            if (numsOccurrenceMap.containsKey(num)) {
                //若包含，则将对应value值加一
                int value = numsOccurrenceMap.get(num);
                numsOccurrenceMap.replace(num, value + 1);
                continue;//修改value值后继续下一次循环
            }

            //若不包含，说明遇到新数字，将其加入HashMap中
            numsOccurrenceMap.put(num, 1);
        }

        //循环结束后，得到所有数字与其出现次数的键值对，返回
        return numsOccurrenceMap;
    }

    /*public static void main(String[] args) {
        Solutions solutions = new Solutions();
        int[] nums = {-1, -1, 0, 1, 2, 3, 3, 2, 4, 4, 4, 5};
        System.out.println(solutions.numsOccurrence(nums));
    }*/
}
