package com.hey_there.DailyProblems.SortAnArray;

public class Solution {
    public int[] sortArray_heapSort(int[] nums) {
        int len = nums.length;
        //从最后一个非叶结点开始，从下往上，从右至左调整,构建大顶堆
        for (int i = (len - 1) / 2; i >= 0; i--) {
            adjustHeap(nums, i, len);
        }

        for (int j = 0; j < len - 1; j++) {
            //交换堆顶和堆底的值
            int temp = nums[0];
            nums[0] = nums[len - 1 - j];
            nums[len - 1 - j] = temp;
            //重新调整前len-j个元素为大顶堆
            adjustHeap(nums, 0, len - 1 - j);
        }
        return nums;
    }

    //adjustHeap每次调用都将根为start的子树调整为大顶堆
    //且不会调整end及以后的值
    private void adjustHeap(int[] array, int start, int end) {
        for (int i = start * 2 + 1; i < end; i = i * 2 + 1) {
            //获取值较大的子结点的下标
            if (i + 1 < end && array[i] < array[i + 1]) {
                i++;
            }

            if (array[start] < array[i]) {
                //若根结点比子结点小，则交换
                int temp = array[start];
                array[start] = array[i];
                array[i] = temp;
                //改变start的值，下次循环继续调整子树
                start = i;
            } else {
                break;
            }
        }
    }

    public int[] sortArray_bubbleSort(int[] nums) {
        int len = nums.length;
        int temp;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] > nums[j]) {
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    public int[] sortArray_selectionSort(int[] nums) {
        int len = nums.length;
        int maxValIndex, temp;
        for (int i = len; i > 0; i--) {
            maxValIndex = 0;
            for (int j = 1; j < i; j++) {
                if (nums[j] > nums[maxValIndex]) {
                    maxValIndex = j;
                }
            }
            if (maxValIndex != i - 1) {
                temp = nums[maxValIndex];
                nums[maxValIndex] = nums[i - 1];
                nums[i - 1] = temp;
            }
        }
        return nums;
    }

    public int[] sortArray_insertionSort(int[] nums) {
        int len = nums.length;
        int temp;
        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i - 1]) {
                temp = nums[i];
                int j;
                for (j = i - 1; j >= 0 && nums[j] > temp; j--) {
                    nums[j + 1] = nums[j];
                }
                nums[j + 1] = temp;
            }
        }
        return nums;
    }

    public int[] sortArray_shellSort(int[] nums) {
        int len = nums.length;
        int increment = len;
        while (increment > 1) {
            increment /= 2;
            for (int i = increment; i < len; i++) {
                if (nums[i] < nums[i - increment]) {
                    int temp = nums[i];
                    int j;
                    for (j = i - increment; j >= 0 && nums[j] > temp; j -= increment) {
                        nums[j + increment] = nums[j];
                    }
                    nums[j + increment] = temp;
                }
            }
        }
        return nums;
    }

    public int[] sortArray_mergingSort_recursive(int[] nums) {
        int len = nums.length;
        int[] assist = new int[len];
        recursiveMergingSort(nums, assist, 0, nums.length - 1);
        return assist;
    }

    private void recursiveMergingSort(int[] array, int[] assist, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        recursiveMergingSort(array, assist, start, mid);
        recursiveMergingSort(array, assist, mid + 1, end);
        //归并两路递归的结果
        int low = start, high = mid + 1;
        int assistIndex = start;
        while (low <= mid && high <= end) {
            if (array[low] < array[high]) {
                assist[assistIndex] = array[low];
                low++;
            } else {
                assist[assistIndex] = array[high];
                high++;
            }
            assistIndex++;
        }
        while (low <= mid) {
            assist[assistIndex] = array[low];
            low++;
            assistIndex++;
        }
        while (high <= end) {
            assist[assistIndex] = array[high];
            high++;
            assistIndex++;
        }
        //将assist数组中归并后的结果拷贝到原数组中
        System.arraycopy(assist, start, array, start, end - start + 1);
    }

    public int[] sortArray_mergingSort_nonrecursive(int[] nums) {
        int len = nums.length;
        int[] assist = new int[len];
        int step = 2;
        int stepStart;

        while (step <= len) {
            stepStart = 0;
            //以step为步长进行合并
            while (stepStart + step <= len) {
                merge(nums, assist, stepStart, stepStart + step / 2 - 1, stepStart + step - 1);
                stepStart += step;
            }
            //处理残余部分
            if (stepStart <= len - 1) {
                merge(nums, assist, stepStart, stepStart + step / 2 - 1, len - 1);
            }
            step *= 2;
        }
        //最后再从头到尾合并一遍
        merge(nums, assist, 0, step / 2 - 1, len - 1);
        return nums;
    }

    private void merge(int[] array, int[] assist, int start, int mid, int end) {
        int low = start, high = mid + 1;
        int assistIndex = start;
        while (low <= mid && high <= end) {
            if (array[low] < array[high]) {
                assist[assistIndex] = array[low];
                low++;
            } else {
                assist[assistIndex] = array[high];
                high++;
            }
            assistIndex++;
        }
        while (low <= mid) {
            assist[assistIndex] = array[low];
            low++;
            assistIndex++;
        }
        while (high <= end) {
            assist[assistIndex] = array[high];
            high++;
            assistIndex++;
        }
        //将assist数组中合并后的结果拷贝到原数组中
        System.arraycopy(assist, start, array, start, end - start + 1);
    }

    public int[] sortArray_quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = array[start];
        //两个辅助数组，分别存放array中比pivot小和大的数
        int[] assist_smaller = new int[end - start + 1];
        int[] assist_bigger = new int[end - start + 1];
        int index_smaller = 0, index_bigger = 0;//以上两数组中下一个课存放位置的下标
        //遍历array中从start到end的所有元素，存入辅助数组中
        for (int i = start + 1; i <= end; i++) {
            if (array[i] <= pivot) {
                assist_smaller[index_smaller] = array[i];
                index_smaller++;
            } else {
                assist_bigger[index_bigger] = array[i];
                index_bigger++;
            }
        }
        //将辅助数组中元素拷贝回原数组
        System.arraycopy(assist_smaller, 0, array, start, index_smaller);
        array[start + index_smaller] = pivot;//将枢纽值放回原数组
        System.arraycopy(assist_bigger, 0, array, start + index_smaller + 1, index_bigger);

        //递归对值pivot两边进行排序
        quickSort(array, start, start + index_smaller - 1);
        quickSort(array, start + index_smaller + 1, end);
    }

    public static void main(String[] args) {
        //int[] nums = {2, 4, 3, 6, 1, 5};
        //int[] nums = {3, -1};
        int[] nums = {5, 1, 1, 2, 0, 0};
        Solution solution = new Solution();
        nums = solution.sortArray_quickSort(nums);
        for (int n : nums) {
            System.out.print(n + "  ");
        }
        System.out.println();
    }
}
