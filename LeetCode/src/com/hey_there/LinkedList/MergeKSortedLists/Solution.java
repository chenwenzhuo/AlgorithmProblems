package com.hey_there.LinkedList.MergeKSortedLists;

import com.hey_there.LinkedList.ListNode;

public class Solution {
    /*将合并 k 个链表转化成合并两个链表的问题
    * 使用两个链表数组 prevLists 和 nextLists
    * 将 prevLists 中第1，2个链表合并，第3，4个链表合并...放入 nextLists 中
    * 知道只剩一个链表为止
    * 时间复杂度：O(Nlogk) ，其中 k 是链表的数目，N 是结点总数*/
    public ListNode mergeKLists_divideAndConquer(ListNode[] lists) {
        int numOfLists = lists.length;//未合并的链表的数量
        //处理特殊值
        if (numOfLists == 0) {
            return null;
        } else if (numOfLists == 1) {
            return lists[0];
        }

        ListNode[] prevLists = lists;
        ListNode[] nextLists;
        //当还有两个及以上链表未合并时，继续循环
        while (numOfLists >= 2) {
            //初始化此次循环要使用的 nextLists
            if (numOfLists % 2 == 0) {
                //若有偶数个链表未合并
                nextLists = new ListNode[numOfLists / 2];
            } else {
                //若有奇数个链表未合并
                nextLists = new ListNode[numOfLists / 2 + 1];
            }

            // prevLists 和 nextLists 的下标
            int indexPrevLists = 0, indexNextLists = 0;

            //将prevLists 中的链表合并，放到 nextLists 中
            while (indexPrevLists < numOfLists) {
                //仅当numOfLists为奇数时可能成立，将多出来的一个链表放到nextLists数组的最后一位
                if (indexPrevLists + 1 == numOfLists) {
                    nextLists[indexNextLists] = prevLists[indexPrevLists];
                    break;
                }

                //合并prevLists中的两个链表，合并后的大链表放在nextLists中
                nextLists[indexNextLists] =
                        mergeTwoSortedLists(prevLists[indexPrevLists], prevLists[indexPrevLists + 1]);

                //更改下标的值
                indexPrevLists += 2;
                indexNextLists += 1;
            }
            //此次循环的 nextLists 就是下次循环的 prevLists
            prevLists = nextLists;
            numOfLists = prevLists.length;//更新未合并的链表数量
        }
        return prevLists[0];
    }

    /*逐一两两合并链表
     * 将合并 k 个链表的问题转化成合并 2 个链表 k-1 次
     * 时间复杂度： O(kN) ，其中 k 是链表的数目，N 是结点总数*/
    public ListNode mergeKLists_mergeTwoAtATime(ListNode[] lists) {
        int numOfLists = lists.length;//未合并的链表的数量
        //处理特殊值
        if (numOfLists == 0) {
            return null;
        } else if (numOfLists == 1) {
            return lists[0];
        }

        for (int i = 0; i < numOfLists - 1; i++) {
            lists[i + 1] = mergeTwoSortedLists(lists[i], lists[i + 1]);
        }

        return lists[numOfLists - 1];
    }

    private ListNode mergeTwoSortedLists(ListNode list_1, ListNode list_2) {
        ListNode mergedListHead = new ListNode(-1);//合并后的链表的头结点
        ListNode mergedListTail = mergedListHead;

        while (list_1 != null || list_2 != null) {//只要两个链表其中之一未遍历完，就继续遍历

            //若两个链表均未遍历完
            if (list_1 != null && list_2 != null) {
                //取两个链表当前结点中值较小的一个
                if (list_1.val < list_2.val) {
                    mergedListTail.next = list_1;
                    list_1 = list_1.next;
                } else {
                    mergedListTail.next = list_2;
                    list_2 = list_2.next;
                }
                mergedListTail = mergedListTail.next;//合并后的大链表的尾结点引用向后移
                continue;
            }

            //若两链表其中之一已经遍历完
            //将未遍历完的链表的剩余部分拼接在大链表之后
            if (list_1 == null) {
                mergedListTail.next = list_2;
            } else {
                mergedListTail.next = list_1;
            }
            break;
        }

        return mergedListHead.next;
    }

    public static void main(String[] args) {
        ListNode list_1 = new ListNode(5);
        list_1.next = new ListNode(6);
        list_1.next.next = new ListNode(9);

        ListNode list_2 = new ListNode(2);
        list_2.next = new ListNode(5);
        list_2.next.next = new ListNode(7);
        list_2.next.next.next = new ListNode(11);

        Solution solution = new Solution();
        ListNode merged = solution.mergeTwoSortedLists(list_1, list_2);
        ListNode node = merged;
        while (node != null) {
            System.out.print(node.val + "  ");
            node = node.next;
        }
        System.out.println();
    }
}
