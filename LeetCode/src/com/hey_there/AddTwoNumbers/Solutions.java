package com.hey_there.AddTwoNumbers;

public class Solutions {
    /**
     * 检查两参数合法后，获得将两链表表示的数字
     * 将两数字相加后再使用链表存储
     *
     * @param l1 第一个加数
     * @param l2 第二个加数
     * @return 返回以链表表示的两加数之和
     */
    public ListNode addTwoNumbersMySolution_1(ListNode l1, ListNode l2) {
        /*
        检查两链表是否为null，为null则抛出异常
         */
        if (null == l1 || null == l2) {
            throw new IllegalArgumentException("List cannot be null!");
        }

        /*
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

    /**
     * @param list 链表，从头至尾的节点分别存储了数字的个、十、百···位
     * @return 以int值返回list所表示的数字
     */
    public int getIntFromList(ListNode list) {
        int num = 0;//此链表表示的数字
        int index = 0;//10的n次方的指数

        while (null != list) {
            num += list.val * ((int) Math.pow(10, index));
            index++;
            list = list.next;
        }
        return num;
    }

    /**
     * @param list 链表，从头至尾的节点分别存储了数字的个、十、百···位
     * @return 以long值返回list所表示的数字
     */
    public long getLongFromList(ListNode list) {
        long num = 0;
        long index = 0;

        while (null != list) {
            num += list.val * ((long) Math.pow(10, index));
            index++;
            list = list.next;
        }

        return num;
    }

    /**
     * 逐项遍历两链表，
     * 将每个节点中的数字相加并向前进位
     *
     * @param l1 第一个加数
     * @param l2 第二个加数
     * @return 返回以链表表示的两加数之和
     */
    public ListNode addTwoNumbersMySolution_2(ListNode l1, ListNode l2) {
        if (null == l1 || null == l2) {
            throw new IllegalArgumentException("List cannot be null!");
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

        /*
        当l1_search和l2_search其中一个为空时退出循环，
        退出循环后，
        检查两链表是否还有后续节点需要处理
         */
        if (null == l1_search && null == l2_search && 0 != carry) {//若两者均为空，只需处理一个进位
            tail.next = new ListNode(carry);
            tail = tail.next;
        } else if (null != l1_search) {//若l1_search非空，处理后续节点
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
        } else if (null != l2_search) {//若l2_search非空，处理后续节点
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

    //官方题解
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);//使用带头节点的链表存储和，方便操作

        ListNode p = l1, q = l2;
        ListNode tail = dummyHead;//链表尾指针

        int carry = 0;//进位
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            int sum = carry + x + y;
            carry = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
