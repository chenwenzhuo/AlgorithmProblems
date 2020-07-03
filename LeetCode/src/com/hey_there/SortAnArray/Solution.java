package com.hey_there.SortAnArray;

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
        int inc = len;//排序增量
        while (inc > 1) {
            inc /= 2;//每趟排序都将增量缩小
            //逐个将nums[i]与nums[i - inc]比较
            for (int i = inc; i < len; i++) {
                //若后面的比前面的小
                if (nums[i] < nums[i - inc]) {
                    int temp = nums[i];//暂存较小的这个数
                    int j;
                    //以inc为步长向前，不断交换，找到temp的正确位置
                    for (j = i - inc; j >= 0 && nums[j] > temp; j -= inc) {
                        nums[j + inc] = nums[j];
                    }
                    nums[j + inc] = temp;//将temp存入
                }
            }
        }
        return nums;
    }

    public int[] sortArray_mergeSort_recursive(int[] nums) {
        int len = nums.length;
        int[] assist = new int[len];
        recursiveMergeSort(nums, assist, 0, nums.length - 1);
        return assist;
    }

    private void recursiveMergeSort(int[] array, int[] assist, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        //递归排序两个子序列
        recursiveMergeSort(array, assist, start, mid);
        recursiveMergeSort(array, assist, mid + 1, end);
        //归并两路递归的结果
        int low = start, high = mid + 1;
        int assistIndex = start;
        //当low和high在两段序列中都未走到头时，取较小者填入assist数组中
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
        //当low或high在各自的子序列中走到头时，分开处理另一个序列的剩余部分
        //以下两个循环只会执行其中一个
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

    public int[] sortArray_mergeSort_nonrecursive(int[] nums) {
        int len = nums.length;
        int[] assist = new int[len];
        int step = 2;//进行归并的步长
        int stepStart;
        while (step <= len) {
            stepStart = 0;
            //以step为步长进行合并
            while (stepStart + step <= len) {
                //每次调用merge方法，都会使[stepStart,stepStart + step - 1]闭区间内的数字有序
                merge(nums, assist, stepStart, stepStart + step / 2 - 1, stepStart + step - 1);
                stepStart += step;
            }
            //数组尾部可能存在不足一个步长的部分，单独对其进行排序
            if (stepStart <= len - 1) {
                merge(nums, assist, stepStart, stepStart + step / 2 - 1, len - 1);
            }
            step *= 2;//步长以2的幂增长
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
