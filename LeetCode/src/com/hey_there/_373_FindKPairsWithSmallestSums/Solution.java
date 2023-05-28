package com.hey_there._373_FindKPairsWithSmallestSums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //只需要取数组前k个元素，否则对于过于巨大的数组，会超过内存限制
        nums1 = Arrays.copyOfRange(nums1, 0, Math.min(nums1.length, k));
        nums2 = Arrays.copyOfRange(nums2, 0, Math.min(nums2.length, k));
        int len1 = nums1.length, len2 = nums2.length;
        long total = ((long) len1) * ((long) len2);//可能的数对的总数
        k = (int) Math.min(k, total);//k可能比两数组所有可能的数对数量都大，所以需要在二者中取较小者
        //两元素之和最小为nums1[0]+nums2[0]，最大为nums1[len1-1]+nums2[len2-1]
        int minSum = nums1[0] + nums2[0];
        int maxSum = nums1[len1 - 1] + nums2[len2 - 1];
        int threshold = 0;
        //二分查找一个阈值threshold，使得相加小于阈值的数对数量小于k
        while (minSum <= maxSum) {
            int midSum = minSum + (maxSum - minSum) / 2;
            //统计相加小于等于midSum的数对数量
            //left是nums1的下标，right是nums2的下标
            int right = len2 - 1, cnt = 0;
            for (int left = 0; left < len1; left++) {
                while (right >= 0 && nums1[left] + nums2[right] > midSum)
                    right--;
                cnt += (right + 1);
            }
            if (cnt >= k) {
                maxSum = midSum - 1;
                threshold = midSum;
            } else minSum = midSum + 1;
        }
        List<List<Integer>> ans = new ArrayList<>();
        //将相加小于threshold的数对加入ans集合
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                int sum = nums1[i] + nums2[j];
                if (sum >= threshold)
                    break;
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                ans.add(list);
            }
        }
        //将相加等于threshold的数对加入ans集合
        //对于nums1的每个下标i，二分查找nums2的下标j，使得nums1[i]+nums2[j]==threshold
        for (int i = 0; i < len1; i++) {
            //对于当前i，两数之和最小值大于阈值，或最大值小于阈值，则直接跳过查找
            if (nums1[i] + nums2[0] > threshold || nums1[i] + nums2[len2 - 1] < threshold)
                continue;
            int left = 0, right = len2 - 1;
            //查找j的左边界
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums1[i] + nums2[mid] < threshold)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            int low = -1, high = -1;//左右边界下标
            if (nums1[i] + nums2[left] == threshold)
                low = left;//相加等于threshold，则left为有效值
            if (low == -1) {//未找到有效左边界，则对于当前i，没有有效j
                while (i < len1 - 1 && nums1[i] == nums1[i + 1])
                    i++;//跳过所有相同的nums1[i]
                continue;
            }

            //左边界有效时，查找右边界
            left = low;
            right = len2 - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums1[i] + nums2[mid] > threshold)
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            high = right;//左边界有效，则右边界一定有效

            for (int j = low; j <= high; j++) {
                if (ans.size() >= k) break;
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                ans.add(list);
            }

            //对于连续相同的nums1[i]，j的左右边界也相同
            while (i < len1 - 1 && nums1[i] == nums1[i + 1]) {
                for (int j = low; j <= high; j++) {
                    if (ans.size() >= k) break;
                    List<Integer> list = new ArrayList<>();
                    list.add(nums1[i]);
                    list.add(nums2[j]);
                    ans.add(list);
                }
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 9530;
        List<List<Integer>> ans = solution.kSmallestPairs(nums1, nums2, k);
        System.out.println(ans);
    }
}
