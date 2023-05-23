package com.hey_there._1011_CapacityToShipPackagesWithinDDays;

public class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int totalWeight = 0, maxWeight = 0;//总重量、最大的单个重量
        for (int w : weights) {
            totalWeight += w;
            maxWeight = Math.max(maxWeight, w);
        }
        //载重最低为maxWeight，最高为所有物品总重量
        int low = maxWeight, high = totalWeight;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            //计算对于载重mid，需要装载的次数
            int cnt = 0, weightOnShip = 0;
            for (int w : weights) {
                //对于载重mid，剩余的容量可以装下当前货物
                if (weightOnShip + w <= mid) {
                    weightOnShip += w;//将当前货物装上去
                    continue;
                }
                //剩余容量不足时
                cnt++;//将已有的运走，重置容量
                weightOnShip = w;//将当前货物装上去
            }
            cnt++;//将最后一批运走
            //存在重量比载重大的货物，或所需天数大于规定天数，则增大载重
            if (cnt > days)
                low = mid + 1;
            else//所需天数小于等于规定天数，可尝试减小载重
                high = mid - 1;
        }
        return low;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] weights = {1, 2, 3, 1, 1};
        int days = 4;
        int res = solution.shipWithinDays(weights, days);
        System.out.println(res);
    }
}
