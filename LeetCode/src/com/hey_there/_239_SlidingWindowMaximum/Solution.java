package com.hey_there._239_SlidingWindowMaximum;

import java.util.LinkedList;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        //用双端队列实现单调队列（队头到队尾递减）
        LinkedList<Integer> deque = new LinkedList<>();
        //将数组元素依次加入队列
        for (int i = 0; i < n; i++) {
            //加入前，将队列中小于当前元素的值都移除
            //因为这些元素总是先于nums[i]被移出窗口，而nums[i]比这些值大，
            //所以这些值一定不会成为某个时刻窗口中的最大值
            while (!deque.isEmpty() && deque.getLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);//加入队尾

            //窗口大小超过k时，需要从窗口中移除最早加入的元素，即nums[i-k]
            //但nums[n-k]可能已经在加入元素的过程中被移除，
            //所以需要判断队头元素是否等于nums[i-k]
            if (i >= k && deque.getFirst() == nums[i - k])
                deque.removeFirst();
            //窗口大小达到k，则从中取最大值
            if (i >= k - 1)
                res[i - k + 1] = deque.getFirst();
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] res = solution.maxSlidingWindow(nums, k);
        for (int r : res) System.out.print(r + "  ");
    }
}
