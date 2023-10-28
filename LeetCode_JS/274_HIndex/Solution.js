/**
 * @param {number[]} citations
 * @return {number}
 */
let hIndex = function (citations) {
    citations.sort((a, b) => a - b);
    let low = 0,
        high = citations.length;
    while (low < high) {
        let mid = Math.floor((low + high) / 2);
        let count = citations.length - mid;
        if (citations[mid] >= count)
            high = mid;
        else low = mid + 1;
    }
    return citations.length - low;
};