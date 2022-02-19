package com.hey_there._969_PancakeSorting;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> revKs = new ArrayList<>();
        for (int n = arr.length; n > 1; n--) {
            int maxIdx = 0;//数组arr中[0,n)范围内的最大值的下标
            //寻找最大值下标
            for (int i = 1; i < n; i++) {
                if (arr[i] > arr[maxIdx]) maxIdx = i;
            }
            //若最大值已经在最后一位，不执行反转
            if (maxIdx == n - 1) continue;
            //否则执行两次反转
            reverseArr(arr, maxIdx);//k==maxIdx+1的煎饼反转，反转[0,maxIdx]
            reverseArr(arr, n - 1);//k==n的煎饼反转，反转[0,n-1]
            revKs.add(maxIdx + 1);
            revKs.add(n);
        }
        return revKs;
    }

    //反转数组中[0,end]的部分
    private void reverseArr(int[] arr, int end) {
        for (int i = 0, j = end; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
