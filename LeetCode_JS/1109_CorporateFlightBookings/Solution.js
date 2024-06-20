/**
 * @param {number[][]} bookings
 * @param {number} n
 * @return {number[]}
 */
const corpFlightBookings = function (bookings, n) {
    const diff = new Array(n).fill(0); // 差分数组
    bookings.forEach(bk => {
        const [st, ed, val] = bk;
        diff[st - 1] += val;
        if (ed < n)
            diff[ed] -= val;
    });
    // 从差分数组构建answer数组
    for (let i = 1; i < n; i++)
        diff[i] += diff[i - 1];
    return diff;
};