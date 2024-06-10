/**
 * @param {number[]} people
 * @param {number} limit
 * @return {number}
 */
const numRescueBoats = function (people, limit) {
    people.sort((a, b) => a - b); // 升序排列
    let cnt = 0;
    // 每船最多2人，则最小重量的应该与尽可能重量大的一起
    let low = 0, high = people.length - 1;
    while (low <= high) {
        // people[low]与people[high]相加不超过limit，则二人共船
        // 下标low和high位置的人不能上一条船，则people[high]必须单独一条船
        if (people[low] + people[high] <= limit) {
            low++;
        }
        high--;
        cnt++;
    }
    return cnt;
};