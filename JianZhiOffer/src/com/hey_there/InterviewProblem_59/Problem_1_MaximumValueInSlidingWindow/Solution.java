package com.hey_there.InterviewProblem_59.Problem_1_MaximumValueInSlidingWindow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution {
    public int[] maxSlidingWindow_1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        //使用大顶堆辅助遍历
        //java中PriorityQueue默认为小顶堆，需要自定义Comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n2 - n1;
            }
        });
        //存储窗口中最大值的数组
        int[] res = new int[nums.length - k + 1];
        int resIdx = 0;//res数组的下标
        //向大顶堆中存入k个值
        for (int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }
        //取堆中最大值存入list
        res[resIdx++] = maxHeap.peek();

        //将窗口向右滑动，每滑动一次就取一次最大值存入list
        for (int i = k; i < nums.length; i++) {
            maxHeap.add(nums[i]);//向堆中加入新值
            maxHeap.remove(nums[i - k]);//将滑出窗口范围的值从堆中移除
            res[resIdx++] = maxHeap.peek();
        }
        return res;
    }

    public int[] maxSlidingWindow_2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0)
            return new int[0];
        //存储窗口中最大值的数组
        int[] res = new int[nums.length - k + 1];
        //双端队列
        LinkedList<Integer> deque = new LinkedList<>();

        //形成窗口之前，将nums的前k个元素插入队列
        for (int i = 0; i < k; i++) {
            //从队尾向前，移除小于nums[i]的的所有元素
            while (!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);//将nums[i]加入队头
        }
        //将队头存入res数组
        res[0] = deque.peekFirst();
        //形成窗口之后，移动窗口，每移动一次就将窗口中的最大值加入res数组
        for (int i = k; i < nums.length; i++) {
            /* 窗口每移动一次，都要将移出窗口的数组元素从队列中删除
             * 增加一个判断是因为：
             * 队首元素是窗口中的最大值，而最大值不一定是此次被移出窗口的数组元素
             * 所以不能直接将队首删除*/
            if (deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            //从队尾向前，移除小于nums[i]的的所有元素
            while (!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[2];
        arr1[0] = 1;
        arr1[1] = 2;
        int[] arr2 = new int[2];
        arr2[0] = 1;
        arr2[1] = 2;
        System.out.println(Arrays.equals(arr1, arr2));
    }
}
