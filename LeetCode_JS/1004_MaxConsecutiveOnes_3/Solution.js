/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var longestOnes = function (nums, k) {
    const n = nums.length;
    let ans = 0, left = 0, right = 0;
    if (k === 0) { // k为0时单独处理
        while (left < n) {
            if (nums[left] !== 1) { // 寻找一个1作为起点
                left++;
                continue;
            }
            // 增大窗口，直到nums[right]不为1
            right = left;
            while (right < n && nums[right] === 1)
                right++;
            ans = Math.max(ans, right - left);
            left = right;
        }
        return ans;
    }
    
    let winZeroes = 0;
    while (right < n) {
        // 尝试扩大窗口，窗口中最多有k个0
        while (right < n && winZeroes <= k) {
            // right指向0，且0的数量到达上限，则不能扩大窗口
            if (winZeroes === k && nums[right] === 0)
                break;
            // 将right指向的元素纳入窗口，为0时增加计数
            if (nums[right] === 0)
                winZeroes++;
            right++;
        }

        // right为n，或winZeroes为k（下标right为开区间）
        ans = Math.max(ans, right - left);
        while (winZeroes === k) { // 缩小窗口，直到0的数量低于上限
            if (nums[left] === 0)
                winZeroes--;
            left++;
        }
    }
    return ans;
};

var longestOnes = function (nums, k) {
    const n = nums.length;
    let ans = 0, left = 0, winZeroes = 0;
    // 枚举右端点，增大窗口
    let right;
    for (right = 0; right < n; right++) {
        // 将right指向的元素纳入窗口，为0时增加计数
        if (nums[right] === 0)
            winZeroes++;

        if (winZeroes === k) { // 窗口中的0达到上限
            ans = Math.max(ans, right - left + 1);
            // 缩小窗口，直到0的数量低于上限
            while (winZeroes === k) {
                if (nums[left] === 0)
                    winZeroes--;
                left++;
            }
        }
    }
    // 最后再更新一次，此时right为n，为开区间，不用加1
    ans = Math.max(ans, right - left);
    return ans;
};

console.log(longestOnes([0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1], 3));