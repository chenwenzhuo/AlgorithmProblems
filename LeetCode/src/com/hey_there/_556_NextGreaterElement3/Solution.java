package com.hey_there._556_NextGreaterElement3;

public class Solution {
    public int nextGreaterElement(int n) {
        int len_n = 0;//n的数字位数
        int temp = n;
        while (temp > 0) {//计算n是几位数
            len_n++;
            temp /= 10;
        }
        //将n的各数位上的值存入数组
        int[] digits = new int[len_n];
        temp = n;
        for (int i = len_n - 1; i >= 0; i--) {
            digits[i] = temp % 10;
            temp /= 10;
        }
        //从后向前遍历数组，若后一个数字大于前一个，记录前一个下标
        int target = -1;
        for (int i = len_n - 1; i > 0; i--) {
            if (digits[i] > digits[i - 1]) {
                target = i - 1;
                break;
            }
        }
        //若未找到从后往前降序的两个数字，则没有下一个更大的数
        if (target == -1) return -1;
        //找到有效target，再次从后向前遍历，寻找第一个比digit[target]大的数
        //此时区间[target+1,len_n)一定是降序，找到的一定是最小的、比digit[target]大的数
        for (int i = len_n - 1; i > target; i--) {
            if (digits[i] > digits[target]) {//找到后交换二者位置
                temp = digits[i];
                digits[i] = digits[target];
                digits[target] = temp;
                break;
            }
        }
        //交换后区间[target+1,len_n)仍保持降序，将区间反转，得到比n大的最小数
        int left = target + 1, right = len_n - 1;
        while (left < right) {
            temp = digits[left];
            digits[left] = digits[right];
            digits[right] = temp;
            left++;
            right--;
        }
        //将数组转为整数
        long ans = 0;
        for (int i = 0; i < len_n; i++) {
            ans *= 10;
            ans += digits[i];
        }
        //超出32位整数范围，返回-1，否则返回ans
        return ans > (long) Integer.MAX_VALUE ? -1 : (int) ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.nextGreaterElement(2147483476);
        System.out.println(ans);
    }
}
