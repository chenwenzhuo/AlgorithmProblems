package com.hey_there.SmallestKNumbers;

import java.util.Arrays;

public class Solution {
    public int[] getLeastNumbers_sort(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    public int[] getLeastNumbers_heapSort1(int[] arr, int k) {
        //将数组中前k个值调整为大顶堆
        for (int i = (k - 1) / 2; i >= 0; i--) {
            maxHeapAdjust(arr, i, k - 1);
        }

        //从第k+1个位置开始遍历数组
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < arr[0]) {//若找到比大顶堆的堆顶更小的值
                //交换
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;
                //重新调整前k个值为大顶堆
                maxHeapAdjust(arr, 0, k - 1);
            }
        }

        //取出前k个值返回
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    public int[] getLeastNumbers_heapSort2(int[] arr, int k) {
        int len = arr.length;
        //将数组调整为一个小顶堆
        for (int i = (len - 1) / 2; i >= 0; i--) {
            minHeapAdjust(arr, i, len - 1);
        }

        int sortedLen = 0;//有序序列的长度
        while (sortedLen < k) {
            //将小顶堆的根结点与最末尾的叶结点交换
            int temp = arr[0];
            arr[0] = arr[len - 1 - sortedLen];
            arr[len - 1 - sortedLen] = temp;
            sortedLen++;//有序部分长度增加

            //将交换后的数组继续调整为小顶堆
            minHeapAdjust(arr, 0, len - 1 - sortedLen);
        }

        //取出最后k个返回
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[len - 1 - i];
        }
        return ans;
    }

    //将数组中heap[start]到heap[end]范围内的元素调整为大顶堆
    private void maxHeapAdjust(int[] heap, int start, int end) {
        for (int i = start * 2 + 1; i <= end; i = i * 2 + 1) {
            //计算start结点的较大子节点的位置
            if (i < end && heap[i] < heap[i + 1]) {
                i++;
            }

            //若根结点start的值较大，则此子树是大顶堆，退出循环
            if (heap[start] >= heap[i]) {
                break;
            }
            //若根结点start值小于子节点，交换两者的位置
            int temp = heap[start];
            heap[start] = heap[i];
            heap[i] = temp;
            start = i;//更新start的值
        }
    }

    private void minHeapAdjust(int[] heap, int start, int end) {
        for (int i = start * 2 + 1; i <= end; i = i * 2 + 1) {
            //找出当前start结点的值较小的子结点下标
            if (i < end && heap[i] > heap[i + 1]) {
                i++;
            }

            if (heap[start] <= heap[i]) {
                break;
            }

            int temp = heap[start];
            heap[start] = heap[i];
            heap[i] = temp;
            start = i;//更新start的值
        }
    }

    public static void main(String[] args) {
        //int[] arr = {0, 1, 2, 1};
        int[] arr = {0, 1, 1, 3, 4, 4, 1, 3, 2, 2};

        Solution solution = new Solution();
        int[] ans = solution.getLeastNumbers_heapSort1(arr, 5);
        for (int n : ans) {
            System.out.print(n + "   ");
        }
        System.out.println();
    }
}
