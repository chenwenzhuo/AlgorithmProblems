package com.hey_there.InterviewProblem_41_FindMedianFromDataStream;

import java.util.LinkedList;

//使用链表实现，在测试用例很长的情况下超时
public class MedianFinder_1 {
    private LinkedList<Integer> sequence;

    public MedianFinder_1() {
        this.sequence = new LinkedList<>();
    }

    public void addNum(int num) {
        int lenSeq = sequence.size();
        if (lenSeq == 0) {
            sequence.add(num);
            return;
        }
        //二分搜索插入位置
        int left = 0, right = lenSeq - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            int valAtMid = sequence.get(mid);
            if (valAtMid > num) right = mid;
            else if (valAtMid < num) left = mid + 1;
            else {
                left = mid;
                right = mid;
            }
        }
        //将num插入链表
        if (num <= sequence.get(left)) sequence.add(left, num);
        else sequence.add(left + 1, num);
    }

    public double findMedian() {
        int lenSeq = sequence.size();
        if (lenSeq % 2 == 1) return sequence.get(lenSeq / 2);
        else {
            double n1 = sequence.get(lenSeq / 2);
            double n2 = sequence.get(lenSeq / 2 - 1);
            return (n1 + n2) / 2;
        }
    }

    public static void main(String[] args) {
        MedianFinder_1 finder = new MedianFinder_1();
        finder.addNum(1);
        finder.addNum(2);
        System.out.println(finder.findMedian());
        System.out.println(finder.sequence);
        finder.addNum(3);
        System.out.println(finder.findMedian());
        System.out.println(finder.sequence);
    }
}
