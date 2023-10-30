/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @param {number} k
 * @return {number[][]}
 */
let kSmallestPairs = function (nums1, nums2, k) {
    //取两数组的前k个元素，否则当数组规模过大时会内存超限
    nums1 = nums1.slice(0, Math.min(nums1.length, k));
    nums2 = nums2.slice(0, Math.min(nums2.length, k));
    let len1 = nums1.length,
        len2 = nums2.length;
    k = Math.min(k, len1 * len2);//k可能比两数组所有可能的数对数量都大，所以需要在二者中取较小者

    //数对之和的最小为nums1[0]+nums2[0]，最大为nums1[len1-1]+nums2[len2-1]
    let minSum = nums1[0] + nums2[0];
    let maxSum = nums1[len1 - 1] + nums2[len2 - 1];
    //二分查找一个阈值threshold，使得相加小于阈值的数对数量小于k
    let threshold = 0;
    while (minSum <= maxSum) {
        let midSum = Math.floor((minSum + maxSum) / 2);
        //统计相加小于等于midSum的数对数量
        let index2 = len2 - 1,
            cnt = 0;
        for (let index1 = 0; index1 < len1; index1++) {
            while (index2 >= 0 && nums1[index1] + nums2[index2] > midSum)
                index2--;
            cnt += (index2 + 1);
        }
        if (cnt >= k) {
            maxSum = midSum - 1;
            threshold = midSum;
        } else {
            minSum = midSum + 1;
        }
    }

    let ans = [];
    //将相加小于阈值的数对加入ans
    for (let i = 0; i < len1; i++) {
        if (nums1[i] + nums2[0] >= threshold)
            break;
        for (let j = 0; j < len2; j++) {
            if (nums1[i] + nums2[j] < threshold)
                ans.push([nums1[i], nums2[j]]);
        }
    }
    //将相加等于threshold的数对加入ans集合
    //对于nums1的每个下标i，二分查找nums2的下标j，使得nums1[i]+nums2[j]==threshold
    for (let i = 0; i < len1; i++) {
        //对于当前i，两数之和最小值大于阈值，或最大值小于阈值，则直接跳过查找
        if (nums1[i] + nums2[0] > threshold || nums1[i] + nums2[len2 - 1] < threshold)
            continue;
        let left = 0,
            right = len2 - 1;
        //二分查找j的左边界
        while (left <= right) {
            let mid = Math.floor((left + right) / 2);
            if (nums1[i] + nums2[mid] < threshold)
                left = mid + 1;
            else right = mid - 1;
        }
        //检查左边界是否有效，即完成二分查找后nums1[i]+nums2[left]===threshold是否成立
        let low = -1;
        if (nums1[i] + nums2[left] === threshold)
            low = left; //相加相等，则left为有效值，将left当前值记录
        //low的值仍为-1，则left无效，即对于当前i，没有有效j使nums1[i]+nums2[j]===threshold
        if (low === -1) {
            while (i < len1 - 1 && nums1[i] === nums1[i + 1])
                i++; //跳过所有相同的nums1[i]
            continue;
        }

        //找到了有效左边界，二分查找右边界
        left = low;
        right = len2 - 1;
        while (left <= right) {
            let mid = Math.floor((left + right) / 2);
            if (nums1[i] + nums2[mid] > threshold)
                right = mid - 1;
            else left = mid + 1;
        }
        let high = right; //左边界有效，则找到的右边界也一定有效

        //将闭区间[low,high]间所有数与nums1[i]组成数对
        for (let j = low; j <= high; j++) {
            if (ans.length >= k) break; //数量够了，退出循环
            ans.push([nums1[i], nums2[j]]);
        }
        //对于连续相同的nums1[i]，j的左右边界也相同
        while (i < len1 - 1 && nums1[i] === nums1[i + 1]) {
            for (let j = low; j <= high; j++) {
                if (ans.length >= k) break; //数量够了，退出循环
                ans.push([nums1[i], nums2[j]]);
            }
            i++;
        }
    }
    return ans;
};

let nums1 = [1, 7, 11];
let nums2 = [2, 4, 6];
let k = 3;
console.log(kSmallestPairs(nums1, nums2, k));