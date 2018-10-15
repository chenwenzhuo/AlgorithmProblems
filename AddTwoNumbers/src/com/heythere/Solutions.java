package com.heythere;

public class Solutions {
    /**
     * 检查两参数合法后，获得将两链表表示的数字
     * 将两数字相加后再使用链表存储
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersMySolution(ListNode l1, ListNode l2) {
        /*
        检查两链表是否为null，为null则抛出异常
         */
        if (null == l1 || null == l2) {
            throw new IllegalArgumentException("List cannot be null!");
        }

        /*
        按照要求，可以假设除了数字 0 之外，这两个数字都不会以零开头。
        所以若第一个节点为0，
        则可认为此链表表示的数字为0，则可直接返回另一个
         */
        if (0 == l1.val) {
            return l2;
        }
        if (0 == l2.val) {
            return l1;
        }

        /*
        若两个链表表示的数字都不为0，
        获取两链表表示的数字
         */
        int firstNum = getIntFromList(l1);
        int secondNum = getIntFromList(l2);
        //求和
        int sum = firstNum + secondNum;

        /*
        将求得的和用链表存储
         */
        //将个位存入链表
        int digit = sum % 10;
        ListNode sumList = new ListNode(digit);
        //将其余位存入链表
        sum /= 10;//个位已存入，将其去除
        ListNode tail = sumList;//链表尾指针
        while (0 != sum) {
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            sum /= 10;
        }

        return sumList;
    }

    public int getIntFromList(ListNode listNode) {
        int num = 0;//此链表表示的数字
        int index = 0;//10的n次方的指数

        while (null != listNode) {
            num += listNode.val * ((int) Math.pow(10, index));
            index++;
            listNode = listNode.next;
        }
        return num;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1 || null == l2) {
            throw new IllegalArgumentException("List cannot be null!");
        }

        if (0 == l1.val) {
            return l2;
        }
        if (0 == l2.val) {
            return l1;
        }

        int carry = 0;//进位
        int tempSum = 0;
        ListNode sumList = null;//要返回的结果
        ListNode tail = null;//链表尾指针

        //定义两个引用遍历两链表
        ListNode l1_search = l1;
        ListNode l2_search = l2;

        //先将个位放入链表
        tempSum = l1_search.val + l2_search.val + carry;
        carry = tempSum / 10;
        sumList = new ListNode(tempSum % 10);
        tail = sumList;

        //开始处理后续节点前，将两引用指向第二个节点
        l1_search = l1_search.next;
        l2_search = l2_search.next;
        while (null != l1_search && null != l2_search) {
            tempSum = l1_search.val + l2_search.val + carry;
            carry = tempSum / 10;
            tail.next = new ListNode(tempSum % 10);

            tail = tail.next;
            l1_search = l1_search.next;
            l2_search = l2_search.next;
        }

        if (null == l1_search && null == l2_search && 0 != carry) {
            tail.next = new ListNode(carry);
            tail = tail.next;
        } else if (null != l1_search) {
            while (null != l1_search) {
                tempSum = l1_search.val + carry;
                carry = tempSum / 10;
                tail.next = new ListNode(tempSum % 10);

                tail = tail.next;
                l1_search = l1_search.next;
            }
            if (0 != carry) {
                tail.next = new ListNode(carry);
                tail = tail.next;
            }
        } else if (null != l2_search) {
            while (null != l2_search) {
                tempSum = l2_search.val + carry;
                carry = tempSum / 10;
                tail.next = new ListNode(tempSum % 10);

                tail = tail.next;
                l2_search = l2_search.next;
            }
            if (0 != carry) {
                tail.next = new ListNode(carry);
                tail = tail.next;
            }
        }

        return sumList;
    }
}
