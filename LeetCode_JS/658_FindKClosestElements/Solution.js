/**
 * @param {number[]} arr
 * @param {number} k
 * @param {number} x
 * @return {number[]}
 */
var findClosestElements = function (arr, k, x) {
    const n = arr.length;
    // 二分搜索，尝试寻找x
    let low = 0, high = n - 1;
    let targetInd = -1;
    while (low <= high) {
        const mid = (low + high) >> 1;
        if (arr[mid] > x) {
            high = mid - 1;
        } else if (arr[mid] < x) {
            low = mid + 1;
        } else {
            targetInd = mid;
            break;
        }
    }
    // targetInd为-1，则数组中不存在x，则在low和high中取一个
    if (targetInd === -1) {
        if (high < 0)
            targetInd = low;
        else if (high >= n)
            targetInd = high;
        else {
            // 此时low比high大，二者距离相等时应优先取high
            const diff1 = Math.abs(x - arr[low]);
            const diff2 = Math.abs(x - arr[high]);
            if (diff1 < diff2)
                targetInd = low;
            else targetInd = high;
        }
    }

    // 从targetInd向两端扩展，取距离最近的元素加入ans
    const ans = [];
    if (targetInd >= 1 && Math.abs(x - arr[targetInd - 1]) === Math.abs(targetInd)) {
        ans.push(arr[targetInd - 1]);
        low = targetInd - 2;
        high = targetInd;
    } else {
        ans.push(arr[targetInd]);
        low = targetInd - 1;
        high = targetInd + 1;
    }
    while (ans.length < k) {
        const diffLow = low >= 0 ? Math.abs(x - arr[low]) : null;
        const diffHigh = high < n ? Math.abs(x - arr[high]) : null;
        // 二者有一个无效，说明走到尽头，则取另一个
        if (diffLow === null) {
            ans.push(arr[high]);
            high++;
        } else if (diffHigh === null) {
            ans.unshift(arr[low]);
            low--;
        } else {
            // 二者均为有效，取距离较近者
            if (diffLow <= diffHigh) {
                ans.unshift(arr[low]);
                low--;
            } else {
                ans.push(arr[high]);
                high++;
            }
        }
    }
    return ans;
};

var findClosestElements = function (arr, k, x) {
    const n = arr.length;
    // 二分搜索x在arr中的左边界，不存在时返回的是大于x的最小下标
    const leftBound = () => {
        let low = 0, high = n;
        while (low < high) {
            const mid = (low + high) >> 1;
            if (arr[mid] >= x)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    // 查找x所在的位置，pos可能指向x，也可能指向大于x的最小值
    const pos = leftBound();
    let low, high, ans = [];
    if (0 <= pos && pos < n && arr[pos] === x) {
        low = pos;
        high = pos + 1;
    } else {
        low = pos - 1;
        high = pos;
    }
    while (ans.length < k) {
        if (low < 0) {
            ans.push(arr[high]);
            high++;
        } else if (high >= n) {
            ans.unshift(arr[low]);
            low--;
        } else {
            const diff1 = Math.abs(x - arr[low]);
            const diff2 = Math.abs(x - arr[high]);
            if (diff1 <= diff2) {
                ans.unshift(arr[low]);
                low--;
            } else {
                ans.push(arr[high]);
                high++;
            }
        }
    }
    return ans;
}
