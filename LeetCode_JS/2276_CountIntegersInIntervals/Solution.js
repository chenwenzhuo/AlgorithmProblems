class CountIntervals {
    constructor() {
        this.intervals = []; // 存储所有区间
        this.countNums = 0; // 所有区间的长度之和
    }

    /** 
     * @param {number} left 
     * @param {number} right
     * @return {void}
     */
    add(left, right) {
        const { intervals } = this;
        if (intervals.length === 0) { // 空集直接添加
            intervals.push([left, right]);
            this.countNums += (right - left + 1);
            return;
        }
        // 二分查找一个下标i，使intervals[i][0]<=left<=intervals[i+1][0]
        let low = 0, high = intervals.length - 1;
        while (low <= high) {
            const mid = (low + high) >> 1;
            if (intervals[mid][0] > left) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        let startIndex = high; // left在下标startIndex和startIndex+1的两个区间的起点之间
        // 若区间[left,right]与现有区间不相交，直接插入一个区间
        if ((startIndex === intervals.length - 1 && left > intervals[startIndex][1]) ||
            (startIndex < 0 && right < intervals[0][0]) ||
            (startIndex >= 0 && left > intervals[startIndex][1] && right < intervals[startIndex + 1][0])) {
            intervals.splice(startIndex + 1, 0, [left, right]);
            this.countNums += (right - left + 1);
            return;
        }

        // 区间[left,right]与现有区间可以相交
        if (startIndex < 0)
            startIndex = 0;
        if (left > intervals[startIndex][1])
            startIndex++; // 与区间intervals[startIndex]不相交
        let endIndex = startIndex;
        let mergedItvsLenSum = 0;
        while (endIndex < intervals.length && right > intervals[endIndex][1]) {
            mergedItvsLenSum += (intervals[endIndex][1] - intervals[endIndex][0] + 1);
            endIndex++;
        }
        if (endIndex < intervals.length &&
            (intervals[endIndex][0] <= right && right <= intervals[endIndex][1])) {
            mergedItvsLenSum += (intervals[endIndex][1] - intervals[endIndex][0] + 1);
        } else {
            endIndex--;
        }
        // 区间[left,right]与现有可相交区间合并后形成的区间
        const newInterval = [
            Math.min(left, intervals[startIndex][0]),
            Math.max(right, intervals[endIndex][1])
        ];

        // 更新intervals
        let itvsToDelete = endIndex - startIndex + 1;
        intervals.splice(startIndex, itvsToDelete, newInterval);
        this.countNums += (newInterval[1] - newInterval[0] + 1 - mergedItvsLenSum);
    };

    /**
     * @return {number}
     */
    count() {
        return this.countNums;
    };
}
/**
 * Your CountIntervals object will be instantiated and called as such:
 * var obj = new CountIntervals()
 * obj.add(left,right)
 * var param_2 = obj.count()
 */

/* let obj = new CountIntervals();
console.log(obj.count());
obj.add(39, 44);
obj.add(13, 49);
console.log(obj.count());
console.log(obj.count());
obj.add(47, 50); */

let obj = new CountIntervals();
console.log(obj.count());
obj.add(8, 43);
obj.add(13, 16);
obj.add(26, 33);
obj.add(28, 36);
obj.add(29, 37);
console.log(obj.count());
obj.add(34, 46);